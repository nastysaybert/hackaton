package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.server.StreamResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ru.tele2.autoct.views.components.helper.I18NLogin;

import java.io.InputStream;

@CssImport(value = "./styles/login-overlay-wrapper.css", themeFor = "vaadin-login-overlay-wrapper")
@CssImport(value = "./styles/login-submit-button.css", themeFor = "vaadin-login-form")
public class LoginOverlayBlock extends LoginOverlay{

    public LoginOverlayBlock (AuthenticationManager authenticationManager) {
        this.setI18n(I18NLogin.russianLoginI18n());
        this.setForgotPasswordButtonVisible(false);

        final InputStream stream = getClass().getClassLoader().getResourceAsStream("logo.jpg");
        StreamResource streamResource = new StreamResource("logo.jpg",() -> {return stream;});
        Image logo = new Image(streamResource, "logo");
        logo.setHeight("220px");
        logo.setWidth("310px");


        this.setTitle(logo);
        this.getTitle().getElement().getStyle().set("background-color", "black");
//        this.getElement().getThemeList().add("dark");
//        this.getElement().getStyle().set("background-image", "../../../src/main/webapp/WEB-INF/images/logo.jpg");


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
