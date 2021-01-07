package ru.itis.html.generator.models;

import ru.itis.html.generator.annotations.HtmlForm;
import ru.itis.html.generator.annotations.HtmlInput;

@HtmlForm(method = "post", action = "/users")
public class User {

    @HtmlInput(name = "firstName", placeholder = "Имя")
    private String firstName;
    @HtmlInput(name = "lastName", placeholder = "Фамилия")
    private String lastName;
    @HtmlInput(name = "password", placeholder = "Пароль")
    private String password;
    @HtmlInput(name = "email", placeholder = "Почта")
    private String email;
}
