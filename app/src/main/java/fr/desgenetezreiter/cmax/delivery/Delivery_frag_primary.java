package fr.desgenetezreiter.cmax.delivery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.desgenetezreiter.cmax.MainActivity;
import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.OrderAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.models.AuthResult;
import fr.desgenetezreiter.cmax.models.OrderViewModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Delivery_frag_primary extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private UserViewModel userViewModel;
    private AuthResult currentUser;

    private RecyclerView recyclerView;

    private Handler handler;
    private Runnable runnable;

    public Delivery_frag_primary() {
    }

    public static Delivery_frag_primary newInstance(String param1, String param2) {
        return new Delivery_frag_primary();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivery_frag_primary, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        currentUser = userViewModel.getCurrentUser().getValue();
        recyclerView = view.findViewById(R.id.delivery_frag_primary_rv);
        if(currentUser == null){
            Intent intent = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        OrderViewModel.getOrders();
        OrderViewModel.getPendingOrders().observe(getViewLifecycleOwner(),orderResults -> {
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new OrderAdapter(context,orderResults,this));
        });

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,15000);
                OrderViewModel.getOrders();
            }
        }, 0);
    }

    @Override
    public void onItemClick(int position) {
    }
}
