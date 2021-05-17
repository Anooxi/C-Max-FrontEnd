package fr.desgenetezreiter.cmax.services;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.models.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RestaurantService {
    @GET("restaurants")
    Call<ArrayList<UserModel>> getRestaurants(@Header("Authorization") String token);
}
