package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.MenuModel;
import fr.desgenetezreiter.cmax.models.MenuSend;
import fr.desgenetezreiter.cmax.models.MenuViewModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MenusService {
    @POST("menus")
    Call<Void> postMenu(@Body MenuViewModel.MenuBody menu);
}
