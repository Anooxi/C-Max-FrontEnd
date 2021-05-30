package fr.desgenetezreiter.cmax.client;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.CartViewModel;
import fr.desgenetezreiter.cmax.models.CreditCardModel;
import fr.desgenetezreiter.cmax.models.OrderModel;
import fr.desgenetezreiter.cmax.models.OrderViewModel;
import fr.desgenetezreiter.cmax.models.UserModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Client_frag_paiement extends Fragment {

    private View view;
    private Context context;

    private UserViewModel userViewModel;

    private TextInputLayout card;
    private TextInputLayout exp;
    private TextInputLayout csv;
    private MaterialButton button;

    private AuthResult currentUser;

    public Client_frag_paiement() {
    }

    public static Client_frag_paiement newInstance() {
        return new Client_frag_paiement();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_client_frag_paiement, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        currentUser = userViewModel.getCurrentUser().getValue();

        card = view.findViewById(R.id.client_frag_paiement_cb);
        exp = view.findViewById(R.id.client_frag_paiement_exp);
        csv = view.findViewById(R.id.client_frag_paiement_csv);
        button = view.findViewById(R.id.client_frag_paiement_button);

        button.setOnClickListener(v -> {
            CreditCardModel creditCardModel = new CreditCardModel(
                    card.getEditText().getText().toString(),
                    exp.getEditText().getText().toString(),
                    csv.getEditText().getText().toString());
            OrderModel orderModel = new OrderModel(CartViewModel.getCart().getValue().getClientId(),creditCardModel);
            OrderViewModel.postOrder(orderModel);

        });

        OrderViewModel.getSuccess().observe(getViewLifecycleOwner(),aBoolean -> {
            if(true){
                CartViewModel.deleteCart();
                Snackbar.make(view,"Ordre payé",Snackbar.LENGTH_SHORT).show();
                Navigation.findNavController(view).popBackStack();
            } else {
                CartViewModel.deleteCart();
                Snackbar.make(view,"Ordre payé",Snackbar.LENGTH_SHORT).show();
                Navigation.findNavController(view).popBackStack();
            }
        });
    }
}