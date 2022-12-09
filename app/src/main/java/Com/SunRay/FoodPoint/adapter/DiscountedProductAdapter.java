package Com.SunRay.FoodPoint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Com.SunRay.FoodPoint.ProductPay;
import Com.SunRay.FoodPoint.R;
import Com.SunRay.FoodPoint.model.DiscountedProducts;

public class DiscountedProductAdapter extends RecyclerView.Adapter<DiscountedProductAdapter.DiscountedProductViewHolder> {

    Context context;
    List<DiscountedProducts> discountedProductsList;

    public DiscountedProductAdapter(Context context, List<DiscountedProducts> discountedProductsList) {
        this.context = context;
        this.discountedProductsList = discountedProductsList;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.discounted_row_items, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {

        holder.name.setText(discountedProductsList.get(position).getName());
        holder.price.setText(discountedProductsList.get(position).getPrice());
        holder.qty.setText(discountedProductsList.get(position).getQuantity());
        holder.unit.setText(discountedProductsList.get(position).getUnit());
        holder.discount.setText(discountedProductsList.get(position).getDiscount());

        holder.image.setImageResource(discountedProductsList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(view -> {
            Intent i=new Intent(context, ProductPay.class);
            i.putExtra("name", discountedProductsList.get(position).getName());
            i.putExtra("image", discountedProductsList.get(position).getImageUrl());
            i.putExtra("price",discountedProductsList.get(position).getPrice());
            i.putExtra("qty",discountedProductsList.get(position).getQuantity());
            i.putExtra("unit",discountedProductsList.get(position).getUnit());
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return discountedProductsList.size();
    }

    public static class DiscountedProductViewHolder extends  RecyclerView.ViewHolder{

        TextView name, price, qty, unit, discount;
        ImageView image;

        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
            unit = itemView.findViewById(R.id.unit);
            discount = itemView.findViewById(R.id.discount);
            image = itemView.findViewById(R.id.product_image);

        }
    }
}
