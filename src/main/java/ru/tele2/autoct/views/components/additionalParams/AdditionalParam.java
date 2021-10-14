package ru.tele2.autoct.views.components.additionalParams;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.tele2.autoct.enums.ParamType;
import ru.tele2.autoct.services.additionalParams.*;

public class AdditionalParam extends VerticalLayout {
    public AdditionalParam(ParamType paramType,
                           AuthLevelService authLevelService,
                           BranchService branchService,
                           NotifService notifService,
                           ServService servService,
                           TrplService trplService){
        this.setPadding(false);
        this.setMargin(false);
        switch (paramType) {
            case AUTH_LEVEL:
                this.add(new AuthLevelForm(authLevelService));
                break;
            case BRANCH:
                this.add(new BranchForm(branchService));
                break;
            case DURATION:
                this.add(new DurationForm());
                break;
            case NOTIF:
                this.add(new NotifForm(notifService));
                break;
            case SERV:
                this.add(new ServForm(servService));
                break;
            case SUMM:
                this.add(new SummForm());
                break;
            case TRPL:
                this.add(new TrplForm(trplService));
                break;
            case USSD_REQUEST:
                this.add(new USSDRequestForm());
                break;
            default:
                break;
        }
    }
}
