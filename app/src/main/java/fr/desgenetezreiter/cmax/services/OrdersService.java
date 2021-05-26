package fr.desgenetezreiter.cmax.services;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.models.OrderModel;
import fr.desgenetezreiter.cmax.models.OrderResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrdersService {
    @GET("orders")
    Call<ArrayList<OrderResult>> getOrder();

    @POST("orders")
    Call<OrderResult> postOrder(@Body OrderModel orderModel);

    @POST("orders/{orderId}")
    Call<String> nextStatus(@Path(value="orderId", encoded = true) String orderId);
}
