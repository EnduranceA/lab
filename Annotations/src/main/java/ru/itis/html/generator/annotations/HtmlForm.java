package ru.itis.html.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//указываем, на что можно вешать данную аннотацию
@Target(ElementType.TYPE)
//SOURCE означает, что аннотация будет доступна во время компиляции
@Retention(RetentionPolicy.SOURCE)
public @interface HtmlForm {
    String method() default "get";
    String action() default "/";
}
