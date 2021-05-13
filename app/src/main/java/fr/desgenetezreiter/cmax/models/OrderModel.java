package fr.desgenetezreiter.cmax.models;

public class OrderModel {
    private Object cart;
    private CreditCardModel creditCard;

    public Object getCart() {
        return cart;
    }

    public void setCart(Object cart) {
        this.cart = cart;
    }

    public CreditCardModel getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardModel creditCard) {
        this.creditCard = creditCard;
    }
}
