package ru.tele2.autoct.views.components.serviceViews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConfirmRemovingFormDialog extends Dialog {
    public ConfirmRemovingFormDialog(AtomicBoolean confirmation){
        this.setModal(false);
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setPadding(false);
        layout.setSpacing(false);

        HorizontalLayout content = new HorizontalLayout();
        Icon warningIcon = new Icon(VaadinIcon.WARNING);
        warningIcon.setColor("red");
        Label contentlabel = new Label("Уверены, что хотите удалить элемент?");
//        contentlabel.getStyle().set("color", "red").set("font-size", "1.5em");
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        content.add(warningIcon,contentlabel);

        Button confirmButton = new Button(new Icon(VaadinIcon.CHECK_CIRCLE_O));
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        confirmButton.addClickListener( event -> {
            confirmation.set(true);
            this.close();
        });

        Button cancelButton = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE_O));
//        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton.addClickListener( event -> {
            confirmation.set(false);
            this.close();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(confirmButton, cancelButton);

        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(content, buttons);
        this.add(layout);
    }
}
