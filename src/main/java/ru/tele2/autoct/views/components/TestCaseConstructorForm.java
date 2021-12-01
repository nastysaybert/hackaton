package ru.tele2.autoct.views.components;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.views.components.serviceViews.ConfirmRemovingFormDialog;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestCaseConstructorForm extends VerticalLayout implements BeforeLeaveObserver {

    private TestCaseForm testCaseForm;
    private Registrator registrator;

    public TestCaseConstructorForm(TestCaseDto testCaseDto,
                                   Registrator registrator){
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);
        this.setSizeFull();
        testCaseForm = new TestCaseForm(testCaseDto, registrator);
        this.add(testCaseForm);
        HorizontalLayout buttonsLine = new HorizontalLayout();
        buttonsLine.setMargin(false);
        buttonsLine.setPadding(false);

        Button saveFromFormButton = new Button("Сохранить в БД", new Icon(VaadinIcon.ARROW_CIRCLE_DOWN_O));
        saveFromFormButton.setIconAfterText(true);
        saveFromFormButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        saveFromFormButton.addClickListener(event ->{
            if (!testCaseForm.isTemplate()){
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
            } else {
                if (testCaseForm.getHeader().isEmpty()){
                    Notification.show("Заполните наименование шаблона").addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else{
                    boolean result = registrator.getTestCaseService().save(registrator.getTestCaseService()
                            .getTestCaseDtoFromForm(testCaseForm));
                    if (result){
                        //если сохранили ТК, то перебрасываемся на список ТК из БД
                        getParent().get().getUI().get().getPage().reload();
                        Notification.show("ТК сохранен").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }
                }
            }

        });

        Button resetConstructor = new Button("Сброс конструктора", new Icon(VaadinIcon.FILE_REFRESH));
        resetConstructor.setIconAfterText(true);
        resetConstructor.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        resetConstructor.getElement().setProperty("title", "Сброс конструктора без сохранения изменений");
        resetConstructor.addClickListener(event ->{
            AtomicBoolean confirm = new AtomicBoolean();
            String text = new String("Уверены, что хотите выполнить сброс конструктора? " +
                    "Текущие изменения не будут сохранены");
            ConfirmRemovingFormDialog confirmDialog  = new ConfirmRemovingFormDialog(confirm,text);
            confirmDialog.open();
            confirmDialog.addOpenedChangeListener( dialogOpenedChangeEvent ->{
                if (confirm.get()) {
                    this.remove(testCaseForm);
                    testCaseForm = new TestCaseForm(null, registrator);
                    this.addComponentAsFirst(testCaseForm);
                }
            });
        });

        buttonsLine.add(saveFromFormButton,resetConstructor);

//        UI.getCurrent().addBeforeLeaveListener()
//
//        UI.getCurrent().getSession().getService().addServiceDestroyListener(event ->{
//            TestCaseDto dtoFromForm = registrator.getTestCaseService()
//                        .getTestCaseDtoFromForm(testCaseForm);
//            if (dtoFromForm.getTestCaseStepList().size()>0){
//                TestCaseDto autosaved = new TestCaseDto();
//                autosaved.setName("[Автосохраненный] "+ dtoFromForm.getName());
//                autosaved.setTemplate(dtoFromForm.isTemplate());
//                autosaved.setInitialData(dtoFromForm.getInitialData());
//                autosaved.setTestCaseStepList(dtoFromForm.getTestCaseStepList());
//                registrator.getTestCaseService().save(autosaved);
//            }
//        });

        this.add(buttonsLine);
    }


    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
        beforeLeaveEvent.postpone();
        TestCaseDto dtoFromForm = registrator.getTestCaseService()
                .getTestCaseDtoFromForm(testCaseForm);
        if (dtoFromForm.getTestCaseStepList().size()>0){
            TestCaseDto autosaved = new TestCaseDto();
            autosaved.setName("[Автосохраненный] "+ dtoFromForm.getName());
            autosaved.setTemplate(dtoFromForm.isTemplate());
            autosaved.setInitialData(dtoFromForm.getInitialData());
            autosaved.setTestCaseStepList(dtoFromForm.getTestCaseStepList());
            registrator.getTestCaseService().save(autosaved);
        }
        beforeLeaveEvent.getContinueNavigationAction().proceed();
    }
}
