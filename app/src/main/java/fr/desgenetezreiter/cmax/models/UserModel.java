package fr.desgenetezreiter.cmax.models;

public class UserModel {
    private Object _id;
    private Boolean admin;
    private String type;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private AddressModel address;

    private String restaurant_name;
    private ProductModel[] products;
    private MenuModel[] menus;

    public String getFullAddress(){
        return getAddress().getFullAddress();
    }
    public String getFullName(){
        return String.format("%s %s",getFirst_name(),getLast_name());
    }

    // Get + Set

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
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

    public ProductModel[] getProducts() {
        return products;
    }

    public void setProducts(ProductModel[] products) {
        this.products = products;
    }

    public MenuModel[] getMenus() {
        return menus;
    }

    public void setMenus(MenuModel[] menus) {
        this.menus = menus;
    }
}
