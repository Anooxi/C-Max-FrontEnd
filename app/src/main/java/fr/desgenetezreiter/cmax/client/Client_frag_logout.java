package fr.desgenetezreiter.cmax.client;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.desgenetezreiter.cmax.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Client_frag_logout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Client_frag_logout extends Fragment {

    public Client_frag_logout() {
    }

    public static Client_frag_logout newInstance() {
        return new Client_frag_logout();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client_frag_logout, container, false);
    }
}
