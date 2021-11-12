package ru.tele2.autoct.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tele2.autoct.services.additionalParams.*;
import ru.tele2.autoct.services.security.UserService;
import ru.tele2.autoct.views.components.serviceViews.LogoutBlock;
import java.util.HashMap;
import java.util.Map;

public class MainLayout extends VerticalLayout {

    private Map<Tab, Component> tabsToPages = new HashMap<>();
    private Tabs tabs;
    private HorizontalLayout toggleAndLogout = new HorizontalLayout();
    private HorizontalLayout tabsAndContent = new HorizontalLayout();
    private VerticalLayout content = new VerticalLayout();

    public MainLayout(BCryptPasswordEncoder bCryptPasswordEncoder,
                      UserService userService,
                      Registrator registrator){
        frontFormat(this);
        this.setSpacing(false);
        frontFormat(tabsAndContent);
        frontFormat(toggleAndLogout);

        DrawerToggle toggle = new DrawerToggle();
        toggle.getStyle().set("background-color", "var(--lumo-contrast-10pct)");
        toggle.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
        toggle.addClickListener(event -> tabs.setVisible(!tabs.isVisible()));
        toggleAndLogout.add(toggle, new LogoutBlock(userService,bCryptPasswordEncoder));

        tabs = configureTabs(registrator);

        frontFormat(content);
        tabs.addSelectedChangeListener(event ->{
            content.removeAll();
            content.add(tabsToPages.get(event.getSelectedTab()));
        });
        tabs.setSelectedIndex(0);
        content.add(tabsToPages.get(tabs.getSelectedTab()));

        Scroller scroller = new Scroller(content);
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.setSizeFull();
        scroller.getStyle().set("border-bottom", "1px solid var(--lumo-contrast-20pct)");

//        tabsAndContent.add(tabs, content);
        tabsAndContent.add(tabs,scroller);
        add(toggleAndLogout, tabsAndContent);
    }

    private Tabs configureTabs(Registrator registrator){
        Tabs tabs = new Tabs();

        Tab constructTC = createTab(VaadinIcon.CLUSTER, "Конструктор ТК");
        Tab savedTC = createTab(VaadinIcon.DATABASE, "Сохраненные ТК");
        Tab templatesTC = createTab(VaadinIcon.FILE_TABLE, "Шаблоны");

        //вкладка сохраненные
        tabsToPages.put(savedTC, new TestCasesRepresentation(tabsToPages, tabs, constructTC, registrator));
        tabs.add(savedTC);

        //вкладка конструктор
        tabsToPages.put(constructTC, new TestCaseConstructorForm(null,registrator));
        tabs.add(constructTC);

        //вкладка шаблоны
        tabsToPages.put(templatesTC, new TemplatesRepresentation(tabsToPages, tabs, constructTC, registrator));
        tabs.add(templatesTC);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setWidth("20%");

        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");
        Tab tab = new Tab(icon, new Span(viewName));
        tab.getStyle().set("background-color", "var(--lumo-contrast-10pct)");
        tab.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
        return tab;
    }

    private void frontFormat (VerticalLayout component){
        component.setPadding(false);
        component.setMargin(false);
        component.setSizeFull();
    }

    private void frontFormat (HorizontalLayout component){
        component.setPadding(false);
        component.setMargin(false);
        component.setSizeFull();
    }

//    public void changeSelectedTabTo(int index){
//        content.removeAll();
//        content.add(tabsToPages.get(index));
//    }
//
//    private void refreshTab(Tab tab){
//        if (tab.getLabel() == "Сохраненные ТК"){
//            getUI().get().getPage().reload();
//        }
//    }
}
