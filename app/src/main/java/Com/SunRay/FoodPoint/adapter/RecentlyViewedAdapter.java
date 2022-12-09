package Com.SunRay.FoodPoint.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Com.SunRay.FoodPoint.ProductPay;
import Com.SunRay.FoodPoint.R;
import Com.SunRay.FoodPoint.model.RecentlyViewed;

public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {

    Context context;
    List<RecentlyViewed> recentlyViewedList;

    public RecentlyViewedAdapter(Context context, List<RecentlyViewed> recentlyViewedList) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_items, parent, false);

        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.name.setText(recentlyViewedList.get(position).getName());
        holder.price.setText(recentlyViewedList.get(position).getPrice());
        holder.qty.setText(recentlyViewedList.get(position).getQuantity());
        holder.unit.setText(recentlyViewedList.get(position).getUnit());
        holder.image.setImageResource(recentlyViewedList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(view -> {
            Intent i=new Intent(context, ProductPay.class);
            i.putExtra("name", recentlyViewedList.get(position).getName());
            i.putExtra("image", recentlyViewedList.get(position).getImageUrl());
            i.putExtra("price",recentlyViewedList.get(position).getPrice());
            i.putExtra("qty",recentlyViewedList.get(position).getQuantity());
            i.putExtra("unit",recentlyViewedList.get(position).getUnit());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public  static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{
        TextView name, price, qty, unit;
        ImageView image;

        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
            unit = itemView.findViewById(R.id.unit);
            image = itemView.findViewById(R.id.product_image);
        }
    }

}
