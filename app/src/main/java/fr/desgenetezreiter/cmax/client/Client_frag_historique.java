package fr.desgenetezreiter.cmax.client;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.OrderAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.models.OrderViewModel;

public class Client_frag_historique extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private Context context;

    private RecyclerView rv1;
    private RecyclerView rv2;

    public Client_frag_historique() {
    }

    public static Client_frag_historique newInstance(String param1, String param2) {
        return new Client_frag_historique();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_client_frag_historique, container, false);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv1 = view.findViewById(R.id.client_frag_historique_rv1);
        rv2 = view.findViewById(R.id.client_frag_historique_rv2);

        OrderViewModel.getOrders();
        OrderViewModel.getPendingOrders().observe(getViewLifecycleOwner(), pending -> {
            if(pending != null){
                rv1.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                rv1.setAdapter(new OrderAdapter(context,pending,this));
            }
        });
        OrderViewModel.getPreviousOrders().observe(getViewLifecycleOwner(), closed -> {
            if(closed != null){
                rv1.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                rv1.setAdapter(new OrderAdapter(context,closed,this));
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}