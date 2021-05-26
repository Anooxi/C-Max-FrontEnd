package fr.desgenetezreiter.cmax.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.ProductModel;
import fr.desgenetezreiter.cmax.models.ProductResult;

public class ProductAddAdapter extends RecyclerView.Adapter<ProductAddAdapter.ProductAddViewHolder>  {

    private Context context;
    private ArrayList<ProductModel> products;
    private RecycleViewOnClickListener recycleViewOnClickListener;

    public ProductAddAdapter(Context context, ArrayList<ProductModel> products, RecycleViewOnClickListener recycleViewOnClickListener) {
        this.context = context;
        this.products = products;
        this.recycleViewOnClickListener = recycleViewOnClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ProductAddAdapter.ProductAddViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ProductAddAdapter.ProductAddViewHolder(inflater.inflate(R.layout.adapter_addproduct,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAddAdapter.ProductAddViewHolder holder, int position) {
        ProductModel product = products.get(position);
        holder.product_name.setText(product.getName());
        holder.product_description.setText(product.getDescription());
        holder.product_price.setVisibility(View.INVISIBLE);
        holder.product_number.setText("0");

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

    public class ProductAddViewHolder extends RecyclerView.ViewHolder {

        protected MaterialCardView materialCardView;
        protected TextView product_name;
        protected TextView product_description;
        protected TextView product_price;
        protected ImageView product_image;
        protected TextView product_number;
        protected MaterialButton product_button;

        public ProductAddViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.adapter_addproduct_card);
            product_name = itemView.findViewById(R.id.adapter_addproduct_name);
            product_description = itemView.findViewById(R.id.adapter_addproduct_description);
            product_price = itemView.findViewById(R.id.adapter_addproduct_prix);
            product_image = itemView.findViewById(R.id.adapter_addproduct_iv);
            product_number = itemView.findViewById(R.id.adapter_addproduct_number);
            product_button = itemView.findViewById(R.id.adapter_addproduct_btn);
            materialCardView.setOnClickListener(v -> {
                recycleViewOnClickListener.onItemClick(getAdapterPosition());
                product_number.setText(Integer.parseInt(product_number.getText().toString()) + 1 + "");
            });
            product_button.setOnClickListener(v -> {
                product_number.setText(Integer.parseInt(product_number.getText().toString()) + 1 + "");
            });
        }
    }

    public ArrayList<ProductModel> getProducts() {
        return products;
    }
}
