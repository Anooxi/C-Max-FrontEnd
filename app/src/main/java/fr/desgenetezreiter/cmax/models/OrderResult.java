package fr.desgenetezreiter.cmax.models;

import java.util.ArrayList;

public class OrderResult {
    private String _id;
    private String status;
    private UserModel client;
    private String deliverer;
    private UserModel restaurant;
    private ArrayList<MenuModel> menus;
    private CreditCardModel payment;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getClient() {
        return client;
    }

    public void setClient(UserModel client) {
        this.client = client;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public UserModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(UserModel restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<MenuModel> menus) {
        this.menus = menus;
    }

    public CreditCardModel getPayment() {
        return payment;
    }

    public void setPayment(CreditCardModel payment) {
        this.payment = payment;
    }
}
