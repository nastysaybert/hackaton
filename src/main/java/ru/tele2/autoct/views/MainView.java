package ru.tele2.autoct.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.security.UserService;
import ru.tele2.autoct.views.components.*;

/**
 * Главное view(представление) когда открывается приложение отрабытывает вот этот код
 */
@Route
@PageTitle(value = "AutoCT")
@Slf4j
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
//@CssImport(value = "./styles/grid-style.css")
public class MainView extends VerticalLayout {

    @Autowired
    public MainView(BCryptPasswordEncoder bCryptPasswordEncoder,
                    UserService userService,
                    Registrator registrator) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setWidthFull();
        wrapper.setPadding(false);
        wrapper.setMargin(false);
        wrapper.setSpacing(false);

//        wrapper.add(new LogoutBlock(userService,bCryptPasswordEncoder));
        wrapper.add(new MainLayout(bCryptPasswordEncoder, userService, registrator));

        add(wrapper);
    }
}
