package edu.cseju.applicationtechshopju;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycalrViewHolder extends RecyclerView.ViewHolder {

    TextView riName, riPrice;
    ImageView riImage;

    public RecycalrViewHolder(@NonNull View itemView) {
        super(itemView);

        riName = itemView.findViewById(R.id.riName);
        riPrice = itemView.findViewById(R.id.riPrice);
        riImage = itemView.findViewById(R.id.riImage);
    }
}
