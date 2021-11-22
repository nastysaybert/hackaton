package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import ru.tele2.autoct.dto.AdditionalParamDto;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.services.additionalParams.*;

import java.awt.*;

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
    private ClntIdForm clntIdForm;
    private ZoneForm zoneForm;
    private ActivationMethodForm activationMethodForm;
    private TechnologyTypeForm technologyTypeForm;
    private ClientTypeForm clientTypeForm;
    private CountForm countForm;
    private TextForm textForm;

    public AdditionalParam(ParamType paramType,
                           Registrator registrator){
        this.setWidthFull();
        this.setId("AdditionalParam");
        switch (currentParamType = paramType) {
            case AUTH_LEVEL:
                this.add(authLevelForm = new AuthLevelForm(registrator.getAuthLevelService()));
                break;
            case BRANCH:
                this.add(branchForm = new BranchForm(registrator.getBranchService()));
                break;
            case DURATION:
                this.add(durationForm = new DurationForm());
                break;
            case NOTIF:
                this.add(notifForm= new NotifForm(registrator.getNotifService()));
                break;
            case SERV:
                this.add(servForm = new ServForm(registrator.getServService()));
                break;
            case SUMM:
                this.add(summForm = new SummForm());
                break;
            case TRPL:
                this.add(trplForm = new TrplForm(registrator.getTrplService()));
                break;
            case USSD_REQUEST:
                this.add(ussdRequestForm = new USSDRequestForm());
                break;
            case CLNT:
                this.add(clntIdForm = new ClntIdForm());
                break;
            case ZONE:
                this.add(zoneForm = new ZoneForm(registrator.getZoneService()));
                break;
            case ACTIVATION_METHOD:
                this.add(activationMethodForm = new ActivationMethodForm(registrator.getActivationMethodService()));
                break;
            case СLNT_TYPE:
                this.add(clientTypeForm = new ClientTypeForm(registrator.getClientTypeService()));
                break;
            case TECHNOLOGY:
                this.add(technologyTypeForm = new TechnologyTypeForm(registrator.getTechnologyTypeService()));
                break;
            case COUNT:
                this.add(countForm = new CountForm());
                break;
            case TEXT:
                this.add(textForm = new TextForm());
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
            case CLNT:
                this.additionalParamDto = clntIdForm.getParam();
                break;
            case ZONE:
                this.additionalParamDto = zoneForm.getParam();
                break;
            case ACTIVATION_METHOD:
                this.additionalParamDto = activationMethodForm.getParam();
                break;
            case СLNT_TYPE:
                this.additionalParamDto = clientTypeForm.getParam();
                break;
            case TECHNOLOGY:
                this.additionalParamDto = technologyTypeForm.getParam();
                break;
            case COUNT:
                this.additionalParamDto = countForm.getParam();
                break;
            case TEXT:
                this.additionalParamDto = textForm.getParam();
                break;
            default:
                this.additionalParamDto = null;
                break;
        }
    }

    public void setAdditionalParam(AdditionalParamDto additionalParam,
                                   ParamType paramType,
                                   Registrator registrator){
        switch (currentParamType = paramType) {
            case AUTH_LEVEL:
                authLevelForm.setParam(additionalParam, registrator.getAuthLevelService());
                break;
            case BRANCH:
                branchForm.setParam(additionalParam, registrator.getBranchService());
                break;
            case DURATION:
                durationForm.setParam(additionalParam);
                break;
            case NOTIF:
                notifForm.setParam(additionalParam, registrator.getNotifService());
                break;
            case SERV:
                servForm.setParam(additionalParam, registrator.getServService());
                break;
            case SUMM:
                summForm.setParam(additionalParam);
                break;
            case TRPL:
                trplForm.setParam(additionalParam,registrator.getTrplService());
                break;
            case USSD_REQUEST:
                ussdRequestForm.setParam(additionalParam);
                break;
            case CLNT:
                clntIdForm.setParam(additionalParam);
                break;
            case ZONE:
                zoneForm.setParam(additionalParam,registrator.getZoneService());
                break;
            case ACTIVATION_METHOD:
                activationMethodForm.setParam(additionalParam,registrator.getActivationMethodService());
                break;
            case СLNT_TYPE:
                clientTypeForm.setParam(additionalParam,registrator.getClientTypeService());
                break;
            case TECHNOLOGY:
                technologyTypeForm.setParam(additionalParam,registrator.getTechnologyTypeService());
                break;
            case COUNT:
                countForm.setParam(additionalParam);
                break;
            case TEXT:
                textForm.setParam(additionalParam);
                break;
            default:
                break;
        }
    }

    public ParamType getCurrentParamType(){
        return currentParamType;
    }

    public boolean isValid(){
        configureAddParam();
        if (additionalParamDto == null){
            Notification.show("Заполните ключевой параметр!")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        } else return true;
    }
}
