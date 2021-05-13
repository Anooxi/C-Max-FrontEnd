package fr.desgenetezreiter.cmax.models;

public class RegisterModel {

    private final String email;
    private final String password;
    private final String first_name;
    private final String last_name;
    private final String phone;
    private final String restaurant_name;
    private final AddressModel address;
    private final String type;

    // Client et Livreur
    public RegisterModel(String email, String password, String first_name, String last_name, String phone, AddressModel address, String type) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.restaurant_name = null;
    }

    // Restaurant
    public RegisterModel(String email, String password, String first_name, String last_name, String phone, String restaurant_name, AddressModel address, String type) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.restaurant_name = restaurant_name;
        this.address = address;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public AddressModel getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
}
