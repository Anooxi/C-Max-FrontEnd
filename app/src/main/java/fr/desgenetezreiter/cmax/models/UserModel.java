package fr.desgenetezreiter.cmax.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserModel {
    private String _id;
    private Boolean admin;
    private String type;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private AddressModel address;

    private String restaurant_name;
    private ArrayList<String> products;
    private ArrayList<ProductModel> productModels;
    private ArrayList<String> menus;
    private ArrayList<MenuModel> menuModels;

    public String getFullAddress(){
        return getAddress().getFullAddress();
    }
    public String getFullName(){
        return String.format("%s %s",getFirst_name(),getLast_name());
    }

    // Get + Set

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public ArrayList<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(ArrayList<ProductModel> productModels) {
        this.productModels = productModels;
    }

    public ArrayList<String> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<String> menus) {
        this.menus = menus;
    }

    public ArrayList<MenuModel> getMenuModels() {
        return menuModels;
    }

    public void setMenuModels(ArrayList<MenuModel> menuModels) {
        this.menuModels = menuModels;
    }
}
