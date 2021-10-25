package ru.tele2.autoct.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.FileUtils;
import org.vaadin.olli.FileDownloadWrapper;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.views.components.TestCaseForm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCaseConstructorForm extends VerticalLayout {
    public TestCaseConstructorForm(AbonDictionaryService abonDictionaryService,
                                   CheckDictionaryService checkDictionaryService,
                                   AuthLevelService authLevelService,
                                   BranchService branchService,
                                   NotifService notifService,
                                   ServService servService,
                                   TrplService trplService,
                                   TestCaseService testCaseService,
                                   DownloadService downloadService){
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);
        this.setSizeFull();
        TestCaseForm testCaseForm = new TestCaseForm(abonDictionaryService,
                checkDictionaryService,
                authLevelService,
                branchService,
                notifService,
                servService,
                trplService);
        this.add(testCaseForm);
        HorizontalLayout buttonsLine = new HorizontalLayout();
        buttonsLine.setMargin(false);
        buttonsLine.setPadding(false);

        Button saveFromFormButton = new Button("Сохранить в БД", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        saveFromFormButton.setIconAfterText(true);
        saveFromFormButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        saveFromFormButton.addClickListener(event ->{
            if (testCaseForm.isValid()){
                boolean result = testCaseService.save(testCaseService.getTestCaseDtoFromForm(testCaseForm));
                if (result){
                    Notification.show("ТК сохранен").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                } else Notification.show("ТК не сохранен").addThemeVariants(NotificationVariant.LUMO_ERROR);
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

        buttonsLine.add(saveFromFormButton,buttonWrapper);
        this.add(buttonsLine);
    }


}
