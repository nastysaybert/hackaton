package ru.saybert.hackaton.services.additionalParams;

import ru.saybert.hackaton.services.DownloadService;
import ru.saybert.hackaton.services.TestCaseService;
import ru.saybert.hackaton.services.dictionaries.AbonDictionaryService;
import ru.saybert.hackaton.services.dictionaries.BTEDictionaryService;
import ru.saybert.hackaton.services.dictionaries.CheckDictionaryService;
import ru.saybert.hackaton.services.dictionaries.ProjectService;

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

    ProjectService getProjectService();
}
