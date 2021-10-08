package ru.tele2.autoct.enums;

public enum ParamType {
    SERV ("Услуга"),
    TRPL ("Тариф"),
    NOTIF ("Нотификация"),
    USSD_REQUEST ("USSD-ответ"),
    DURATION ("Продолжительность"),
    BRANCH("Регион"),
    SUMM ("Сумма");

    private String title;

    ParamType (String title){
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
