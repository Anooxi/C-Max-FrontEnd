package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.CartModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CartService {
    @GET("cart")
    Call<CartModel> getUserCart();
}
