package edu.cseju.applicationtechshopju;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.cseju.applicationtechshopju.model.Order;
import edu.cseju.applicationtechshopju.model.Product;

public class OrderProduct extends AppCompatActivity implements View.OnClickListener {

    Product product;
    TextView opProductName, opTotalPay;
    EditText opCustomerName, opCustomerPhone, opCustomerAddress, opBkashTxn;
    Button opPlaceOrder;
    ProgressBar opProgress;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);

        product = (Product) getIntent().getSerializableExtra("data");
        Toast.makeText(this, product.productName, Toast.LENGTH_SHORT).show();

        opProductName = findViewById(R.id.opTvProductName);
        opTotalPay = findViewById(R.id.opTotalPay);
        opCustomerName = findViewById(R.id.opCustomerName);
        opCustomerPhone = findViewById(R.id.opCustomerPhone);
        opCustomerAddress = findViewById(R.id.opCustomerAdress);
        opBkashTxn = findViewById(R.id.opBkashTxn);
        opPlaceOrder = findViewById(R.id.opPlaceOrder);
        opProgress = findViewById(R.id.opProgress);

        opPlaceOrder.setOnClickListener(this);
        opProductName.setText("Product: " + product.productName);
        opTotalPay.setText("Price: " + product.productPrice);
        opProgress.setVisibility(View.INVISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("order");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.opPlaceOrder:
                PlaceOrder();
                break;
        }
    }

    private void PlaceOrder() {
        String customerName = opCustomerName.getText().toString().trim();
        String customerPhone = opCustomerPhone.getText().toString().trim();
        String customerAddress = opCustomerAddress.getText().toString().trim();
        String bkshId = opBkashTxn.getText().toString().trim();

        if (customerName.isEmpty())
        {
            opCustomerName.setError("Enter Name");
            opCustomerName.requestFocus();
            return;
        }
        if (customerPhone.isEmpty())
        {
            opCustomerPhone.setError("Enter Phone");
            opCustomerPhone.requestFocus();
            return;
        }
        if (customerAddress.isEmpty())
        {
            opCustomerAddress.setError("Enter Address");
            opCustomerAddress.requestFocus();
            return;
        }
        if (bkshId.isEmpty())
        {
            opBkashTxn.setError("Enter TxN");
            opBkashTxn.requestFocus();
            return;
        }

        String key = databaseReference.push().getKey();

        Order order = new Order(key, product.productId, customerName, customerPhone, customerAddress, bkshId);

        opProgress.setVisibility(View.VISIBLE);
        databaseReference.child(key).setValue(order)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        opProgress.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(getApplicationContext(), Catalog.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }



}