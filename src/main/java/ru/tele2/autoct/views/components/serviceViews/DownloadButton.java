package ru.tele2.autoct.views.components.serviceViews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.FileUtils;
import org.vaadin.olli.FileDownloadWrapper;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.views.components.TestCaseForm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadButton {

    private FileDownloadWrapper buttonWrapper;
    private Button downloadFileButton;

    public DownloadButton(DownloadService downloadService,
                          List<TestCaseDto> downloadList){
        downloadFileButton = new Button("Выгрузить в *.xlsx", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        downloadFileButton.setIconAfterText(true);
        downloadFileButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        buttonWrapper = new FileDownloadWrapper(
                new StreamResource("TestCase.xlsx", () -> {
                    try {
                        return new ByteArrayInputStream(FileUtils.readFileToByteArray(downloadService.download(downloadList)));
                    } catch (IOException e) {
                        return null;
                    }
                }));
        buttonWrapper.wrapComponent(downloadFileButton);
    }

    //отдельный конструктор, чтобы скачать из формы. TODO:убрать
    public DownloadButton(DownloadService downloadService,
                          TestCaseService testCaseService,
                          TestCaseForm testCaseForm){
        Button downloadFileButton  = new Button("Выгрузить в *.xlsx", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        downloadFileButton.setIconAfterText(true);
        downloadFileButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        buttonWrapper = new FileDownloadWrapper(
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
    }

    public FileDownloadWrapper getButtonWrapper(){
        return this.buttonWrapper;
    }

    public Button getDownloadFileButton(){
        return downloadFileButton;
    }


}
