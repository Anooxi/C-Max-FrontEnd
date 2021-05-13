package fr.desgenetezreiter.cmax;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;
import fr.desgenetezreiter.cmax.client.Client_login;
import fr.desgenetezreiter.cmax.client.Client_loginregister;
import fr.desgenetezreiter.cmax.delivery.Delivery_login;
import fr.desgenetezreiter.cmax.delivery.Delivery_loginregister;
import fr.desgenetezreiter.cmax.restaurant.Restaurant_loginregister;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button client = (Button) findViewById(R.id.main_client);
        Button restaurant = (Button) findViewById(R.id.main_restaurant);
        Button delivery = (Button) findViewById(R.id.main_delivery);
        ArrayList<Button> liste = new ArrayList<>();
        liste.add(client);
        liste.add(restaurant);

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
        client.setOnClickListener(v -> {
            Intent intent = new Intent(this, Client_loginregister.class);
            startActivity(intent);
        });

        delivery.setOnClickListener(v -> {
            Intent intent = new Intent(this, Delivery_loginregister.class);
            startActivity(intent);
        });

        restaurant.setOnClickListener(v -> {
            Intent intent = new Intent(this, Restaurant_loginregister.class);
            startActivity(intent);
        });
    }
}