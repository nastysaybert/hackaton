package ru.tele2.autoct.views.components.serviceViews;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tele2.autoct.services.security.UserService;


public class LogoutBlock extends HorizontalLayout {
    public LogoutBlock(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.setWidthFull();
        this.setJustifyContentMode(JustifyContentMode.END);
        this.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        this.setHeight("25px");

        MenuBar userMenu = new MenuBar();
        userMenu.addThemeVariants(MenuBarVariant.LUMO_SMALL);
        MenuItem currentUser = userMenu.addItem(new Icon(VaadinIcon.USER_CHECK));
        currentUser.add(new Text(" " + SecurityContextHolder.getContext().getAuthentication().getName()));
        SubMenu userSubMenu = currentUser.getSubMenu();

        MenuItem changePassword = userSubMenu.addItem(new Icon(VaadinIcon.PASSWORD));
        changePassword.add(new Text(" Сменить пароль"));
        changePassword.addClickListener(event -> {
            new ChangePasswordDialog(userService,bCryptPasswordEncoder).open();
        });
        changePassword.getElement().getStyle().set("padding-top", "0px");
        changePassword.getElement().getStyle().set("padding-bottom", "0px");
        changePassword.getElement().getStyle().set("padding-right", "7px");

        MenuItem logout = userSubMenu.addItem(new Icon(VaadinIcon.EXIT_O));
        logout.add(new Text(" Выйти"));
        logout.addClickListener(event ->{
                getUI().get().getPage().setLocation("logout");
        });
        logout.getElement().getStyle().set("padding-top", "0px");
        logout.getElement().getStyle().set("padding-bottom", "0px");
        logout.getElement().getStyle().set("padding-right", "7px");

        add(userMenu);
    }
}
