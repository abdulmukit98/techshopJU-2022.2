package edu.cseju.applicationtechshopju;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.cseju.applicationtechshopju.model.Product;

public class RecycalrViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView riName, riPrice;
    ImageView riImage;
    Context context;
    List<Product> productList;

    public RecycalrViewHolder(@NonNull View itemView, List<Product> productList) {
        super(itemView);

        riName = itemView.findViewById(R.id.riName);
        riPrice = itemView.findViewById(R.id.riPrice);
        riImage = itemView.findViewById(R.id.riImage);

        this.productList = productList;
        context = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, ""+productList.get(getAdapterPosition()).productName, Toast.LENGTH_SHORT).show();
    }


}
