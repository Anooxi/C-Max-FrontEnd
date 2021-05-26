package fr.desgenetezreiter.cmax.models;

import java.util.ArrayList;

public class ProductModel {
    private String _id;
    private String name;
    private String description;
    private String img_url;
    private ArrayList<ProductResult> extras;
    private String type;

    public ProductModel(String name,String description, String type){
        this.name = name;
        this.description = description;
        this.type = type;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ProductResult> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<ProductResult> extras) {
        this.extras = extras;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}


