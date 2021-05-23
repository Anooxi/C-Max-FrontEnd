package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.services.HttpClient;
import fr.desgenetezreiter.cmax.services.OrdersService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {

    private static OrdersService ordersService = HttpClient.getInstance().getOrdersService();
    private static MutableLiveData<ArrayList<OrderResult>> currentOrders = new MutableLiveData<>(new ArrayList<OrderResult>());
    private static MutableLiveData<ArrayList<OrderResult>> pendingOrders = new MutableLiveData<>(new ArrayList<OrderResult>());
    private static MutableLiveData<ArrayList<OrderResult>> previousOrders = new MutableLiveData<>(new ArrayList<OrderResult>());

    public static MutableLiveData<ArrayList<OrderResult>> getOrders(){
        ordersService.getOrder().enqueue(new Callback<ArrayList<OrderResult>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderResult>> call, Response<ArrayList<OrderResult>> response) {
                if(response.isSuccessful()){

                } else {
                    currentOrders.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OrderResult>> call, Throwable t) {
                currentOrders.setValue(null);
            }

        });
        return currentOrders;
    }

    public static void postOrder(OrderModel orderModel){
        ordersService.postOrder(orderModel).enqueue(new Callback<OrderResult>() {
            @Override
            public void onResponse(Call<OrderResult> call, Response<OrderResult> response) {
                if(response.isSuccessful()){
                    currentOrders.getValue().add(response.body());

                } else {

                }
            }

            @Override
            public void onFailure(Call<OrderResult> call, Throwable t) {

            }
        });
    }

}
