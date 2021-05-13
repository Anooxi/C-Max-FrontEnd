package fr.desgenetezreiter.cmax.models;

public class LoginModel {
    private final String password;
    private final String email;

    public LoginModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
