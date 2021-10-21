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
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.services.security.UserService;
import ru.tele2.autoct.views.components.LogoutBlock;
import ru.tele2.autoct.views.components.TestCaseForm;

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
        wrapper.getStyle().set("margin-top", "0px");
        wrapper.getStyle().set("padding-top", "0px");

        wrapper.add(new LogoutBlock(userService,bCryptPasswordEncoder));

        TestCaseForm testCaseForm = new TestCaseForm(abonDictionaryService,
                checkDictionaryService,
                authLevelService,
                branchService,
                notifService,
                servService,
                trplService);
        wrapper.add(testCaseForm);
        HorizontalLayout buttonsLine = new HorizontalLayout();
        buttonsLine.setMargin(false);
        buttonsLine.setPadding(false);

        Button saveFromFormButton = new Button("Сохранить в БД", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        saveFromFormButton.setIconAfterText(true);
        saveFromFormButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        saveFromFormButton.addClickListener(event ->{
            boolean result = testCaseService.save(testCaseService.getTestCaseDtoFromForm(testCaseForm));
            if (result){
                Notification.show("ТК сохранен").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else Notification.show("ТК не сохранен").addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        Button downloadFileButton  = new Button("Выгрузить в *.xlsx", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        downloadFileButton.setIconAfterText(true);
        downloadFileButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
                new StreamResource("TestCase.xlsx", () -> {
                    try {
                        List<TestCaseDto> downloadList = new ArrayList<>();
                        downloadList.add(testCaseService.getTestCaseDtoFromForm(testCaseForm));
                        return new ByteArrayInputStream(FileUtils.readFileToByteArray(downloadService.download(downloadList)));
                    } catch (IOException e) {
                        return null;
                    }
                }));
        buttonWrapper.wrapComponent(downloadFileButton);

        buttonsLine.add(saveFromFormButton, buttonWrapper);
        wrapper.add(buttonsLine);
        add(wrapper);
    }
}
