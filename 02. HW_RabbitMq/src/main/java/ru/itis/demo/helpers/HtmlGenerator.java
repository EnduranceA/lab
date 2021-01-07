package ru.itis.demo.helpers;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class HtmlGenerator {

    @Autowired
    public FreeMarkerConfigurer freeMarkerConfigurer;

    public String generate(String template, Object params) {
        Configuration config = freeMarkerConfigurer.getConfiguration();
        Template tp = null;
        try {
            tp = config.getTemplate(template);
            StringWriter writer = new StringWriter();
            tp.process(prepareData(params), writer);
            String htmlStr = writer.toString();
            writer.flush();
            writer.close();
            return htmlStr;
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Map<String, Object> prepareData(Object params) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", params);
        return data;
    }

}
