package ru.saybert.hackaton.views;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.saybert.hackaton.services.additionalParams.Registrator;
import ru.saybert.hackaton.services.security.UserService;


/**
 * Главное view(представление) когда открывается приложение отрабытывает вот этот код
 */
@Route
@PageTitle(value = "Hackaton")
@Slf4j
@Theme(variant = Lumo.LIGHT)
//@CssImport(value = "./styles/grid-style.css")
public class MainView extends VerticalLayout {

    @Autowired
    public MainView(BCryptPasswordEncoder bCryptPasswordEncoder,
                    UserService userService,
                    Registrator registrator) {
        HorizontalLayout wrapper = new HorizontalLayout();
        wrapper.setWidthFull();
        wrapper.setPadding(false);
        wrapper.setMargin(false);
        wrapper.setSpacing(false);
        this.getStyle().set("overflow-y","visible");
        this.setHeight("100vh");

        MessageList list = new MessageList();
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            Notification.show("Message received: " + submitEvent.getValue(),
                    3000, Notification.Position.MIDDLE);
        });
        wrapper.add(list);
        wrapper.add(input);
        add(wrapper);
    }
}
