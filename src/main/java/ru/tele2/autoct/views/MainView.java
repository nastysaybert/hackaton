package ru.tele2.autoct.views;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tele2.autoct.services.security.UserService;
import ru.tele2.autoct.views.components.LogoutBlock;


/**
 * Главное view(представление) когда открывается приложение отрабытывает вот этот код
 */
@Route
@PageTitle(value = "AutoCT")
@Slf4j
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
//@CssImport(value = "./styles/grid-style.css")
public class MainView extends VerticalLayout {

//    private final ServiceProviderForView serviceProviderForView;
//    private MemoryBuffer buffer = new MemoryBuffer();
//    private TestCaseGrid grid;

    @Autowired
    public MainView(BCryptPasswordEncoder bCryptPasswordEncoder,
//                    ServiceProviderForView serviceProviderForView,
//                    DownloadService downloadService,
//                    TestCaseService testCaseService,
//                    ProjectService projectService,
                    UserService userService
    ) {
//        this.serviceProviderForView = serviceProviderForView;
//        this.grid = new TestCaseGrid(serviceProviderForView.getTestCaseGridProvider());

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setWidthFull();
        wrapper.setPadding(false);
        wrapper.getStyle().set("margin-top", "0px");
        wrapper.getStyle().set("padding-top", "0px");

        wrapper.add(new LogoutBlock(userService,bCryptPasswordEncoder));
        add(wrapper);

//        HorizontalLayout line1 = new HorizontalLayout();
//        line1.setPadding(true);
//        line1.setWidthFull();
//        //по умолчанию margin и padding у компонентов vaadin стоит по 16px, убираем
//        line1.getStyle().set("margin-top", "0px");
//        line1.getStyle().set("padding-top", "0px");
//        line1.getStyle().set("padding-bottom", "0px");
//
//        HorizontalLayout line2 = new HorizontalLayout();
//        line2.setPadding(true);
//        line2.setWidthFull();
//        line2.getStyle().set("margin-top", "0px");
//        line2.getStyle().set("padding-top", "0px");
//        line2.getStyle().set("padding-bottom", "0px");
//
//        //кнопка и диалог для загрузки файла
//        Button openUploadDialogButton = new Button("Добавить тест-кейсы", new Icon(VaadinIcon.CLOUD_UPLOAD_O));
//        openUploadDialogButton.addClickListener(event -> new UploadDialog(serviceProviderForView, buffer, grid,projectService).open());
//        //кнопку пошире
//        openUploadDialogButton.setWidth("17em");
//        //отступ снаружи - авто, добавить отступ сверху
//        openUploadDialogButton.getStyle().set("margin-left", "auto");
//        openUploadDialogButton.getStyle().set("margin-top", "37px");
//
//        //Блок для поиска
//        line1.add(new SearchBlock(grid), openUploadDialogButton);
//
//
//
//        //Блок для выгрузки в файл
//        line2.add(new DownloadBlock(grid,downloadService,testCaseService));
//
//        //Кнопка для удаления выбранных ТС
//
//        line2.add(new DeleteTestCaseButton(testCaseService,grid));
//        //wrapper.add(new DeleteTestCaseButton(testCaseService,grid));
//
//        wrapper.add(line1);
//        wrapper.add(line2);

//
//        //дерево для отображения ТК
//        add(grid);
    }
}
