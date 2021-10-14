package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.textfield.IntegerField;

public class SummForm extends IntegerField {
    public SummForm(){
//        this.setWidthFull();
//        this.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
//        this.setPrefixComponent(new Icon(VaadinIcon.MONEY));
        this.setLabel("Введите сумму");
        this.setClearButtonVisible(true);
    }
}
