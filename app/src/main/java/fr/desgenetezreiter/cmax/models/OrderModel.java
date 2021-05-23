package fr.desgenetezreiter.cmax.models;

public class OrderModel {
    private String cart;
    private CreditCardModel credit_card;

    public OrderModel(String cart, CreditCardModel credit_card) {
        this.cart = cart;
        this.credit_card = credit_card;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public CreditCardModel getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(CreditCardModel credit_card) {
        this.credit_card = credit_card;
    }
}
