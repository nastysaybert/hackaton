package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.login.LoginOverlay;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ru.tele2.autoct.views.components.helper.I18NLogin;

public class LoginOverlayBlock extends LoginOverlay{

    public LoginOverlayBlock (AuthenticationManager authenticationManager){
        this.setI18n(I18NLogin.russianLoginI18n());
        this.setForgotPasswordButtonVisible(false);
        this.setAction("login");        //to post the login form to Spring Security
        this.addLoginListener(event -> {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(event.getUsername(), event.getPassword()));
                getUI().get().getPage().setLocation("");        //залогинился и иди на главную
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        this.setOpened(true);
    }


}
