package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.services.additionalParams.*;

public class AdditionalParam extends Div {

    private AdditionalParamDto additionalParamDto;
    private ParamType currentParamType;
    private AuthLevelForm authLevelForm;
    private BranchForm branchForm;
    private DurationForm durationForm;
    private NotifForm notifForm;
    private ServForm servForm;
    private SummForm summForm;
    private TrplForm trplForm;
    private USSDRequestForm ussdRequestForm;

    public AdditionalParam(ParamType paramType,
                           AuthLevelService authLevelService,
                           BranchService branchService,
                           NotifService notifService,
                           ServService servService,
                           TrplService trplService){
        this.setWidthFull();
        this.setId("AdditionalParam");
        switch (currentParamType = paramType) {
            case AUTH_LEVEL:
                this.add(authLevelForm = new AuthLevelForm(authLevelService));
                break;
            case BRANCH:
                this.add(branchForm = new BranchForm(branchService));
                break;
            case DURATION:
                this.add(durationForm = new DurationForm());
                break;
            case NOTIF:
                this.add(notifForm= new NotifForm(notifService));
                break;
            case SERV:
                this.add(servForm = new ServForm(servService));
                break;
            case SUMM:
                this.add(summForm = new SummForm());
                break;
            case TRPL:
                this.add(trplForm = new TrplForm(trplService));
                break;
            case USSD_REQUEST:
                this.add(ussdRequestForm = new USSDRequestForm());
                break;
            default:
                break;
        }
    }

    public AdditionalParamDto getAdditionalParamDto(){
        configureAddParam();
        return this.additionalParamDto;
    }

    private void configureAddParam(){
        switch (currentParamType) {
            case AUTH_LEVEL:
                this.additionalParamDto = authLevelForm.getParam();
                break;
            case BRANCH:
                this.additionalParamDto = branchForm.getParam();
                break;
            case DURATION:
                this.additionalParamDto = durationForm.getParam();
                break;
            case NOTIF:
                this.additionalParamDto = notifForm.getParam();
                break;
            case SERV:
                this.additionalParamDto = servForm.getParam();
                break;
            case SUMM:
                this.additionalParamDto = summForm.getParam();
                break;
            case TRPL:
                this.additionalParamDto = trplForm.getParam();
                break;
            case USSD_REQUEST:
                this.additionalParamDto = ussdRequestForm.getParam();
                break;
            default:
                this.additionalParamDto = null;
                break;
        }
    }

    public boolean isValid(){
        configureAddParam();
        if (additionalParamDto!=null){
            if (additionalParamDto.getParamId().isEmpty() || additionalParamDto.getParamValue().isEmpty()){
                Notification.show("Заполните ключевой параметр!")
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return false;
            } else return true;
        } else return false;
    }
}
