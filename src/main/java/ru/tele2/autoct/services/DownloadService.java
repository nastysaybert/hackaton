package ru.tele2.autoct.services;

import ru.tele2.autoct.dto.TestCaseDto;

import java.io.File;
import java.util.List;

public interface DownloadService {

    File download(List<TestCaseDto> downloadList);
}
