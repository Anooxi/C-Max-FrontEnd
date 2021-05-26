package fr.desgenetezreiter.cmax.models;

import androidx.lifecycle.ViewModel;

import fr.desgenetezreiter.cmax.services.HttpClient;
import fr.desgenetezreiter.cmax.services.MenusService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewModel extends ViewModel {

    public static class MenuBody{
        private MenuSend menu;

        public MenuBody(MenuSend menuSend){
            this.menu = menuSend;
        };
    }

    private static MenusService menusService = HttpClient.getInstance().getMenusService();

    public static void postMenu(MenuSend menuSend){
        MenuBody body = new MenuBody(menuSend);

        menusService.postMenu(body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(call.toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
