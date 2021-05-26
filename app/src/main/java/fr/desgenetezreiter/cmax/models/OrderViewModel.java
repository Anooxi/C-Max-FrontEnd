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
    private static MutableLiveData<Boolean> success = new MutableLiveData<Boolean>();

    public static MutableLiveData<ArrayList<OrderResult>> getOrders(){
        ordersService.getOrder().enqueue(new Callback<ArrayList<OrderResult>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderResult>> call, Response<ArrayList<OrderResult>> response) {
                if(response.isSuccessful()){
                    ArrayList<OrderResult> orderResults = response.body();
                    ArrayList<OrderResult> pending = new ArrayList<>();
                    ArrayList<OrderResult> closed = new ArrayList<>();

                    for(OrderResult order : orderResults){
                        if(order.isClosed()){
                            closed.add(order);
                        } else {
                            pending.add(order);
                        }
                    }
                    pendingOrders.setValue(pending);
                    previousOrders.setValue(closed);

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
                    setSuccess(true);
                } else {
                    setSuccess(false);
                }
            }

            @Override
            public void onFailure(Call<OrderResult> call, Throwable t) {
                setSuccess(false);
            }
        });
    }

    public static MutableLiveData<ArrayList<OrderResult>> getCurrentOrders() {
        if(OrderViewModel.currentOrders == null){
            OrderViewModel.currentOrders = new MutableLiveData<>();
        }
        return currentOrders;
    }

    public static void setCurrentOrders(ArrayList<OrderResult> currentOrders) {
        if(OrderViewModel.currentOrders == null){
            OrderViewModel.currentOrders = new MutableLiveData<>();
        }
        OrderViewModel.currentOrders.setValue(currentOrders);
    }

    public static MutableLiveData<ArrayList<OrderResult>> getPendingOrders() {
        if(OrderViewModel.pendingOrders == null){
            OrderViewModel.pendingOrders = new MutableLiveData<>();
        }
        return pendingOrders;
    }

    public static void setPendingOrders(ArrayList<OrderResult> pendingOrders) {
        if(OrderViewModel.pendingOrders == null){
            OrderViewModel.pendingOrders = new MutableLiveData<>();
        }
        OrderViewModel.pendingOrders.setValue(pendingOrders);
    }

    public static MutableLiveData<ArrayList<OrderResult>> getPreviousOrders() {
        if(OrderViewModel.previousOrders == null){
            OrderViewModel.previousOrders = new MutableLiveData<>();
        }
        return previousOrders;
    }

    public static void setPreviousOrders(ArrayList<OrderResult> previousOrders) {
        if(OrderViewModel.previousOrders == null){
            OrderViewModel.previousOrders = new MutableLiveData<>();
        }
        OrderViewModel.previousOrders.setValue(previousOrders);
    }

    public static MutableLiveData<Boolean> getSuccess() {
        if(OrderViewModel.success == null){
            OrderViewModel.success = new MutableLiveData<>();
        }
        return success;
    }

    public static void setSuccess(Boolean success) {
        if(OrderViewModel.success == null){
            OrderViewModel.success = new MutableLiveData<>();
        }
        OrderViewModel.success.setValue(success);
    }
}
