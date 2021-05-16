package fr.desgenetezreiter.cmax.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.client.Client_primary;
import fr.desgenetezreiter.cmax.models.LoginModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;

public class Delivery_login extends AppCompatActivity {
    private UserViewModel userViewModel;

    private TextInputLayout TILemail;
    private TextInputLayout TILpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);

        Button confirm = (Button) findViewById(R.id.confirm_button);
        ArrayList<Button> liste = new ArrayList<>();
        liste.add(confirm);

        TILemail = findViewById(R.id.delivery_email);
        TILpassword = findViewById(R.id.delivery_password);
        userViewModel = new UserViewModel();

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
        confirm.setOnClickListener(v -> {
            login();
        });

        userViewModel.getCurrentUser().observe(this,res -> {
            if(res != null){
                if(res.success){
                    startActivity(new Intent(this,Delivery_primary.class));
                    finish();
                } else {
                    Snackbar.make(confirm.getRootView(),res.getResCode() + ":" + res.getMessage(),Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.design_default_color_error,getTheme()))
                            .show();
                }
            }
        });

    }
    private void login(){
        String email = TILemail.getEditText().getText().toString();
        String password = TILpassword.getEditText().getText().toString();
        LoginModel loginModel = new LoginModel(email,password);
        //check if fields empty
        userViewModel.login(loginModel);
    }
}