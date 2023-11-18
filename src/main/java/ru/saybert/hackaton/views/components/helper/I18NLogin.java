package ru.saybert.hackaton.views.components.helper;

import com.vaadin.flow.component.login.LoginI18n;
import lombok.experimental.UtilityClass;

@UtilityClass
public class I18NLogin {
    public LoginI18n russianLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Constructor");
        i18n.getHeader().setDescription("DBI TestCase Creator");
        i18n.getForm().setUsername("Имя пользователя");
        i18n.getForm().setTitle("Введите данные для входа в приложение");
        i18n.getForm().setSubmit("Войти");
        i18n.getForm().setPassword("Пароль");
        i18n.getForm().setForgotPassword("Забыли пароль?");
        i18n.getErrorMessage().setTitle("Неправильный логин или пароль");
        i18n.getErrorMessage()
                .setMessage("Проверьте логин/пароль и повторите попытку входа");
        i18n.setAdditionalInformation("Для входа используйте доменное имя корпоративной учетной записи");
        return i18n;
    }
}
