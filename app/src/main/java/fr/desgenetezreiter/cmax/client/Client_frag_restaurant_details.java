package fr.desgenetezreiter.cmax.client;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.MenuAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.adapters.RestaurantAdapter;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.RestaurantViewModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Client_frag_restaurant_details extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private UserViewModel userViewModel;
    private RestaurantViewModel restaurantViewModel;

    private UserModel currentRestaurant;
    private AuthResult currentUser;

    private RecyclerView recyclerView;
    private TextView restaurant_name;
    private TextView restaurant_description;

    public Client_frag_restaurant_details() {
    }

    public static Client_frag_restaurant_details newInstance() {
        return new Client_frag_restaurant_details();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_client_frag_restaurant_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        restaurant_name = view.findViewById(R.id.Client_frag_restaurant_details_name);
        restaurant_description = view.findViewById(R.id.Client_frag_restaurant_details_description);
        recyclerView = view.findViewById(R.id.Client_frag_restaurant_details_rv);

        currentUser = userViewModel.getCurrentUser().getValue();
        currentRestaurant = restaurantViewModel.getCurrentRestaurant().getValue();
        if(currentRestaurant == null){
            Navigation.findNavController(view).popBackStack();
        }

        restaurant_name.setText(currentRestaurant.getRestaurant_name());

        restaurantViewModel.getMenus(currentUser.token,currentRestaurant.get_id());
        restaurantViewModel.getCurrentRestaurantMenus().observe(getViewLifecycleOwner(),menus -> {
            if(menus != null){
                recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(new MenuAdapter(context,menus,this));
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Navigation.findNavController(view).navigate(R.id.action_client_frag_restaurant_details_to_client_frag_menu_details);
    }
}