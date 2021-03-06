package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModelInstance;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class HttpClient {
    private static final String BASE_URL = "https://apeat.dorpax.io/v1/";
    private static HttpClient INSTANCE;
    private static Retrofit retrofit;

    private static AuthService authService;
    private static RestaurantService restaurantService;
    private static CartService cartService;
    private static OrdersService ordersService;
    private static MenusService menusService;

    private HttpClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        OkHttpClient client = okHttpBuilder
                .addInterceptor(getInterceptor())
                .addInterceptor(authorizationInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpClient();
        }
        return INSTANCE;
    }

    public MenusService getMenusService(){
        if (menusService == null){
            menusService = retrofit.create(MenusService.class);
        }
        return menusService;
    }

    public OrdersService getOrdersService(){
        if (ordersService == null){
            ordersService = retrofit.create(OrdersService.class);
        }
        return ordersService;
    }

    public AuthService getAuthService() {
        if (authService == null) {
            authService = retrofit.create(AuthService.class);
        }
        return authService;
    }

    public RestaurantService getRestaurantService() {
        if (restaurantService == null) {
            restaurantService = retrofit.create(RestaurantService.class);
        }
        return restaurantService;
    }

    public CartService getCartService() {
        if(cartService == null) {
            cartService = retrofit.create(CartService.class);
        }
        return cartService;
    }

    public HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private Interceptor authorizationInterceptor() {
        return chain -> {
            UserViewModelInstance userViewModelInstance = UserViewModelInstance.getInstance();
            AuthResult authResult = userViewModelInstance.getCurrentUser().getValue();
            if(authResult == null) return chain.proceed(chain.request());

            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + authResult.token)
                    .build();
            return chain.proceed(newRequest);
        };
    }
}
