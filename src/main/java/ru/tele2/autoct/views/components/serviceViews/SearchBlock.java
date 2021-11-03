package ru.tele2.autoct.views.components.serviceViews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class SearchBlock extends HorizontalLayout {

    private TextField searchField;
    private Button searchButton;

    public SearchBlock(){
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);
        this.setWidthFull();
        this.setJustifyContentMode(JustifyContentMode.END);

        searchField = new TextField();
        searchField.setPlaceholder("Поиск");
        searchField.setClearButtonVisible(true);
        searchField.setWidth("50%");
        searchButton = new Button(new Icon(VaadinIcon.SEARCH));
        this.add(searchField,searchButton);

    }

    public Button getSearchButton() {
        return searchButton;
    }

    public TextField getSearchField() {
        return searchField;
    }
}
