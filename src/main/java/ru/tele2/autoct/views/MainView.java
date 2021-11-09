package ru.tele2.autoct.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.vaadin.olli.FileDownloadWrapper;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.BTEDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.services.security.UserService;
import ru.tele2.autoct.views.components.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
                    AbonDictionaryService abonDictionaryService,
                    CheckDictionaryService checkDictionaryService,
                    BTEDictionaryService bteDictionaryService,
                    AuthLevelService authLevelService,
                    BranchService branchService,
                    NotifService notifService,
                    ServService servService,
                    TrplService trplService,
                    TestCaseService testCaseService,
                    DownloadService downloadService) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setWidthFull();
        wrapper.setPadding(false);
        wrapper.setMargin(false);
        wrapper.setSpacing(false);

//        wrapper.add(new LogoutBlock(userService,bCryptPasswordEncoder));
        wrapper.add(new MainLayout(bCryptPasswordEncoder, userService, abonDictionaryService, checkDictionaryService,
                bteDictionaryService, authLevelService, branchService, notifService, servService, trplService,
                testCaseService, downloadService));

//        wrapper.add(new TestCasesAccordion(testCaseService));
        add(wrapper);
    }
}
