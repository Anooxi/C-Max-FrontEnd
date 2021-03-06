package fr.desgenetezreiter.cmax.models;

public class CreditCardModel {
    private String number;
    private String expiration;
    private String ccv;

    public CreditCardModel(String number, String expiration, String ccv) {
        this.number = number;
        this.expiration = expiration;
        this.ccv = ccv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
}
