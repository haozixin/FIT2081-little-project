package com.example.carsapp_week2.provider;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "car")
public class Car {
    public static final String TABLE_NAME = "car";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "carId")
    private  int id;
    @ColumnInfo(name = "carMaker")
    private String maker;
    @ColumnInfo(name = "carModel")
    private String model;
    @ColumnInfo(name = "carColor")
    private String color;
    @ColumnInfo(name = "carPrice")
    private float price;
    @ColumnInfo(name = "carSeats")
    private int seats;
    @ColumnInfo(name = "carYear")
    private int year;


    public Car (String maker, String model,String color,float price,int seats,int year){

        this.maker = maker;
        this.model = model;
        this.color = color;
        this.price = price;
        this.seats = seats;
        this.year = year;
    }


    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public  String getMaker(){return maker;}

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public float getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public int getYear() {
        return year;
    }
}
