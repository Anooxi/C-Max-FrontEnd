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

import com.google.android.material.button.MaterialButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.MenuAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.CartViewModel;
import fr.desgenetezreiter.cmax.models.MenuModel;
import fr.desgenetezreiter.cmax.models.RestaurantViewModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Client_frag_panier extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private RecyclerView recyclerView;
    private TextView prix;
    private MaterialButton button;

    private UserViewModel userViewModel;

    private AuthResult currentUser;

    public Client_frag_panier() {
    }

    public static Client_frag_panier newInstance(String param1, String param2) {
        return new Client_frag_panier();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_client_frag_panier, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        recyclerView = view.findViewById(R.id.client_frag_panier_rv);
        prix = view.findViewById(R.id.client_frag_panier_prix);
        button = view.findViewById(R.id.client_frag_panier_button);

        currentUser = userViewModel.getCurrentUser().getValue();
        CartViewModel.getCart().observe(getViewLifecycleOwner(), cart -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new MenuAdapter(context,cart.getMenus(),this));
            if(cart.getMenus() != null){
                prix.setText(cart.getPrixTotal() + "€");
            } else {
                prix.setText("0€");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_client_frag_panier_to_client_frag_paiement);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        //TODO Ouvre une petite fenètre qui montre les produits
    }
}