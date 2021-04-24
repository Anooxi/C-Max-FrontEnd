package fr.desgenetezreiter.cmax.restaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.desgenetezreiter.cmax.R;

public class Restaurant_frag_historique extends Fragment {

    private View view;

    public Restaurant_frag_historique() {
    }

    public static Restaurant_frag_historique newInstance(String param1, String param2) {
        return new Restaurant_frag_historique();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restaurant_frag_historique, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}