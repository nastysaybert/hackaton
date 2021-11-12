package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.BTEDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;

@Service
@AllArgsConstructor
@Getter
public class RegistratorImpl implements Registrator {

//    private final NotifMapper notifMapper;
//    private final NotifRepository notifRepository;
//    private final AuthLevelRepository authLevelRepository;
//    private final AuthLevelMapper authLevelMapper;
//    private final BranchRepository branchRepository;
//    private final BranchMapper branchMapper;
//    private final ServRepository servRepository;
//    private final ServMapper servMapper;
//    private final TrplRepository trplRepository;
//    private final TrplMapper trplMapper;
//    private final ZoneRepository zoneRepository;
//    private final ZoneMapper zoneMapper;

    private final ActivationMethodService activationMethodService;
    private final AuthLevelService authLevelService;
    private final BranchService branchService;
    private final NotifService notifService;
    private final ServService servService;
    private final TrplService trplService;
    private final ZoneService zoneService;
    private final TestCaseService testCaseService;
    private final AbonDictionaryService abonDictionaryService;
    private final CheckDictionaryService checkDictionaryService;
    private final BTEDictionaryService bteDictionaryService;
    private final DownloadService downloadService;

//    public ActivationMethodService getActivationMethodService(){
//        return activationMethodService;
//    }
//
//    public AuthLevelService getAuthLevelService(){
//        return authLevelService;
//    }
//
//    public BranchService getBranchService() {
//        return branchService;
//    }
//
//    public NotifService getNotifService() {
//        return notifService;
//    }
//
//    public ServService getServService() {
//        return servService;
//    }
//
//    public TrplService getTrplService() {
//        return trplService;
//    }
//
//    public ZoneService getZoneService() {
//        return zoneService;
//    }
}
