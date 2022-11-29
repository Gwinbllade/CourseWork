package com.user;



public class Subscriber extends User{
    public Subscriber(){}

    public Subscriber(String userID, String surname, String name, String patronymic, String phone, String tariffName, String tariffID) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.tariffID = tariffID;
        this.userID = userID;
        this.tariffName = tariffName;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    private String name, password, surname, patronymic, phone, tariffID, userID,tariffName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTariffID() {
        return tariffID;
    }

    public void setTariffID(String tariffID) {
        this.tariffID = tariffID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setInformation(String[] args){
        userID = args[0];
        password = args[1];
        phone = args[2];
        surname = args[3];
        name = args[4];
        patronymic = args[5];
        tariffID = args[6];
        tariffName = args[7];
    }



}
