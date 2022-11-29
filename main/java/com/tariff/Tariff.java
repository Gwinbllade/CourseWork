package com.tariff;

public class Tariff {
    String ID;
    String name;
    String subscriptionFee;
    String minutesByOperator;
    String minunesOtherOperators;
    String SMS;
    String mobileData;
    String priceAdditionalMinute;
    String priceAdditionalSMS;
    String priceAdditionalMB;
    String specialCondition;
    public Tariff() {

    }

    public Tariff(String ID, String name, String subscriptionFee, String minutesByOperator, String minunesOtherOperators, String SMS, String mobileData, String priceAdditionalMinute, String priceAdditionalSMS, String priceAdditionalMB, String specialCondition) {
        this.ID = ID;
        this.name = name;
        this.subscriptionFee = subscriptionFee;
        this.minutesByOperator = minutesByOperator;
        this.minunesOtherOperators = minunesOtherOperators;
        this.SMS = SMS;
        this.mobileData = mobileData;
        this.priceAdditionalMinute = priceAdditionalMinute;
        this.priceAdditionalSMS = priceAdditionalSMS;
        this.priceAdditionalMB = priceAdditionalMB;
        this.specialCondition = specialCondition;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(String subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public String getMinutesByOperator() {
        return minutesByOperator;
    }

    public void setMinutesByOperator(String minutesByOperator) {
        this.minutesByOperator = minutesByOperator;
    }

    public String getMinunesOtherOperators() {
        return minunesOtherOperators;
    }

    public void setMinunesOtherOperators(String minunesOtherOperators) {
        this.minunesOtherOperators = minunesOtherOperators;
    }

    public String getSMS() {
        return SMS;
    }

    public void setSMS(String SMS) {
        this.SMS = SMS;
    }

    public String getMobileData() {
        return mobileData;
    }

    public void setMobileData(String mobileData) {
        this.mobileData = mobileData;
    }

    public String getPriceAdditionalMinute() {
        return priceAdditionalMinute;
    }

    public void setPriceAdditionalMinute(String priceAdditionalMinute) {
        this.priceAdditionalMinute = priceAdditionalMinute;
    }

    public String getPriceAdditionalSMS() {
        return priceAdditionalSMS;
    }

    public void setPriceAdditionalSMS(String priceAdditionalSMS) {
        this.priceAdditionalSMS = priceAdditionalSMS;
    }

    public String getPriceAdditionalMB() {
        return priceAdditionalMB;
    }

    public void setPriceAdditionalMB(String priceAdditionalMB) {
        this.priceAdditionalMB = priceAdditionalMB;
    }

    public String getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }
}
