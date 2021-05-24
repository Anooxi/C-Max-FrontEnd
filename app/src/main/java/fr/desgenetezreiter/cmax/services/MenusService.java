package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.MenuModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MenusService {
    @POST("menus")
    Call<MenuModel> postMenu(@Path(value="restaurant_id",encoded = true) String restaurant_id,
                             @Body MenuModel menu);
}
