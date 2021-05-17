package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.GlobalMethods;
import fr.desgenetezreiter.cmax.services.HttpClient;
import fr.desgenetezreiter.cmax.services.RestaurantService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantViewModel extends ViewModel {

    private int status;

    private RestaurantService restaurantService = HttpClient.getInstance().getRestaurantService();
    private MutableLiveData<ArrayList<UserModel>> restaurantList;
    private MutableLiveData<UserModel> currentRestaurant;


    public MutableLiveData<ArrayList<UserModel>> getRestaurantList() {
        if(restaurantList == null){
            restaurantList = new MutableLiveData<>();
        }
        return restaurantList;
    }

    public void setRestaurantList(ArrayList<UserModel> newRestaurantList) {
        if(restaurantList == null){
            restaurantList = new MutableLiveData<>();
        }
        this.restaurantList.setValue(newRestaurantList);
    }


    public MutableLiveData<UserModel> getCurrentRestaurant() {
        if(currentRestaurant == null){
            currentRestaurant = new MutableLiveData<>();
        }
        return currentRestaurant;
    }

    public void setCurrentRestaurant(UserModel newCurrentRestaurant) {
        if(currentRestaurant == null){
            currentRestaurant = new MutableLiveData<>();
        }
        this.currentRestaurant.setValue(newCurrentRestaurant);
    }

    public void getRestaurants(String token){
        restaurantService.getRestaurants("Bearer " + token).enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                if(response.isSuccessful()){
                    setStatus(GlobalMethods.SUCCESS);
                    setRestaurantList(response.body());
                } else {
                    setRestaurantList(null);
                    setStatus(GlobalMethods.ERROR);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                setRestaurantList(null);
                setStatus(GlobalMethods.FAILURE);
            }
        });
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
