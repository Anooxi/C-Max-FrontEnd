package fr.desgenetezreiter.cmax.delivery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.UserViewModelInstance;

public class Delivery_frag_logout extends Fragment {

    public Delivery_frag_logout() {
    }

    public static Delivery_frag_logout newInstance(String param1, String param2) {
        return new Delivery_frag_logout();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delivery_frag_logout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().finish();
        UserViewModelInstance.getInstance().setCurrentUser(null);
    }
}