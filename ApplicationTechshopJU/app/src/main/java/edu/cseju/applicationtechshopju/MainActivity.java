package edu.cseju.applicationtechshopju;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button lgBtnSignIn;
    EditText lgEdtMail, lgEdtPass;
    TextView lgTvCreateAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lgTvCreateAcc = findViewById(R.id.lgTvCreateAcc);
        lgEdtMail = findViewById(R.id.lgEdtMail);
        lgEdtPass = findViewById(R.id.lgEdtPassword);
        lgBtnSignIn = findViewById(R.id.lgBtnSignIn);


    }
}



