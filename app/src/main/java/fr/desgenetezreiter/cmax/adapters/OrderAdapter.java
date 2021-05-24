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

import java.util.ArrayList;

import fr.desgenetezreiter.cmax.R;
import fr.desgenetezreiter.cmax.models.OrderResult;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private ArrayList<OrderResult> orderResults;
    private RecycleViewOnClickListener recycleViewOnClickListener;

    public OrderAdapter(Context context, ArrayList<OrderResult> orderResults, RecycleViewOnClickListener recycleViewOnClickListener) {
        this.context = context;
        this.orderResults = orderResults;
        this.recycleViewOnClickListener = recycleViewOnClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new OrderAdapter.OrderViewHolder(inflater.inflate(R.layout.adapter_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderViewHolder holder, int position) {
        OrderResult order = orderResults.get(position);
        holder.status.setText(order.getStatus());
        holder.clientName.setText(order.getClient().getFullName());
        holder.clientAddress.setText(order.getClient().getFullAddress());
        holder.restaurantName.setText(order.getRestaurant().getFullAddress());
        holder.restaurantAddress.setText(order.getRestaurant().getFullAddress());
    }

    @Override
    public int getItemCount() {
        if(orderResults == null){
            return 0;
        }
        return orderResults.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        protected TextView clientName;
        protected TextView clientAddress;
        protected TextView restaurantName;
        protected TextView restaurantAddress;
        protected MaterialCardView materialCardView;
        protected TextView status;

        public OrderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.adapter_order_cn);
            clientAddress = itemView.findViewById(R.id.adapter_order_ca);
            restaurantName = itemView.findViewById(R.id.adapter_order_rn);
            restaurantAddress = itemView.findViewById(R.id.adapter_order_ra);
            materialCardView = itemView.findViewById(R.id.adapter_order_card);
            status = itemView.findViewById(R.id.adapter_order_status);
            materialCardView.setOnClickListener(v -> {
                recycleViewOnClickListener.onItemClick(getAdapterPosition());
            });
        }
    }
}
