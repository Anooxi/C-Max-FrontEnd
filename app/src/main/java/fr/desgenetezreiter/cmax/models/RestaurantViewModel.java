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

    private final RestaurantService restaurantService = HttpClient.getInstance().getRestaurantService();
    private MutableLiveData<ArrayList<UserModel>> restaurantList;
    private MutableLiveData<UserModel> currentRestaurant;
    private MutableLiveData<ArrayList<MenuModel>> currentRestaurantMenus;
    private MutableLiveData<ArrayList<ProductModel>> currentRestaurantsProducts;



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

    public void getRestaurants(String token,String category){
        restaurantService.getRestaurants(category).enqueue(new Callback<ArrayList<UserModel>>() {
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

    public MutableLiveData<ArrayList<MenuModel>> getCurrentRestaurantMenus() {
        if(currentRestaurantMenus == null){
            currentRestaurantMenus = new MutableLiveData<>();
        }
        return currentRestaurantMenus;
    }

    public void setCurrentRestaurantMenus(ArrayList<MenuModel> newMenus) {
        if(currentRestaurantMenus == null){
            currentRestaurantMenus = new MutableLiveData<>();
        }
        this.currentRestaurantMenus.setValue(newMenus);
    }

    public MutableLiveData<ArrayList<ProductModel>> getCurrentRestaurantsProducts() {
        if(currentRestaurantsProducts == null){
            currentRestaurantsProducts = new MutableLiveData<>();
        }
        return currentRestaurantsProducts;
    }

    public void setCurrentRestaurantsProducts(ArrayList<ProductModel> newProducts) {
        if(currentRestaurantsProducts == null){
            currentRestaurantsProducts = new MutableLiveData<>();
        }
        this.currentRestaurantsProducts.setValue(newProducts);
    }

    public void getMenus(String restaurant_id){
        restaurantService.getMenus(restaurant_id).enqueue(new Callback<ArrayList<MenuModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MenuModel>> call, Response<ArrayList<MenuModel>> response) {
                if(response.isSuccessful()){
                    setCurrentRestaurantMenus(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MenuModel>> call, Throwable t) {
                setCurrentRestaurantMenus(null);
            }
        });
    }

    public void getProducts(String restaurant_id){
        restaurantService.getProducts(restaurant_id).enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if(response.isSuccessful()){
                    setCurrentRestaurantsProducts(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                setCurrentRestaurantsProducts(null);
            }
        });
    }
}
