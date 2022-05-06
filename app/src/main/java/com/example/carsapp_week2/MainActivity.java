package com.example.carsapp_week2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carsapp_week2.provider.Car;
import com.example.carsapp_week2.provider.CarViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnTouchListener{

    public static final String MAKER = "MAKER";
    public static final String MODEL = "MODEL";
    public static final String COLOR = "COLOR";
    public static final String PRICE = "PRICE";
    public static final String YEAR = "YEAR";
    public static final String SEATS = "SEATS";
    public static final String CAR_NUMBER_FILE = "Car Number File";
    public static final String NUMBER_KEY = "Number_Key";


    static int numb=0;
    Context self;
    EditText maker,model,color,price,year,seats;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fabBtn;

    //listview
//    ArrayList<String> dataSource = new ArrayList<String>();
//    ArrayAdapter<String> adapter;
//    private ListView myListView;

    // save dataSource using shared preferences
    Gson gson = new Gson();
    // recycler card data
    ArrayList<CarItem> data = new ArrayList<>();
    String itemMaker,itemModel,itemColor,itemPrice,itemYear,itemSeats;


    //week 7
    private CarViewModel myCarViewModel;
    String carNumber_v;

    //week8

    DatabaseReference ref;

    //week10
    int lastX,lastY;

    //week11
    private GestureDetectorCompat mDetector;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
//week3 lab

        self = this;
//lab
        // references to both text views
        maker = findViewById(R.id.Maker);
        model = findViewById(R.id.Model);
        color = findViewById(R.id.Color);
        price = findViewById(R.id.Price);
        year = findViewById(R.id.Year);
        seats = findViewById(R.id.Seats);


        Log.d("test", "onCreate: ");

        /* Request permissions to access SMS */
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        /* Create and instantiate the local broadcast receiver
           This class listens to messages come from class SMSReceiver
         */
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        /*
         * Register the broadcast handler with the intent filter that is declared in
         * class SMSReceiver @line 11
         * */
        registerReceiver(myBroadCastReceiver, new IntentFilter(MySMSReceiver.SMS_FILTER));

        //week5
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar ,R.string.Nav_Open,R.string.Nav_Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //fab button
        fabBtn = findViewById(R.id.fab);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
            }
        });

        // listView
//        myListView =  findViewById(R.id.listView);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSource);
//        myListView.setAdapter(adapter);

        //week7
        myCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        //week8
        FirebaseDatabase fDB = FirebaseDatabase.getInstance();
        ref = fDB.getReference("/autoShowroom/fleet");
        //week10
        //View view=findViewById(R.id.activity_main);
        //View view2 = findViewById(R.id.listView);

//        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int X = (int) event.getRawX();
//                int Y = (int) event.getRawY();
//
//
//                int action = event.getActionMasked();
//                switch (action) {
//                    case (MotionEvent.ACTION_DOWN):
//                        lastX = X;
//                        lastY = Y;
//                        break;
//                    case (MotionEvent.ACTION_MOVE):
//                        break;
//
//                    case (MotionEvent.ACTION_UP):
//                        int nowX = X;
//                        int nowY = Y;
//                        int offsetX = nowX - lastX;
//                        int offsetY = nowY - lastY;
//                        boolean con1 = offsetY < 100 && offsetY > -100;
//                        boolean con2 = offsetX > 0;
//                        boolean con3 = offsetY > 0;
//                        boolean con4 = offsetX < 100 && offsetX > -100;
//
//
//                        if (con2 && con1) {
//                            addCar();
//                        } else if (con3 && con4) {
//                            ClearContent();
//                        } else if (offsetY < 0 && con4) {
//                            onClearAllItem();
//                        }
//                        break;
//
//                }
//                return true;
//
//            }
//        };

        //view.setOnTouchListener(onTouchListener);
        //view2.setOnTouchListener(onTouchListener);

        //week11
        gestureDetector Detector = new gestureDetector();
        mDetector = new GestureDetectorCompat(this,Detector);

        View view = findViewById(R.id.activity_main_layout);
        view.setOnTouchListener(this);





        Log.d("j", "onCreate: 运行");

    }
