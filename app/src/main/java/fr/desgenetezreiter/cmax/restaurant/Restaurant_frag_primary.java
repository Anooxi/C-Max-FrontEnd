package fr.desgenetezreiter.cmax.restaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.desgenetezreiter.cmax.MainActivity;
import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.MenuAdapter;
import fr.desgenetezreiter.cmax.adapters.ProductAdapter;
import fr.desgenetezreiter.cmax.adapters.ProductMAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.RestaurantViewModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;


public class Restaurant_frag_primary extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private UserViewModel userViewModel;
    private RestaurantViewModel restaurantViewModel;

    private AuthResult currentUser;
    private UserModel currentRestaurant;

    private RecyclerView recyclerViewMenu;
    private RecyclerView recyclerViewProduct;

    public Restaurant_frag_primary() {
    }

    public static Restaurant_frag_primary newInstance(String param1, String param2) {
        return new Restaurant_frag_primary();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restaurant_frag_primary, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        currentUser = userViewModel.getCurrentUser().getValue();
        if(currentUser == null){
            Intent intent = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        currentRestaurant = currentUser.getUser();

        if(currentRestaurant == null){
            Intent intent = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        restaurantViewModel.getMenus(currentUser.token, currentRestaurant.get_id());
        restaurantViewModel.getProducts(currentUser.token, currentRestaurant.get_id());

        recyclerViewMenu = view.findViewById(R.id.restaurant_frag_primary_rvm);
        recyclerViewProduct = view.findViewById(R.id.restaurant_frag_primary_rvp);



        restaurantViewModel.getCurrentRestaurantMenus().observe(getViewLifecycleOwner(),menus -> {
            if(menus != null){
                recyclerViewMenu.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                recyclerViewMenu.setAdapter(new MenuAdapter(context,menus,this));
            }
        });

        restaurantViewModel.getCurrentRestaurantsProducts().observe(getViewLifecycleOwner(), products -> {
            if(products != null){
                recyclerViewProduct.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                recyclerViewProduct.setAdapter(new ProductMAdapter(context,products,this));
            }
        });

    }

    @Override
    public void onItemClick(int position) {

    }
}