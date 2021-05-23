package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.CartModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartService {
    class AddItemBody {
        public final String menu;
        public final String restaurant;

        public AddItemBody(String menu, String restaurant) {
            this.menu = menu;
            this.restaurant = restaurant;
        }
    }

    @GET("users/me/cart")
    Call<CartModel> getUserCart();

    @POST("users/me/cart")
    Call<CartModel> addInUserCart(@Body AddItemBody body);
}
