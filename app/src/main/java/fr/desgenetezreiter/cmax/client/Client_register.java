package fr.desgenetezreiter.cmax.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.AddressModel;
import fr.desgenetezreiter.cmax.models.LoginModel;
import fr.desgenetezreiter.cmax.models.RegisterModel;
import fr.desgenetezreiter.cmax.models.UserViewModel;
import fr.desgenetezreiter.cmax.restaurant.Restaurant_primary;

public class Client_register extends AppCompatActivity {

    private TextInputLayout TILemail;
    private TextInputLayout TILpassword;
    private TextInputLayout TILlastname;
    private TextInputLayout TILfirstname;
    private TextInputLayout TILphone;
    private TextInputLayout TILstreet;
    private TextInputLayout TILcity;
    private TextInputLayout TILzip;
    private TextInputLayout TILcountry;

    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register);
        Button confirm = (Button) findViewById(R.id.confirm_button);
        ArrayList<Button> liste = new ArrayList<>();
        liste.add(confirm);

        TILemail = findViewById(R.id.client_register_mail);
        TILpassword = findViewById(R.id.client_register_password);
        TILlastname = findViewById(R.id.client_register_lastname);
        TILfirstname = findViewById(R.id.client_register_firstname);
        TILphone = findViewById(R.id.client_register_phone);
        TILstreet = findViewById(R.id.client_register_street);
        TILcity = findViewById(R.id.client_register_city);
        TILzip = findViewById(R.id.client_register_postal);
        TILcountry = findViewById(R.id.client_register_country);
        userViewModel = new UserViewModel();
        // -----------------
        // ----- STYLE -----
        // -----------------
        for (Button b : liste){
            b.setBackgroundColor(getResources().getColor(R.color.primary));
            b.setTextColor(getResources().getColor(R.color.white));
        }

        confirm.setOnClickListener(v -> {
            register();
        });

        userViewModel.getCurrentUser().observe(this, res -> {
            if(res != null){
                if(res.success){
                    startActivity(new Intent(this,Client_primary.class));
                    finish();
                } else {
                    Snackbar.make(confirm.getRootView(),res.getResCode() + ":" + res.getMessage(),Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.design_default_color_error,getTheme()))
                            .show();
                }
            }
        });
    }
    public void register(){
        String email = TILemail.getEditText().getText().toString();
        String password = TILpassword.getEditText().getText().toString();
        String last_name = TILlastname.getEditText().getText().toString();
        String first_name = TILfirstname.getEditText().getText().toString();
        String phone = TILphone.getEditText().getText().toString();
        AddressModel addressModel = new AddressModel();
        addressModel.setFullAddress(
                TILstreet.getEditText().getText().toString(),
                TILcity.getEditText().getText().toString(),
                TILzip.getEditText().getText().toString(),
                TILcountry.getEditText().getText().toString());

        RegisterModel registerModel = new RegisterModel(email,password,first_name,last_name,phone,addressModel,RegisterModel.TYPE_CLIENT);

        userViewModel.register(registerModel);
    }
}