package ru.tele2.autoct.services.additionalParams;

import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.BTEDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;

public interface Registrator {

    ActivationMethodService getActivationMethodService();

    AuthLevelService getAuthLevelService();

    BranchService getBranchService();

    NotifService getNotifService();

    ServService getServService();

    TrplService getTrplService();

    ZoneService getZoneService();

    TestCaseService getTestCaseService();

    AbonDictionaryService getAbonDictionaryService();

    CheckDictionaryService getCheckDictionaryService();

    BTEDictionaryService getBteDictionaryService();

    DownloadService getDownloadService();

    TechnologyTypeService getTechnologyTypeService();

    ClientTypeService getClientTypeService();
}
