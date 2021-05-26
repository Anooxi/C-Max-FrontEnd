package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.services.CartService;
import fr.desgenetezreiter.cmax.services.HttpClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    private static final CartService cartService = HttpClient.getInstance().getCartService();
    private static final MutableLiveData<CartModel> cart = new MutableLiveData<>(new CartModel());

    public static MutableLiveData<CartModel> getCart() {
        cartService.getUserCart().enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(@NotNull Call<CartModel> call, @NotNull Response<CartModel> response) {
                if (response.isSuccessful()) {
                    cart.setValue(response.body());
                } else {
                    cart.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CartModel> call, @NotNull Throwable t) {
                cart.setValue(null);
            }
        });
        return cart;
    }

    public static MutableLiveData<CartModel> addInCart(String menuId, String restaurantId) {
        cartService.addInUserCart(new CartService.AddItemBody(menuId, restaurantId))
                .enqueue(new Callback<CartModel>() {
                    @Override
                    public void onResponse(@NotNull Call<CartModel> call, @NotNull Response<CartModel> response) {
                        cart.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NotNull Call<CartModel> call, @NotNull Throwable t) {
                        cart.setValue(new CartModel());
                    }
                });
        return cart;
    }

    public static void deleteCart(){
        cartService.deleteCart().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    cart.setValue(new CartModel());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
