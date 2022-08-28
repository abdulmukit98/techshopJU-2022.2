package edu.cseju.applicationtechshopju;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class Admin extends AppCompatActivity implements View.OnClickListener {

    EditText adProductName, adProductPrice;
    Button adChoose, adSubmit;
    ProgressBar adProgress;
    ImageView adImage;
    Uri imageUri;

    private static int PICK_IMAGE_REQUEST = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adProductName = findViewById(R.id.adName);
        adProductPrice = findViewById(R.id.adPrice);
        adImage = findViewById(R.id.adImage);
        adChoose = findViewById(R.id.adChoose);
        adSubmit = findViewById(R.id.adSubmit);
        adProgress = findViewById(R.id.adProgress);

        adChoose.setOnClickListener(this);
        adSubmit.setOnClickListener(this);
        adProgress.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.adChoose:
                chooseImage();
                break;
            case R.id.adSubmit:

                break;
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
            data != null && data.getData() != null)
        {
            imageUri = data.getData();
            Picasso.get().load(imageUri).fit().into(adImage);
        }
    }

    private String getFileExtension(Uri imageUri)
    {
        ContentResolver contentResolver = getContentResolver();
        String extn = contentResolver.getType(imageUri);
        return extn.substring(extn.indexOf('/') + 1);
    }



}