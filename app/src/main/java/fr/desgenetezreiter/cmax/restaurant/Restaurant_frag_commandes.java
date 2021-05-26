package fr.desgenetezreiter.cmax.restaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.adapters.OrderAdapter;
import fr.desgenetezreiter.cmax.adapters.RecycleViewOnClickListener;
import fr.desgenetezreiter.cmax.models.OrderViewModel;


public class Restaurant_frag_commandes extends Fragment implements RecycleViewOnClickListener {

    private View view;
    private RecyclerView recyclerView;

    public Restaurant_frag_commandes() {
    }


    public static Restaurant_frag_commandes newInstance(String param1, String param2) {
        return new Restaurant_frag_commandes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restaurant_frag_commandes, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.restaurant_frag_commandes_rv);
        OrderViewModel.getOrders();
        OrderViewModel.getPendingOrders().observe(getViewLifecycleOwner(), orderResults -> {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new OrderAdapter(getContext(),orderResults,this));
        });
    }

    @Override
    public void onItemClick(int position) {
        OrderViewModel.nextStatus(((OrderAdapter) recyclerView.getAdapter()).getOrder(position).get_id())
                .observe(getViewLifecycleOwner(), modified -> OrderViewModel.getOrders());
    }
}
