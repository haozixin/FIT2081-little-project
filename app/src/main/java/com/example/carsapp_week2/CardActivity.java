package com.example.carsapp_week2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.carsapp_week2.provider.CarViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter2;
    ArrayList<CarItem> data;
    //week7
    private CarViewModel myCarViewModel;
    TextView carNumber_v;

//Create a new instance of Gson
    Gson gson = new Gson();
//convert the string back to the ArrayList data type
    Type type = new TypeToken<ArrayList<CarItem>>() {}.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);



////get data from main activity by using shared preference
//        SharedPreferences getListPre = getSharedPreferences(getString(R.string.cardList_file),MODE_PRIVATE);
//        String myCardData = getListPre.getString(getString(R.string.CARD_LIST_KEY),"None");
//        Log.d("j", "Second onCreate:  "+myCardData);
//        //convert the string back to the ArrayList data type
//        data = gson.fromJson(myCardData,type);
//        Log.d("j", "Second onCreate data:  "+data);
//
//        recyclerView =  findViewById(R.id.recycler_view);
//
//        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
//        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
//// pass data to recycler adapter
//        adapter2 = new RecyclerAdapter(data);
//        recyclerView.setAdapter(adapter2);

        //week7

        carNumber_v = findViewById(R.id.number_v_id);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CarAdapter adapter3 = new CarAdapter();
        recyclerView.setAdapter(adapter3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        myCarViewModel.getMyAllCars().observe(this, newData -> {
            adapter3.setCars(newData);
            adapter3.notifyDataSetChanged();

            carNumber_v.setText(newData.size() + " ");
            Log.d("j", "cardActivity"+newData);

        });






    }


}