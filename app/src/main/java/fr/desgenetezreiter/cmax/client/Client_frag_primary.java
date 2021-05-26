package fr.desgenetezreiter.cmax.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import fr.desgenetezreiter.cmax.GlobalMethods;
import fr.desgenetezreiter.cmax.MainActivity;
import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.adapters.RestaurantAdapter;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.RestaurantViewModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

import java.util.ArrayList;

public class Client_frag_primary extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private RestaurantViewModel restaurantViewModel;

    private RecyclerView recyclerView;

    private ArrayList<UserModel> restaurants = new ArrayList<>();


    public Client_frag_primary() {
    }

    public static Client_frag_primary newInstance() {
        return new Client_frag_primary();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_client_frag_primary, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        recyclerView = view.findViewById(R.id.client_frag_primary_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RestaurantAdapter(context, restaurants, this));

        AuthResult currentUser = userViewModel.getCurrentUser().getValue();
        if (currentUser == null) {
            Intent intent = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        restaurantViewModel.getRestaurants(currentUser.token, "");

        // récupère la liste des restaurants
        restaurantViewModel.getRestaurantList().observe(getViewLifecycleOwner(), res -> {
            int status = restaurantViewModel.getStatus();
            if (status == GlobalMethods.SUCCESS) {
                restaurants = res;
                recyclerView.setAdapter(new RestaurantAdapter(context, restaurants, this));
            } else if (status == GlobalMethods.FAILURE) {
                Snackbar.make(context, view, getString(R.string.unauth), Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(context, view, getString(R.string.err), Snackbar.LENGTH_LONG).show();
            }
        });


    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Burger");
        categories.add("Sushi");
        categories.add("Pizza");
        categories.add("Ramen");
        categories.add("Alcohol");
        categories.add("Vegan");
        categories.add("Tacos");
        return categories;
    }

    @Override
    public void onItemClick(int position) {
        restaurantViewModel.setCurrentRestaurant(restaurants.get(position));
        Navigation.findNavController(view).navigate(R.id.action_client_frag_primary_to_client_frag_restaurant_details);
    }


}
