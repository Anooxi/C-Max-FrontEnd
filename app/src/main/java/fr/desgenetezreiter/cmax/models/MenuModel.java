package fr.desgenetezreiter.cmax.models;

import java.util.ArrayList;

public class MenuModel {
    private String _id;
    private String name;
    private String description;
    private String img_url;;
    private ArrayList<ProductResult> products;
    private String category;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<ProductResult> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductResult> products) {
        this.products = products;
    }

    public double getPrix(){
        double tot = 0;
        for(ProductResult product : products){
            tot += product.getPrice();
        }
        return tot;
    }

    public void addProduct(ProductResult e){
        products.add(e);
    }
}
