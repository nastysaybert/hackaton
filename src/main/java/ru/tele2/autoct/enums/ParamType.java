package ru.tele2.autoct.enums;

public enum ParamType {
    SERV ("Услуга"),
    TRPL ("Тариф"),
    NOTIF ("Нотификация"),
    USSD_REQUEST ("USSD-ответ"),
    DURATION ("Продолжительность"),
    BRANCH("Регион"),
    AUTH_LEVEL("Уровень полномочий"),
    CLNT("Шаблон для подготовки SIM"),
    ZONE("Зона вызова"),
    ACTIVATION_METHOD("Метод активации SIM"),
    SUMM ("Сумма"),
    СLNT_TYPE("Тип клиента"),
    TECHNOLOGY("Тип технологии (2G, 3G, 4G, 5G)"),
    COUNT("Количество"),
    TEXT("Текст");


    private String title;

    ParamType (String title){
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
