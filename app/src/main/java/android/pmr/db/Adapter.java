package android.pmr.db;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.pmr.MainActivity;
import android.pmr.R;
import android.pmr.ShowActivity;
import android.pmr.entities.Clothes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ClothesViewHolder> {

    private ArrayList<Clothes> arrayList;

    public Adapter(ArrayList<Clothes> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ClothesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
        return new ClothesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesViewHolder holder, int position) {
        holder.shopName.setText(arrayList.get(position).getShopName());
        holder.desc.setText(arrayList.get(position).getDescription());
        holder.price.setText(arrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder {

        TextView shopName, desc, price;

        public ClothesViewHolder(@NonNull View itemView) {
            super(itemView);

            shopName = itemView.findViewById(R.id.viewShopName);
            desc = itemView.findViewById(R.id.viewDesc);
            price = itemView.findViewById(R.id.viewPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ShowActivity.class);
                    intent.putExtra("ID", arrayList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
