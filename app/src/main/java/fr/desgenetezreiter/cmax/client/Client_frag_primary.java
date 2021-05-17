package fr.desgenetezreiter.cmax.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.desgenetezreiter.cmax.GlobalMethods;
import fr.desgenetezreiter.cmax.MainActivity;
import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.RestaurantViewModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Client_frag_primary extends Fragment {

    private View view;
    private Context context;

    private UserViewModel userViewModel;
    private RestaurantViewModel restaurantViewModel;
    private AuthResult currentUser;

    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;

    private ScrollView scrollView;
    private LinearLayout linearLayoutRestaurants;

    private ArrayList<UserModel> restaurants = new ArrayList<>();
    public Client_frag_primary() {
    }

    public static Client_frag_primary newInstance(String param1, String param2) {
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
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        restaurantViewModel = new ViewModelProvider(requireActivity()).get(RestaurantViewModel.class);

        currentUser = userViewModel.getCurrentUser().getValue();
        if(currentUser == null){
            Intent intent = new Intent(context,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        restaurantViewModel.getRestaurants(currentUser.token);
        scrollView = view.findViewById(R.id.client_frag_primary_sv);
        linearLayoutRestaurants = view.findViewById(R.id.client_frag_primary_sv_ll);

        // récupère la liste des restaurants
        restaurantViewModel.getRestaurantList().observe(getViewLifecycleOwner(), res -> {
            int status = restaurantViewModel.getStatus();
            if(status == GlobalMethods.SUCCESS){
                restaurants = res;
                //affiche les restaurants
                for(UserModel restaurant : restaurants){
                    createCard(restaurant);
                }
            } else if(status == GlobalMethods.FAILURE) {
                Snackbar.make(context,view,getString(R.string.unauth),Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(context,view,getString(R.string.err),Snackbar.LENGTH_LONG).show();
            }
        });
        // On met en place la liste de bouton
        horizontalScrollView = view.findViewById(R.id.client_frag_primary_hsv);
        linearLayout = view.findViewById(R.id.client_frag_primary_hsv_ll);
        ArrayList<String> categories = getCategories();
        for (String c: categories ) {
            MaterialButton button = new MaterialButton(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(20,0,20,0);
            button.setLayoutParams(lp);
            button.setTextColor(getResources().getColor(R.color.white, context.getTheme()));
            button.setBackgroundColor(getResources().getColor(R.color.primary, context.getTheme()));
            button.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            button.setText(c);
            button.setOnClickListener(v -> {
                Toast.makeText(context,c,Toast.LENGTH_SHORT).show();
            });
            linearLayout.addView(button);
        }


    }

    private void createCard(UserModel restaurant){
        LinearLayout llMain = new LinearLayout(context);
        llMain.setOrientation(LinearLayout.VERTICAL);

        LinearLayout llText = new LinearLayout(context);
        TextView tv1 = new TextView(context);
        tv1.setText(restaurant.getRestaurant_name());
        tv1.setTextAppearance(R.style.TextAppearance_AppCompat_Headline);
        llText.addView(tv1);

        LinearLayout llBouton = new LinearLayout(context);
        MaterialButton button = new MaterialButton(context);
        button.setText(R.string.command);
        llBouton.addView(button);

        llMain.addView(llText);
        llMain.addView(llBouton);
        linearLayoutRestaurants.addView(llMain);
    }

    public ArrayList<String> getCategories(){
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
}