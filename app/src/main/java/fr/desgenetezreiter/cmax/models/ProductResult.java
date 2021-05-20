package fr.desgenetezreiter.cmax.models;

public class ProductResult {
    private ProductModel product;
    private double price;

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
