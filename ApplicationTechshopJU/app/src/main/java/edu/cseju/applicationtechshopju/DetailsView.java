package edu.cseju.applicationtechshopju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import edu.cseju.applicationtechshopju.model.Product;

public class DetailsView extends AppCompatActivity implements View.OnClickListener {

    Product product;
    TextView dvProductName,  dvProductPrice;
    ImageView dvImage;
    Button dvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        product = (Product) getIntent().getSerializableExtra("data");

        dvProductName = findViewById(R.id.dvProductName);
        dvProductPrice = findViewById(R.id.dvProductPrice);
        dvImage = findViewById(R.id.dvImage);
        dvOrder = findViewById(R.id.dvOrder);

        dvOrder.setOnClickListener(this);

        dvProductName.setText("Name: " + product.productName);
        dvProductPrice.setText("Price: "+ product.productPrice);
        Picasso.get().load(product.imageLink).fit().into(dvImage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.dvOrder:
                Intent intent = new Intent(getApplicationContext(), OrderProduct.class);
                intent.putExtra("data", product);
                startActivity(intent);
                break;
        }
    }
}