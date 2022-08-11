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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    TextView caTvLogin;
    EditText caGmail, caPassword, caPassword2;
    ProgressBar caProgress;
    Button caBtnSignUp;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        caTvLogin = findViewById(R.id.caTvLogin);
        caBtnSignUp = findViewById(R.id.caBtnSignUp);
        caGmail = findViewById(R.id.caGmail);
        caPassword = findViewById(R.id.caPass);
        caPassword2 = findViewById(R.id.caPass2);
        caProgress = findViewById(R.id.caProgress);
        caProgress.setVisibility(View.INVISIBLE);

        caTvLogin.setOnClickListener(this);
        caBtnSignUp.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.caTvLogin:
                startActivity(new Intent(CreateAccount.this, MainActivity.class));
                break;

            case R.id.caBtnSignUp:
                registration();
                break;
        }
    }

    private void registration() {
        String gmail, password, password2;
        gmail = caGmail.getText().toString().trim();
        password = caPassword.getText().toString().trim();
        password2 = caPassword2.getText().toString().trim();

        if (gmail.isEmpty())
        {
            caGmail.setError("Enter Gmail");
            caGmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(gmail).matches())
        {
            caGmail.setError("Enter valid mail");
            caGmail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            caPassword.setError("Enter Password");
            caPassword.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            caPassword.setError("Minimum 6 length pass");
            caPassword.requestFocus();
            return;
        }

        if (password2.isEmpty())
        {
            caPassword2.setError("Retype Password");
            caPassword2.requestFocus();
            return;
        }

        if (!password.matches(password2))
        {
            caPassword2.setError("Password dont match");
            caPassword2.requestFocus();
            return;
        }

        caProgress.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(gmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                caProgress.setVisibility(View.INVISIBLE);
                if (task.isSuccessful())
                {
                    Toast.makeText(CreateAccount.this, "Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Catalog.class));
                }

                else
                {
                    Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //registration
    }


}