package ru.tele2.autoct.services.additionalParams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.tele2.autoct.services.DownloadService;
import ru.tele2.autoct.services.TestCaseService;
import ru.tele2.autoct.services.dictionaries.AbonDictionaryService;
import ru.tele2.autoct.services.dictionaries.BTEDictionaryService;
import ru.tele2.autoct.services.dictionaries.CheckDictionaryService;
import ru.tele2.autoct.services.dictionaries.ProjectService;

@Service
@AllArgsConstructor
@Getter
public class RegistratorImpl implements Registrator {

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
    private final TechnologyTypeService technologyTypeService;
    private final ClientTypeService clientTypeService;
    private final ProjectService projectService;

}
