package fr.desgenetezreiter.cmax.models;

import java.util.ArrayList;

public class ProductSend {
    private ArrayList<String> product;
    private double price;

    public ArrayList<String> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<String> product) {
        this.product.addAll(product);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addProduct(String e){
        product.add(e);
    }
}
