package edu.cseju.applicationtechshopju;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import edu.cseju.applicationtechshopju.model.Product;

public class Admin extends AppCompatActivity implements View.OnClickListener {

    EditText adProductName, adProductPrice;
    Button adChoose, adSubmit;
    ProgressBar adProgress;
    ImageView adImage;
    Uri imageUri;

    private static int PICK_IMAGE_REQUEST = 5;

    DatabaseReference databaseReference;
    StorageReference storageReference;

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

        databaseReference = FirebaseDatabase.getInstance().getReference("product");
        storageReference = FirebaseStorage.getInstance().getReference("product");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.adChoose:
                chooseImage();
                break;
            case R.id.adSubmit:
                submitData();
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

    private void submitData()
    {
        String name = adProductName.getText().toString().trim();
        String price = adProductPrice.getText().toString().trim();
        String key = databaseReference.push().getKey();

        adProgress.setVisibility(View.VISIBLE);
        String type = getFileExtension(imageUri);
        storageReference.child(key + "." + type).putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageLink = uri.toString();
                                Product product = new Product(key, name, price, imageLink);
                                databaseReference.child(key).setValue(product)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Admin.this, "Success", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                adProgress.setVisibility(View.INVISIBLE);
                            }
                        });
                        

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin.this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        adProgress.setVisibility(View.INVISIBLE);
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuAdminCat:
                startActivity(new Intent(getApplicationContext(), Catalog.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}