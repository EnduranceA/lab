package ru.itis.handlers.handshake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.WebUtils;
import ru.itis.models.CookieValue;
import ru.itis.services.CookieValuesService;
import javax.servlet.http.Cookie;
import java.util.Map;

@Component
public class AuthHandshakeHandler implements HandshakeHandler {

    @Autowired
    private DefaultHandshakeHandler defaultHandshakeHandler;

    @Autowired
    private CookieValuesService cookieValuesService;

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Map<String, Object> map) {
        try {
            ServletServerHttpRequest request = (ServletServerHttpRequest)serverHttpRequest;
            Cookie cookie = WebUtils.getCookie(request.getServletRequest(), "AuthCookie");
            if (cookie != null) {
                CookieValue cookieValue = cookieValuesService.get(cookie.getValue());
                if(cookieValue != null) {
                    map.put("userId", cookieValue.getUser().getId());
                    return defaultHandshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
                }
            }
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
        catch (HandshakeFailureException e) {
            throw new IllegalArgumentException(e);
        }

    }
}