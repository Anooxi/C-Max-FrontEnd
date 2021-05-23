package fr.desgenetezreiter.cmax.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.UserModel;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private Context context;
    private ArrayList<UserModel> restaurants;
    private RecycleViewOnClickListener recycleViewOnClickListener;

    @NonNull
    @NotNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new RestaurantViewHolder(inflater.inflate(R.layout.adapter_restaurant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RestaurantViewHolder holder, int position) {
        UserModel userModel = restaurants.get(position);
        holder.restaurant_name.setText(userModel.getRestaurant_name());
        holder.restaurant_description.setText(userModel.getFullAddress());
        holder.restaurant_nb_menus.setText(context.getResources().getString(R.string.nb_menus,
                userModel.getMenus().size()));
    }

    @Override
    public int getItemCount() {
        if (restaurants == null) {
            return 0;
        }
        return restaurants.size();
    }

    public RestaurantAdapter(Context context, ArrayList<UserModel> restaurants, RecycleViewOnClickListener recycleViewOnClickListener) {
        this.context = context;
        this.restaurants = restaurants;
        this.recycleViewOnClickListener = recycleViewOnClickListener;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        protected MaterialCardView materialCardView;
        protected TextView restaurant_name;
        protected TextView restaurant_description;
        protected ImageView restaurant_image;
        protected TextView restaurant_nb_menus;

        public RestaurantViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.adapter_restaurant_card);
            materialCardView.setOnClickListener(v -> {
                recycleViewOnClickListener.onItemClick(getAdapterPosition());
            });
            restaurant_name = itemView.findViewById(R.id.adapter_restaurant_name);
            restaurant_description = itemView.findViewById(R.id.adapter_restaurant_description);
            restaurant_image = itemView.findViewById(R.id.adapter_restaurant_iv);
            restaurant_nb_menus = itemView.findViewById(R.id.adapter_restaurant_nb_menus);
        }
    }


}
