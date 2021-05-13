package fr.desgenetezreiter.cmax.models;

public class ProductModel {
    private String name;
    private String description;
    private String img_url;
    private ProductModel[] extras;
    private String type;

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

    public ProductModel[] getExtras() {
        return extras;
    }

    public void setExtras(ProductModel[] extras) {
        this.extras = extras;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
