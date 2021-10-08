package ru.tele2.autoct.views;


import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.tele2.autoct.views.components.LoginOverlayBlock;

@Route("login")
@PageTitle("Login | AutoCT")
public class LoginPage extends VerticalLayout implements HasUrlParameter<String>  {

    private AuthenticationManager authenticationManager;

    public LoginPage (AuthenticationManager authenticationManager){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            add(new LoginOverlayBlock(authenticationManager));  //не залогинлся? логинься
        } else {
            getUI().get().getPage().setLocation("");            //уже логинился? молодец, иди на главную
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent,
                             @OptionalParameter String param) {
        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        if (queryParameters.getParameters().size() !=0){
            Notification notification = new Notification("Некорректные учетные данные!",3000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.open();
        }
    }


}
