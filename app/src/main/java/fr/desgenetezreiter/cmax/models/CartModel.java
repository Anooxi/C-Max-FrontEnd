package fr.desgenetezreiter.cmax.models;

import java.util.ArrayList;

public class CartModel {
    private Object _id;
    private String restaurantId;
    private String clientId;
    private ArrayList<MenuModel> menus;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public ArrayList<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<MenuModel> menus) {
        this.menus = menus;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getPrixTotal(){
        double tot = 0;
        for(MenuModel menu : menus){
            tot += menu.getPrix();
        }
        return tot;
    }

    public CartModel() {
        _id = null;
        restaurantId = null;
        clientId = null;
        menus = new ArrayList<>();
    }
}
