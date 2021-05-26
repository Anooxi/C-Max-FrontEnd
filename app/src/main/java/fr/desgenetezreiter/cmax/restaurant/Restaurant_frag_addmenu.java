package fr.desgenetezreiter.cmax.restaurant;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.MenuAdapter;
import fr.desgenetezreiter.cmax.adapters.ProductAddAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListenerBis;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.MenuModel;
import fr.desgenetezreiter.cmax.models.MenuSend;
import fr.desgenetezreiter.cmax.models.MenuViewModel;
import fr.desgenetezreiter.cmax.models.OrderViewModel;
import fr.desgenetezreiter.cmax.models.ProductModel;
import fr.desgenetezreiter.cmax.models.ProductResult;
import fr.desgenetezreiter.cmax.models.ProductSend;
import fr.desgenetezreiter.cmax.models.RestaurantViewModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Restaurant_frag_addmenu extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private UserViewModel userViewModel;
    private RestaurantViewModel restaurantViewModel;

    private AuthResult currentUser;
    private UserModel currentRestaurant;

    private TextInputLayout title;
    private TextInputLayout description;
    private TextInputLayout url;
    private RecyclerView recyclerView;
    private MaterialButton button;

    private MenuSend newMenu;

    public Restaurant_frag_addmenu() {
    }


    public static Restaurant_frag_addmenu newInstance(String param1, String param2) {
        return new Restaurant_frag_addmenu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_restaurant_frag_addmenu, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        recyclerView = view.findViewById(R.id.restaurant_frag_addmenu_rv);
        title = view.findViewById(R.id.restaurant_frag_addmenu_title);
        description = view.findViewById(R.id.restaurant_frag_addmenu_description);
        url = view.findViewById(R.id.restaurant_frag_addmenu_url);
        button = view.findViewById(R.id.restaurant_frag_addmenu_button);


        currentUser = userViewModel.getCurrentUser().getValue();
        if(currentUser == null){
            Navigation.findNavController(view).popBackStack();
        }
        currentRestaurant = restaurantViewModel.getCurrentRestaurant().getValue();

        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new ProductAddAdapter(context,restaurantViewModel.getCurrentRestaurantsProducts().getValue(),this));

        newMenu = new MenuSend();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMenu.setName(title.getEditText().getText().toString());
                newMenu.setDescription(description.getEditText().getText().toString());
                newMenu.setImg_url(url.getEditText().getText().toString());

                MenuViewModel.postMenu(newMenu);
                Navigation.findNavController(view).popBackStack();

            }
        });

    }

    @Override
    public void onItemClick(int position) {
        ProductSend productSend = new ProductSend();
        ProductModel newProduct = ((ProductAddAdapter) recyclerView.getAdapter()).getProducts().get(position);
        productSend.setPrice(10);
        productSend.setProduct(newProduct.get_id());

        newMenu.addProduct(productSend);
    }
}