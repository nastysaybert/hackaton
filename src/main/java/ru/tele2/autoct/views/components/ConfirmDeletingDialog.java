package ru.tele2.autoct.views.components;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.dto.TestCaseDto;
import ru.tele2.autoct.services.TestCaseService;
import java.util.List;

public class ConfirmDeletingDialog extends Dialog {
    public ConfirmDeletingDialog(List<TestCaseDto> checkedItems,
                                 TestCaseService testCaseService){
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setPadding(false);
        layout.setSpacing(false);

        HorizontalLayout content1 = new HorizontalLayout();
        Icon warningIcon = new Icon(VaadinIcon.WARNING);
        warningIcon.setColor("red");
//        warningIcon.getStyle().set("font-size", "50px");
        Label content1label = new Label("Внимание!");
        content1label.getStyle().set("color", "red").set("font-size", "1.5em");
        content1.setAlignItems(FlexComponent.Alignment.CENTER);
        content1.add(warningIcon,content1label);


        Label content2 = new Label("Тест-кейс будет удален безвозвратно!");

        Label content3 = new Label("Вы уверены, что хотите удалить?");

        Button confirmButton = new Button("Подтвердить");
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        confirmButton.addClickListener( event -> {
            checkedItems.forEach(testCaseDto -> {
                testCaseService.delete(testCaseDto);
                this.close();
                getUI().get().getPage().reload();
            });
        });

        Button cancelButton = new Button("Отмена");
//        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton.addClickListener( event -> {
            this.close();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(confirmButton, cancelButton);

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(content1, content2, content3, buttons);
        this.add(layout);

    }
}
