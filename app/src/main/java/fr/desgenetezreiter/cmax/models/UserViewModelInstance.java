package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.MutableLiveData;

public class UserViewModelInstance {
    private static MutableLiveData<AuthResult> currentUser = new MutableLiveData<>();
    private static UserViewModelInstance INSTANCE;

    public UserViewModelInstance(){}
    public static UserViewModelInstance getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserViewModelInstance();
        }
        return INSTANCE;
    }

    public MutableLiveData<AuthResult> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AuthResult newUser) {
        currentUser.setValue(newUser);
    }
}
