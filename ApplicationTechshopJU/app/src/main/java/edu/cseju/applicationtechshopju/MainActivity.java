package edu.cseju.applicationtechshopju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button lgBtnSignIn;
    EditText lgEdtMail, lgEdtPass;
    TextView lgTvCreateAcc;
    ProgressBar lgProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lgTvCreateAcc = findViewById(R.id.lgTvCreateAcc);
        lgEdtMail = findViewById(R.id.lgEdtMail);
        lgEdtPass = findViewById(R.id.lgEdtPassword);
        lgBtnSignIn = findViewById(R.id.lgBtnSignIn);
        lgProgress = findViewById(R.id.lgProgress);
        lgProgress.setVisibility(View.INVISIBLE);

        lgTvCreateAcc.setOnClickListener(this);
        lgBtnSignIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lgBtnSignIn:
                lgProgress.setVisibility(View.VISIBLE);
                break;

            case R.id.lgTvCreateAcc:
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
                break;
        }
    }
}



