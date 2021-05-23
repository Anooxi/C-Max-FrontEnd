package fr.desgenetezreiter.cmax.client;

import android.content.Context;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.desgenetezreiter.cmax.models.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.ProductAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;

public class Client_frag_menu_details extends Fragment implements RecycleViewOnClickListener, View.OnClickListener {

    private Context context;
    private View view;

    private UserViewModel userViewModel;
    private RestaurantViewModel restaurantViewModel;

    private UserModel currentRestaurant;
    private AuthResult currentUser;
    private MenuModel currentMenu;

    private RecyclerView recyclerView;
    private TextView menu_name;
    private TextView menu_description;
    private ImageView menu_image;
    private TextView menu_price;

    public Client_frag_menu_details() {
    }

    public static Client_frag_menu_details newInstance() {
        return new Client_frag_menu_details();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_client_frag_menu_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        menu_name = view.findViewById(R.id.Client_frag_menu_details_name);
        menu_description = view.findViewById(R.id.Client_frag_menu_details_description);
        menu_image = view.findViewById(R.id.Client_frag_menu_details_iv);
        recyclerView = view.findViewById(R.id.Client_frag_menu_details_rv);
        menu_price = view.findViewById(R.id.Client_frag_menu_details_prix);

        Button addInCartButton = view.findViewById(R.id.Client_frag_menu_details_button);
        addInCartButton.setOnClickListener(this);

        currentUser = userViewModel.getCurrentUser().getValue();
        currentRestaurant = restaurantViewModel.getCurrentRestaurant().getValue();
        if (currentRestaurant == null) {
            Navigation.findNavController(view).popBackStack();
        }
        currentMenu = restaurantViewModel.getCurrentRestaurantMenus().getValue().get(getArguments().getInt("POS"));

        menu_name.setText(currentMenu.getName());
        menu_description.setText(currentMenu.getDescription());
        Picasso.get().load(currentMenu.getImg_url())
                .resize(120, 120)
                .error(R.drawable.app_logo)
                .into(menu_image);
        menu_price.setText(getTotalPrice(currentMenu.getProducts()) + "€");

        restaurantViewModel.getProducts(currentUser.token, currentRestaurant.get_id());
        restaurantViewModel.getCurrentRestaurantsProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(new ProductAdapter(context, currentMenu.getProducts(), this));
            }
        });
    }

    public double getTotalPrice(ArrayList<ProductResult> products) {
        double tot = 0;
        for (ProductResult product : products) {
            tot += product.getPrice();
        }
        return tot;
    }

    @Override
    public void onItemClick(int position) {

    }

    public void addInCart() {
        CartViewModel.addInCart(currentMenu.get_id(), currentRestaurant.get_id())
                .observe(this, System.out::println);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Client_frag_menu_details_button) {
            addInCart();
        }
    }
}