//week 11
    void loadDefaultValue (){
        counter = 0;
        String temp=String.valueOf(counter);
        seats.setText(temp);
        Log.d("test", "loadDefaultValue: ");

    }
    void incrementCounter(){
        counter += 1;
    }
    int getCounter(){
        int temp = Integer.parseInt(seats.getText().toString());
        return temp;
    }
    void updateValue(){
        counter = getCounter();

    }

    void setSeats(){
        updateValue();
        incrementCounter();

        String temp=String.valueOf(counter);
        seats.setText(temp);
    }
    void loadDefaultCar(){
        maker.setText("BMW");
        model.setText("X7");
        year.setText("2021");
        color.setText("Black");
        seats.setText("7");
        price.setText("1338");
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        mDetector.onTouchEvent(event);
        return true;
    }



    private class gestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("test", "onSingleTapConfirmed: "+counter+seats.getText());
            if (seats.getText().toString().equals("")){
                loadDefaultValue();
                }

            setSeats();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("test", "onDoubleTap: ");
            loadDefaultCar();

            return super.onDoubleTap(e);
        }

        void adjustSeats(float distanceY){
            if (seats.getText().toString().equals("")){
                seats.setText("0");
            }



            int getSeat = Integer.parseInt(seats.getText().toString());
            int temp;
            temp = getSeat + (int)distanceY/20;
            seats.setText(String.valueOf(temp));

            int checkSeat = Integer.parseInt(seats.getText().toString());
            if(checkSeat<0){
                seats.setText("0");
            }
            else if (checkSeat>40){
                seats.setText("40");
            }
        }

        void adjustPrice(float distanceX){
            if (price.getText().toString().equals("")){
                price.setText("0.0");
            }



            float price2 = Float.parseFloat(price.getText().toString());
            float temp;
            temp = price2 + distanceX;
            price.setText(String.valueOf(temp));

            float checkPrice = Float.parseFloat(price.getText().toString());
            if(checkPrice<0){
                price.setText("0.0");
            }
            else if (checkPrice>5000){
                price.setText("5000.0");
            }
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("test", "onScroll: distanceX:"+distanceX+"  distanceY:"+distanceY);
            if (distanceX == 0){
                adjustSeats(distanceY);
            }
            else if (distanceY==0){
                adjustPrice(distanceX);
            }







            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("test", "onFling: ");
            if(Math.abs(velocityX) >= 600 || Math.abs(velocityY)>=600){
                moveTaskToBack(true);
            }

            float checkPrice = Float.parseFloat(price.getText().toString());
            if(checkPrice<0){
                price.setText("0.0");
            }
            else if (checkPrice>5000){
                price.setText("5000.0");
            }
            float checkSeat = Integer.parseInt(seats.getText().toString());
            if(checkSeat<0){
                price.setText("0.0");
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("test", "onLongPress: ");
            ClearContent();
            super.onLongPress(e);
        }
    }

    //week11

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Option_1:
                ClearContent();
                break;
            case R.id.Option_2:
                onClearAllItem();
                break;



        }
        return true;
    }

    private void turnToActivity2() {

        Intent intent = new Intent(this, CardActivity.class);
        startActivity(intent);
        Log.d("j", "turnTo: 运行");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_1:
                addCar();
                break;
            case R.id.nav_2:
                onRemoveLastItem();

                break;
            case R.id.nav_3:
                onClearAllItem();
                break;
            case R.id.nav_4:
                turnToActivity2();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    class MyBroadCastReceiver extends BroadcastReceiver{
        /*
         * This method 'onReceive' will get executed every time class SMSReceive sends a broadcast
         * */
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(MySMSReceiver.SMS_MSG_KEY);
            /*
             * String Tokenizer is used to parse the incoming message
             * The protocol is to have the account holder name and account number separate by a semicolon
             * */
            StringTokenizer sT = new StringTokenizer(msg,";");//using semicolon as a signal(point) to separate each word.
            String sMaker = sT.nextToken();
            String sModel = sT.nextToken();
            String  sYear= sT.nextToken();
            String  sColor= sT.nextToken();
            String sSeats = sT.nextToken();
            String  sPrice= sT.nextToken();

            maker.setText(sMaker);
            model.setText(sModel);
            color.setText(sColor);
            year.setText(sYear);



            int PriceNum = Integer.valueOf(sPrice);
            if(PriceNum>0){
                price.setText(sPrice);

            }else{
                price.setText("");
                Toast.makeText(context,"Price should be bigger than 0",Toast.LENGTH_LONG).show();
            }


            seats.setText(sSeats);

            /*
             * Now, its time to update the UI
             * */


        }
    }

    public void onRemoveLastItem(){
//        if(dataSource.size()>0){
//            dataSource.remove(dataSource.size()-1);
//            adapter.notifyDataSetChanged();
//            numb-=1;
//            Toast.makeText(this,"you have removed the last item "+ " The car number: "+String.valueOf(numb),Toast.LENGTH_SHORT).show();
//        }
    }
    public void onClearAllItem(){
//        dataSource.clear();
//        adapter.notifyDataSetChanged();
        ClearGarage();
        //week8
        ref.removeValue();
        Toast.makeText(this,"you have cleared all items "+ " The car number: "+String.valueOf(numb),Toast.LENGTH_SHORT).show();

    }
    public void addCar(){
        if((maker.getText().toString().equals("")) | (model.getText().toString().equals(""))){
            Toast.makeText(this,"invalid input  (Must include maker and model)",Toast.LENGTH_SHORT).show();
        }
        else{
            numb+=1;
            String b = String.valueOf(numb);
            carNumber_v = b;
//            dataSource.add("Maker: "+ maker.getText().toString()+"   |   "+"Model:  "+ model.getText().toString());
//            adapter.notifyDataSetChanged();
            hintMessage(b);
            addCarItemInformation();



        }
    }
    public void addCarItemInformation(){


        //week7--save them into database(local)
        String dataMaker = maker.getText().toString();
        String dataModel = model.getText().toString();
        String dataColor = color.getText().toString();
        float dataPrice = Float.parseFloat(price.getText().toString());
        int dataSeats = Integer.parseInt(seats.getText().toString());
        int dataYear = Integer.parseInt(year.getText().toString());
        // save them into CarItem


        CarItem item = new CarItem(dataMaker,dataModel,dataColor,dataPrice,dataYear,dataSeats);
        data.add(item);



        Car c = new Car(dataMaker,dataModel,dataColor,dataPrice,dataSeats,dataYear);
        myCarViewModel.insert(c);
        //week8 save them into Cloud database
        ref.push().setValue(c); //push with a unique key and value of c



    }



    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences getPre = getSharedPreferences(getString(R.string.Pre_file_key),0);
        String myData = getPre.getString(MAKER,"");
        EditText eT = findViewById(R.id.Maker);
        eT.setText(myData);
        //
        myData = getPre.getString(MODEL,"");
        eT =findViewById(R.id.Model);
        eT.setText(myData);
        //
        myData = getPre.getString(COLOR,"");
        eT =findViewById(R.id.Color);
        eT.setText(myData);
        //
        myData = getPre.getString(YEAR,"0");
        eT =findViewById(R.id.Year);
        eT.setText(myData);
        //
        myData = getPre.getString(SEATS,"0");
        eT =findViewById(R.id.Seats);
        eT.setText(myData);
        //
        myData = getPre.getString(PRICE,"0");
        eT =findViewById(R.id.Price);
        eT.setText(myData);

