package fr.desgenetezreiter.cmax.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.client.Client_primary;

public class Delivery_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_register);

        Button confirm = (Button) findViewById(R.id.confirm_button);
        ArrayList<Button> liste = new ArrayList<>();
        liste.add(confirm);
        // -----------------
        // ----- STYLE -----
        // -----------------
        for (Button b : liste){
            b.setBackgroundColor(getResources().getColor(R.color.primary));
            b.setTextColor(getResources().getColor(R.color.white));
        }

        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(this, Delivery_primary.class);
            startActivity(intent);
        });
    }
}