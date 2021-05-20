package fr.desgenetezreiter.cmax.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.MenuModel;
import fr.desgenetezreiter.cmax.models.ProductModel;
import fr.desgenetezreiter.cmax.models.ProductResult;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    Context context;
    ArrayList<MenuModel> menus;
    private RecycleViewOnClickListener recycleViewOnClickListener;

    public MenuAdapter(Context context, ArrayList<MenuModel> menus, RecycleViewOnClickListener recycleViewOnClickListener){
        this.context = context;
        this.menus = menus;
        this.recycleViewOnClickListener = recycleViewOnClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MenuAdapter.MenuViewHolder(inflater.inflate(R.layout.adapter_menu, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MenuViewHolder holder, int position) {
        MenuModel menu = menus.get(position);
        holder.menu_name.setText(menu.getName());
        holder.menu_description.setText(menu.getDescription());

        double total = 0;
        for(ProductResult productResult : menu.getProducts()){
            total += productResult.getPrice();
        }
        String tot = total + "€";
        holder.menu_price.setText(tot);
    }

    @Override
    public int getItemCount() {
        if(menus == null){
            return 0;
        }
        return menus.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        protected MaterialCardView materialCardView;
        protected TextView menu_name;
        protected TextView menu_description;
        protected TextView menu_price;

        public MenuViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.adapter_menu_card);
            menu_name = itemView.findViewById(R.id.adapter_menu_name);
            menu_description = itemView.findViewById(R.id.adapter_menu_description);
            menu_price = itemView.findViewById(R.id.adapter_menu_prix);

        }
    }
}
