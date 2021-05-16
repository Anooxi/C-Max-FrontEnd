package fr.desgenetezreiter.cmax.models;

public class CartModel {
    private Object _id;
    private UserModel restaurant;
    private UserModel client;
    private MenuModel[] menus;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public UserModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(UserModel restaurant) {
        this.restaurant = restaurant;
    }

    public UserModel getClient() {
        return client;
    }

    public void setClient(UserModel client) {
        this.client = client;
    }

    public MenuModel[] getMenus() {
        return menus;
    }

    public void setMenus(MenuModel[] menus) {
        this.menus = menus;
    }
}
