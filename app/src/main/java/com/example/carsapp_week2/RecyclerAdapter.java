package com.example.carsapp_week2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carsapp_week2.provider.Car;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<CarItem> data ;

    public RecyclerAdapter(ArrayList<CarItem> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_maker.setText(data.get(position).getMaker());
        holder.item_model.setText(data.get(position).getModel());
        holder.item_color.setText(data.get(position).getColor());
        holder.item_seats.setText(data.get(position).getSeats()+"");
        holder.item_price.setText(data.get(position).getPrice()+"");
        holder.item_year.setText(data.get(position).getYear()+"");
        final int fPosition = position;
        holder.item_number.setText(" "+(fPosition+1));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_maker,item_model,item_color,item_price,item_seats,item_year,item_number;


        public ViewHolder(View itemView) {
            super(itemView);
            item_number = itemView.findViewById(R.id.number_id);
            item_maker = itemView.findViewById(R.id.maker_id);
            item_model = itemView.findViewById(R.id.model_id);
            item_color = itemView.findViewById(R.id.color_id);
            item_price = itemView.findViewById(R.id.price_id);
            item_seats = itemView.findViewById(R.id.seats_id);
            item_year = itemView.findViewById(R.id.year_id);



        }
    }
}
