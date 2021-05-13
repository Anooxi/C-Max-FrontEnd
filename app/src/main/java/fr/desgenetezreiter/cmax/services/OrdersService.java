package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.OrderModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrdersService {
    @GET("orders")
    Call<OrderModel> getOrders();

    @POST("orders")
    Call<OrderModel> addOrders();
}
