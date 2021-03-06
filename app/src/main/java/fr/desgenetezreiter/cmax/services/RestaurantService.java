package fr.desgenetezreiter.cmax.services;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.models.MenuModel;
import fr.desgenetezreiter.cmax.models.ProductModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("restaurants")
    Call<ArrayList<UserModel>> getRestaurants(@Query("category") String category);

    @GET("restaurants/{restaurant_id}/menus")
    Call<ArrayList<MenuModel>> getMenus(@Path(value="restaurant_id", encoded = true) String restaurantId);

    @GET("restaurants/{restaurant_id}/products")
    Call<ArrayList<ProductModel>> getProducts(@Path(value="restaurant_id", encoded = true) String restaurantId);

    @POST("restaurants/{restaurant_id}/products")
    Call<ArrayList<ProductModel>> postProduct(@Path(value="restaurant_id", encoded = true) String restaurantId,
                                              @Body ProductModel product);

    @DELETE("restaurants/{restaurant_id}/products/{product_id}")
    Call<ArrayList<ProductModel>> deleteProduct(@Path(value="restaurant_id", encoded = true) String restaurantId,
                                                @Path(value="product_id", encoded = true) String productId);
}
