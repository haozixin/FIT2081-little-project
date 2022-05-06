package com.example.carsapp_week2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsapp_week2.provider.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> myCars;
    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        holder.item_maker.setText(myCars.get(position).getMaker());
        holder.item_model.setText(myCars.get(position).getModel());
        holder.item_color.setText(myCars.get(position).getColor());
        holder.item_seats.setText(myCars.get(position).getSeats()+"");
        holder.item_price.setText(myCars.get(position).getPrice()+"");
        holder.item_year.setText(myCars.get(position).getYear()+"");
        final int fPosition = position;
        holder.item_number.setText("Number: "+(fPosition+1));


    }

    @Override
    public int getItemCount() {
        if (myCars==null){
            return 0;
        }
        else
            return myCars.size();

    }
    public void setCars(List<Car> newData){
        myCars = newData;
    }

    public class CarViewHolder extends RecyclerView.ViewHolder{
        public TextView item_maker,item_model,item_color,item_price,item_seats,item_year,item_number,number_v;

        public CarViewHolder(@NonNull View itemView) {
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
