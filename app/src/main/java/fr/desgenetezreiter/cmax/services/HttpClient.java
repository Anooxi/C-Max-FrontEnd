package fr.desgenetezreiter.cmax.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    private static final String BASE_URL = "https://apeat.dorpax.io/v1/";
    private static HttpClient INSTANCE;
    private static Retrofit retrofit;

    private static AuthService authService;
    private static RestaurantService restaurantService;

    private HttpClient(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        OkHttpClient client = okHttpBuilder
                .addInterceptor(getInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpClient getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new HttpClient();
        }
        return INSTANCE;
    }

    public AuthService getAuthService(){
        if(authService == null) {
            authService = retrofit.create(AuthService.class);
        }
        return authService;
    }
    public RestaurantService getRestaurantService(){
        if(restaurantService == null){
            restaurantService = retrofit.create(RestaurantService.class);
        }
        return restaurantService;
    }

    public HttpLoggingInterceptor getInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
