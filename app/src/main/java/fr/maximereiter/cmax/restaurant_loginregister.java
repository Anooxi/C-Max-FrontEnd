package fr.maximereiter.cmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class restaurant_loginregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_loginregister);

        Button login = (Button) findViewById(R.id.restaurant_login);
        Button register = (Button) findViewById(R.id.restaurant_register);
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
            Intent intent = new Intent(this,restaurant_login.class);
            startActivity(intent);
        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this,restaurant_register.class);
            startActivity(intent);
        });
    }
}