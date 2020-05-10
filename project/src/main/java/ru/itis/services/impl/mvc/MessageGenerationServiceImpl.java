package ru.itis.services.impl.mvc;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.services.interfaces.MessageGenerationService;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageGenerationServiceImpl implements MessageGenerationService {

    @Value("${spring.mail.username}")
    private String userName;

    @Autowired
    public Configuration freeMarkerConfig;

    @Override
    public MimeMessagePreparator createMessage(String subject, String text, String email) {
        Map<String, Object> model = new HashMap<>();
        model.put("data", text);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(userName);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(getContentFromTemplate(model), true);
        };
        return messagePreparator;
    }

    private String getContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(
                            freeMarkerConfig.getTemplate("mail.ftl"), model));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return content.toString();
    }
}
