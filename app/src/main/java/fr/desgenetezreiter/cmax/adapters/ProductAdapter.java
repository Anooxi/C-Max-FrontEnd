package fr.desgenetezreiter.cmax.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.ProductModel;
import fr.desgenetezreiter.cmax.models.ProductResult;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<ProductResult> products;
    private RecycleViewOnClickListener recycleViewOnClickListener;

    public ProductAdapter(Context context, ArrayList<ProductResult> products, RecycleViewOnClickListener recycleViewOnClickListener) {
        this.context = context;
        this.products = products;
        this.recycleViewOnClickListener = recycleViewOnClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ProductAdapter.ProductViewHolder(inflater.inflate(R.layout.adapter_product,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int position) {
        ProductModel product = products.get(position).getProduct();
        holder.product_name.setText(product.getName());
        holder.product_description.setText(product.getDescription());
        holder.product_price.setVisibility(View.INVISIBLE);

        Picasso.get().load(product.getImg_url())
                .resize(120,120)
                .error(R.drawable.app_logo)
                .into(holder.product_image);
    }

    @Override
    public int getItemCount() {
        if(products == null){
            return 0;
        }
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        protected MaterialCardView materialCardView;
        protected TextView product_name;
        protected TextView product_description;
        protected TextView product_price;
        protected ImageView product_image;

        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.adapter_product_card);
            materialCardView.setOnClickListener(v -> {
                recycleViewOnClickListener.onItemClick(getAdapterPosition());
            });
            product_name = itemView.findViewById(R.id.adapter_product_name);
            product_description = itemView.findViewById(R.id.adapter_product_description);
            product_price = itemView.findViewById(R.id.adapter_product_prix);
            product_image = itemView.findViewById(R.id.adapter_product_iv);
        }
    }
}
