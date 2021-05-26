package fr.desgenetezreiter.cmax.models;

import java.util.ArrayList;

public class MenuSend {
    private String name;
    private String description;
    private String img_url;
    private ArrayList<ProductSend> products = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ArrayList<ProductSend> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductSend> products) {
        this.products = products;
    }

    public void addProduct(ProductSend e){
        products.add(e);
    }
}
