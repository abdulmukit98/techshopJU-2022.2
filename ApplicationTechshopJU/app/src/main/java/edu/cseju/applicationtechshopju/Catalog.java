package edu.cseju.applicationtechshopju;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.cseju.applicationtechshopju.model.ISync;
import edu.cseju.applicationtechshopju.model.Product;

public class Catalog extends AppCompatActivity {

    RecyclerView catRecyclar;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        catRecyclar = findViewById(R.id.recyclar);

        ReadData(new ISync() {
            @Override
            public void CaptureData(List<Product> productList) {
                RecyclarAdaptar recyclarAdaptar = new RecyclarAdaptar(productList, getApplicationContext());
                catRecyclar.setAdapter(recyclarAdaptar);
            }
        });


    }

    public void ReadData(ISync iSync)
    {
        List<Product> productList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("product");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                   Product product = dataSnapshot.getValue(Product.class);
                   productList.add(product);
                }

                iSync.CaptureData(productList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Catalog.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}