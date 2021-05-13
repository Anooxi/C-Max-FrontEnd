package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.LoginModel;

import fr.desgenetezreiter.cmax.models.RegisterModel;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.PUT;


public interface AuthService {
    @POST("auth")
    Call<AuthResult> login(@Body LoginModel body);

    @PUT("auth")
    Call<AuthResult> register(@Body RegisterModel body);

}
