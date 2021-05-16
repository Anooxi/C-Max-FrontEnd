package fr.desgenetezreiter.cmax.models;

public class AddressModel {
    public String street;
    public String city;
    public String zip;
    public String country;

    public String getFullAddress(){
        return String.format("%s, %s, %s, %s",street,city,zip,country);
    }

    public void setFullAddress(String street,String city, String zip, String country){
        this.setCity(city);
        this.setStreet(street);
        this.setCountry(country);
        this.setZip(zip);

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
