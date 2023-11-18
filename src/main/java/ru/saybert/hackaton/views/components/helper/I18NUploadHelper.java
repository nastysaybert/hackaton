package ru.saybert.hackaton.views.components.helper;

import com.vaadin.flow.component.upload.UploadI18N;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class I18NUploadHelper {

    public static UploadI18N getI18n() {
        UploadI18N i18n = new UploadI18N();
        i18n.setDropFiles(
            new UploadI18N.DropFiles().setOne("Перетащите файл сюда...")
                .setMany("Перетащите файлы сюда..."))
            .setAddFiles(new UploadI18N.AddFiles()
                .setOne("Выбрать файл").setMany("Добавить файлы"))
            .setCancel("Отменить")
            .setError(new UploadI18N.Error()
                .setTooManyFiles("Слишком много файлов.")
                .setFileIsTooBig("Слишком большой файл.")
                .setIncorrectFileType("Некорректный тип файла."))
            .setUploading(new UploadI18N.Uploading()
                .setStatus(new UploadI18N.Uploading.Status()
                    .setConnecting("Соединение...")
                    .setStalled("Загрузка застопорилась.")
                    .setProcessing("Обработка файла..."))
                .setRemainingTime(
                    new UploadI18N.Uploading.RemainingTime()
                        .setPrefix("оставшееся время: ")
                        .setUnknown(
                            "оставшееся время неизвестно"))
                .setError(new UploadI18N.Uploading.Error()
                    .setServerUnavailable("Сервер недоступен")
                    .setUnexpectedServerError(
                        "Неожиданная ошибка сервера")
                    .setForbidden("Загрузка запрещена")))
            .setUnits(Stream
                .of("Б", "Кбайт", "Мбайт", "Гбайт", "Тбайт", "Пбайт",
                    "Эбайт", "Збайт", "Ибайт")
                .collect(Collectors.toList()));

        return i18n;
    }
}
