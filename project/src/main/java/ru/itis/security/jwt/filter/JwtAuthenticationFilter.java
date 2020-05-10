package ru.itis.security.jwt.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.security.jwt.authentication.JwtAuthentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component("jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) {
        try {
            // преобразуем запрос в HTTP
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            // получаем токен
            String token = request.getHeader("Authorization");
            // создаем объект аутентификации
            Authentication authentication = new JwtAuthentication(token);
            // кладем его в контекст для текущего потока
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // отправили запрос дальше
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }
}