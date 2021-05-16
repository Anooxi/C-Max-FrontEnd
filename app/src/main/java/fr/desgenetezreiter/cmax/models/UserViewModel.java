package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;

import fr.desgenetezreiter.cmax.services.AuthService;
import fr.desgenetezreiter.cmax.services.HttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private AuthService authService = HttpClient.getInstance().getAuthService();
    private UserViewModelInstance userViewModelInstance = UserViewModelInstance.getInstance();
    private MutableLiveData<AuthResult> currentUser;

    public void login(LoginModel loginModel){
        authService.login(loginModel).enqueue(new Callback<AuthResult>() {
            @Override
            public void onResponse(Call<AuthResult> call, Response<AuthResult> response) {
                AuthResult authResult = new AuthResult();
                if(response.isSuccessful()){
                    authResult = response.body();
                    authResult.setSuccess(true);
                } else {
                    Gson gson = new Gson();
                    try {
                        authResult = gson.fromJson(response.errorBody().string(),AuthResult.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    authResult.setSuccess(false);
                }
                authResult.setType(AuthResult.LOGIN);
                authResult.setResCode(response.code());
                userViewModelInstance.setCurrentUser(authResult);
            }

            @Override
            public void onFailure(Call<AuthResult> call, Throwable t) {
                userViewModelInstance.setCurrentUser(new AuthResult(0,-1));
            }
        });
    }

    public void register(RegisterModel registerModel){
        authService.register(registerModel).enqueue(new Callback<AuthResult>() {
            @Override
            public void onResponse(Call<AuthResult> call, Response<AuthResult> response) {
                AuthResult authResult = new AuthResult();
                if(response.isSuccessful()){
                    authResult = response.body();
                    authResult.setSuccess(true);
                } else {
                    Gson gson = new Gson();
                    try {
                        authResult = gson.fromJson(response.errorBody().string(),AuthResult.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    authResult.setSuccess(false);
                }
                authResult.setResCode(response.code());
                authResult.setType(AuthResult.REGISTER);
                currentUser.setValue(authResult);
            }

            @Override
            public void onFailure(Call<AuthResult> call, Throwable t) {
                currentUser.setValue(new AuthResult(1,-1));
            }
        });
    }

    public MutableLiveData<AuthResult> getCurrentUser() {
        if(currentUser == null){
            currentUser = userViewModelInstance.getCurrentUser();
        }
        return currentUser;
    }

    public void setCurrentUser(AuthResult newUser) {
        userViewModelInstance.setCurrentUser(newUser);
        currentUser = userViewModelInstance.getCurrentUser();
    }
}
