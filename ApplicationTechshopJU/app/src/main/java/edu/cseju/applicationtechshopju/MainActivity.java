package edu.cseju.applicationtechshopju;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button lgBtnSignIn;
    EditText lgEdtMail, lgEdtPass;
    TextView lgTvCreateAcc;
    ProgressBar lgProgress;

    FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lgBtnSignIn:
                userLogin();
                break;

            case R.id.lgTvCreateAcc:
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
                break;
        }
    }

    private void userLogin() {

        String email, password;
        email = lgEdtMail.getText().toString().trim();
        password = lgEdtPass.getText().toString().trim();

        if (email.isEmpty())
        {
            lgEdtMail.setError("Enter Mail");
            lgEdtMail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            lgEdtMail.setError("Enter valid mail");
            lgEdtMail.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            lgEdtPass.setError("Null Password error");
            lgEdtPass.requestFocus();
            return;
        }
        if (password.length() < 6)
        {
            lgEdtPass.setError("Minimum 6 chracter");
            lgEdtPass.requestFocus();
            return;
        }

        lgProgress.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                lgProgress.setVisibility(View.INVISIBLE);
                if (task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "login", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // userlogin
    }


}



