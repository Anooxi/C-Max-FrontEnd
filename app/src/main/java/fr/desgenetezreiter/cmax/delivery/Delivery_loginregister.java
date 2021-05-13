package fr.desgenetezreiter.cmax.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.client.Client_login;
import fr.desgenetezreiter.cmax.client.Client_register;

public class Delivery_loginregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_loginregister);

        Button login = (Button) findViewById(R.id.delivery_login);
        Button register = (Button) findViewById(R.id.delivery_register);
        ArrayList<Button> liste = new ArrayList<>();
        liste.add(login);
        liste.add(register);
        // -----------------
        // ----- STYLE -----
        // -----------------
        for (Button b : liste){
            b.setBackgroundColor(getResources().getColor(R.color.primary));
            b.setTextColor(getResources().getColor(R.color.white));
        }
        // -------------------
        // ----- ONCLICK -----
        // -------------------
        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, Delivery_login.class);
            startActivity(intent);
        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, Delivery_register.class);
            startActivity(intent);
        });
    }
}