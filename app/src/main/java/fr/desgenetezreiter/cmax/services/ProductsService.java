package fr.desgenetezreiter.cmax.services;

import fr.desgenetezreiter.cmax.models.MenuModel;
import fr.desgenetezreiter.cmax.models.ProductModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductsService {
    @GET("/products")
    Call<ProductModel[]> getProducts();

    @GET("/menus")
    Call<MenuModel[]> getMenus();

    @POST("/menus")
    Call<MenuModel> addMenus();
}
