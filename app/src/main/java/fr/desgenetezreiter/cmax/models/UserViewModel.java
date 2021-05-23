package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import fr.desgenetezreiter.cmax.services.AuthService;
import fr.desgenetezreiter.cmax.services.HttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class UserViewModel extends ViewModel {
    private AuthService authService = HttpClient.getInstance().getAuthService();
    private UserViewModelInstance userViewModelInstance = UserViewModelInstance.getInstance();
    private MutableLiveData<AuthResult> currentUser;

    public void login(LoginModel loginModel) {
        authService.login(loginModel).enqueue(new Callback<AuthResult>() {
            @Override
            public void onResponse(Call<AuthResult> call, Response<AuthResult> response) {
                authUser(response, AuthResult.LOGIN);
            }

            @Override
            public void onFailure(Call<AuthResult> call, Throwable t) {
                setCurrentUser(new AuthResult(0, -1));
            }
        });
    }

    public void register(RegisterModel registerModel) {
        authService.register(registerModel).enqueue(new Callback<AuthResult>() {
            @Override
            public void onResponse(Call<AuthResult> call, Response<AuthResult> response) {
                authUser(response, AuthResult.REGISTER);
            }

            @Override
            public void onFailure(Call<AuthResult> call, Throwable t) {
                currentUser.setValue(new AuthResult(1, -1));
            }
        });
    }

    private void authUser(Response<AuthResult> response, int type) {
        AuthResult authResult = new AuthResult();
        if (response.isSuccessful()) {
            authResult = response.body();
            authResult.setSuccess(true);
        } else {
            Gson gson = new Gson();
            try {
                authResult = gson.fromJson(response.errorBody().string(), AuthResult.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            authResult.setSuccess(false);
        }
        authResult.setResCode(response.code());
        authResult.setType(type);
        currentUser.setValue(authResult);
    }

    public MutableLiveData<AuthResult> getCurrentUser() {
        if (currentUser == null) {
            currentUser = userViewModelInstance.getCurrentUser();
        }
        return currentUser;
    }

    public void setCurrentUser(AuthResult newUser) {
        userViewModelInstance.setCurrentUser(newUser);
        currentUser = userViewModelInstance.getCurrentUser();
    }
}
