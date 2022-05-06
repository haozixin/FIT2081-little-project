package com.example.carsapp_week2;

public class CarItem {
    private String maker,model,color;
    private int year,seats;
    private float price;



    public CarItem(String maker, String model, String color, float price, int year, int seats){
        this.maker=maker;
        this.model=model;
        this.color=color;
        this.price=price;
        this.year=year;
        this.seats=seats;

    }

    public String getColor() {
        return color;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
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
