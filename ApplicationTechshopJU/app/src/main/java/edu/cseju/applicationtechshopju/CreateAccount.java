package edu.cseju.applicationtechshopju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    TextView caTvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        caTvLogin = findViewById(R.id.caTvLogin);



        caTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this, MainActivity.class));
            }
        });

    }
}