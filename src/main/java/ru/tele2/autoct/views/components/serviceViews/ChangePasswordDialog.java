package ru.tele2.autoct.views.components.serviceViews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tele2.autoct.dto.security.UserDto;
import ru.tele2.autoct.services.security.UserService;


public class ChangePasswordDialog extends Dialog {

//    UserService userService;
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ChangePasswordDialog(UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder){
        VerticalLayout layout =  new VerticalLayout();

        PasswordField currentPassword = new PasswordField();
        currentPassword.setLabel("Введите текущиий пароль");
        currentPassword.setRequired(true);
        currentPassword.setWidthFull();
        layout.add(currentPassword);

        PasswordField newPassword = new PasswordField();
        newPassword.setLabel("Введиите новый пароль");
        newPassword.setRequired(true);
        newPassword.setWidthFull();
        layout.add(newPassword);

        PasswordField repeatNewPassword = new PasswordField();
        repeatNewPassword.setLabel("Подтвердите новый пароль");
        repeatNewPassword.setRequired(true);
        repeatNewPassword.setWidthFull();
        layout.add(repeatNewPassword);

        Button changePasswordButton = new Button("Сменить пароль");
        changePasswordButton.addClickListener(event -> {
            if (!(newPassword.getValue().equals(repeatNewPassword.getValue()))){
                Notification.show("Подтверждение пароля не совпадает!").addThemeVariants(NotificationVariant.LUMO_ERROR);
                newPassword.setValue("");
                repeatNewPassword.setValue("");
            } else {
                UserDto currentUser = userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

                if (bCryptPasswordEncoder.matches(currentPassword.getValue(), currentUser.getPassword())){
                    if (userService.changePassword(currentUser, newPassword.getValue())){
                        Notification.show("Пароль успешно изменен!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        this.close();
                    }
                } else {
                    Notification.show("Текущий пароль введен неверно!").addThemeVariants(NotificationVariant.LUMO_ERROR);
                    currentPassword.setValue("");
                }
            }

        });
        layout.add(changePasswordButton);

        add(layout);

    }
}