//lab
        SharedPreferences getCarPre = getSharedPreferences(CAR_NUMBER_FILE,MODE_PRIVATE);
        String myData2 = getCarPre.getString(NUMBER_KEY,"0");
//        TextView eT2 = findViewById(R.id.CarNumber);
//        eT2.setText(myData2);
        numb = Integer.valueOf(myData2);

//lab
        if (seats.getText().toString().equals("")){
            loadDefaultValue();
            Log.d("test", "onStart: ");}
        if (price.getText().toString().equals("")){
            price.setText("0.0");
           }



    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPre = getSharedPreferences(getString(R.string.Pre_file_key),0);
        SharedPreferences.Editor editor = sharedPre.edit();
        EditText point = findViewById(R.id.Maker);
        String Data = point.getText().toString();
        editor.putString(MAKER,Data);
        //
        point = findViewById(R.id.Model);
        Data = point.getText().toString();
        editor.putString(MODEL,Data);
        //
        point = findViewById(R.id.Color);
        Data = point.getText().toString();
        editor.putString(COLOR,Data);
        //
        point = findViewById(R.id.Year);
        Data = point.getText().toString();
        editor.putString(YEAR,Data);
        //
        point = findViewById(R.id.Seats);
        Data = point.getText().toString();
        editor.putString(SEATS,Data);
        //
        point = findViewById(R.id.Price);
        Data = point.getText().toString();
        editor.putString(PRICE,Data);
        editor.apply();

        SharedPreferences getCarPre = getSharedPreferences(CAR_NUMBER_FILE,0);
        SharedPreferences.Editor editor2 = getCarPre.edit();
        String Data2 = String.valueOf(numb);
        editor2.putString(NUMBER_KEY,Data2);
        editor2.apply();

        //save dataSource using shared preferences
        SharedPreferences getListPre = getSharedPreferences(getString(R.string.cardList_file),0);
        SharedPreferences.Editor editor3 = getListPre.edit();
        //Convert the ArrayList object into as string
        String dbStr = gson.toJson(data);
        editor3.putString(getString(R.string.CARD_LIST_KEY),dbStr);
        editor3.apply();

        Log.d("j","onPause: dataSource: "+dbStr);







    }


    public void hintMessage (String b){
        EditText Maker=findViewById(R.id.Maker);
        //b will be added one in number when click add car btn---record car number----but it is out of date
        //Toast.makeText(this, "We added a new car("+Maker.getText().toString()+") "+"The CarNumber: "+b, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "You have added a new car("+Maker.getText().toString()+") ", Toast.LENGTH_SHORT).show();
    }


    public void ClearContent(){
        EditText Clear1 = findViewById(R.id.Maker);
        EditText Clear2 = findViewById(R.id.Model);
        EditText Clear3 = findViewById(R.id.Year);
        EditText Clear4 = findViewById(R.id.Color);
        EditText Clear5 = findViewById(R.id.Seats);
        EditText Clear6 = findViewById(R.id.Price);

        Clear1.getText().clear();
        Clear2.getText().clear();
        Clear3.getText().clear();
        Clear4.getText().clear();
        Clear5.getText().clear();
        Clear6.getText().clear();

        SharedPreferences sharedPre = getSharedPreferences(getString(R.string.Pre_file_key),0);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.remove(MAKER);
        editor.remove(MODEL);
        editor.remove(PRICE);
        editor.remove(SEATS);
        editor.remove(COLOR);
        editor.remove(YEAR);




    }

    public void ClearGarage(){
        numb = 0;
        myCarViewModel.deleteAll();
    }




}



