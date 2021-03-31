package fr.maximereiter.cmax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class restaurant_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);
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
    }
}