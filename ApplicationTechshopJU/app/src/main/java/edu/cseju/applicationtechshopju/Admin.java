package edu.cseju.applicationtechshopju;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Admin extends AppCompatActivity {

    EditText adProductName, adProductPrice;
    Button adChoose, adSubmit;
    ProgressBar adProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
}