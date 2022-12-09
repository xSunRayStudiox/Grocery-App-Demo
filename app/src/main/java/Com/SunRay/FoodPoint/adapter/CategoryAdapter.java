package Com.SunRay.FoodPoint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Com.SunRay.FoodPoint.ProductPay;
import Com.SunRay.FoodPoint.R;
import Com.SunRay.FoodPoint.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.category_name.setText(categoryList.get(position).getName());

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, "Under Processing", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView category_name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            category_name = itemView.findViewById(R.id.categories_name);

        }
    }

}