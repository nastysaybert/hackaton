package ru.saybert.hackaton.services;

import ru.saybert.hackaton.dto.TestCaseDto;

import java.io.File;
import java.util.List;

public interface DownloadService {

    File download(List<TestCaseDto> downloadList);
}
