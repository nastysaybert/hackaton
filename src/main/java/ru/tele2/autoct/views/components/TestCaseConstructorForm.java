package ru.tele2.autoct.views.components;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.serviceViews.DownloadButton;

public class TestCaseConstructorForm extends VerticalLayout {
    public TestCaseConstructorForm(TestCaseDto testCaseDto,
                                   Registrator registrator){
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);
        this.setSizeFull();
        TestCaseForm testCaseForm = new TestCaseForm(testCaseDto, registrator);
        this.add(testCaseForm);
        HorizontalLayout buttonsLine = new HorizontalLayout();
        buttonsLine.setMargin(false);
        buttonsLine.setPadding(false);

        Button saveFromFormButton = new Button("Сохранить в БД", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        saveFromFormButton.setIconAfterText(true);
        saveFromFormButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        saveFromFormButton.addClickListener(event ->{
            if (testCaseForm.isValid()){
                boolean result = registrator.getTestCaseService().save(registrator.getTestCaseService()
                        .getTestCaseDtoFromForm(testCaseForm));
                if (result){
                    //если сохранили ТК, то перебрасываемся на список ТК из БД
                    getParent().get().getUI().get().getPage().reload();
                    Notification.show("ТК сохранен").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                } else Notification.show("ТК с таким именем уже существует, скорректируйте название")
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else Notification.show("ТК не сохранен").addThemeVariants(NotificationVariant.LUMO_ERROR);
        });
        buttonsLine.add(saveFromFormButton,
                new DownloadButton(registrator.getDownloadService(), registrator.getTestCaseService(), testCaseForm)
                        .getButtonWrapper());
        this.add(buttonsLine);
    }
}
