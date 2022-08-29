package edu.cseju.applicationtechshopju;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.cseju.applicationtechshopju.model.Product;

public class RecyclarAdaptar extends RecyclerView.Adapter<RecycalrViewHolder> {

    List<Product> productList;
    Context context;

    public RecyclarAdaptar(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycalrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclar_item, parent, false);

        RecycalrViewHolder recycalrViewHolder = new RecycalrViewHolder(view, productList);
        return recycalrViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycalrViewHolder holder, int position) {
        holder.riName.setText(productList.get(position).productName);
        holder.riPrice.setText(productList.get(position).productPrice);
        Picasso.get().load(productList.get(position).imageLink).fit().into(holder.riImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
