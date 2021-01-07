package ru.itis.html.generator;

import com.google.auto.service.AutoService;
import ru.itis.html.generator.annotations.HtmlForm;
import ru.itis.html.generator.annotations.HtmlInput;
import ru.itis.html.generator.config.FreemarkerConfiguration;
import ru.itis.html.generator.models.forms.Form;
import ru.itis.html.generator.models.forms.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes(value = {"ru.itis.html.generator.annotations.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    private final FreemarkerConfiguration freemarkerConfiguration;

    public HtmlProcessor() {
        this.freemarkerConfiguration = new FreemarkerConfiguration();
    }

    public boolean process(Set<? extends TypeElement> annotation, RoundEnvironment roundEnvironment) {
        //получаем типы с аннотацией HtmlForm
        Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element: annotatedElements) {
            HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
            Form form = Form.builder()
                    .action(htmlForm.action())
                    .method(htmlForm.method())
                    .inputs(getInputsFromForm(element.getEnclosedElements()))
                    .build();
            //1)получаем путь к class-файлам
            //2)создаем путь к html-файлам
            //3)User.class -> User.html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath().substring(1) + element.getSimpleName().toString() +".html";
            freemarkerConfiguration.createHtmlFromTemplate(form, path);
        }
        return true;
    }

    private List<Input> getInputsFromForm(List<? extends Element> elements) {
        List<Input> inputs = new ArrayList<>();
        for (Element el : elements) {
            HtmlInput htmlInput = el.getAnnotation(HtmlInput.class);
            if (htmlInput != null) {
                Input input = Input.builder()
                        .name(htmlInput.name())
                        .placeholder(htmlInput.placeholder())
                        .type(htmlInput.type())
                        .build();
                inputs.add(input);
            }
        }
        return inputs;
    }
}
