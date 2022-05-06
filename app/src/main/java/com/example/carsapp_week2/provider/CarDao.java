package com.example.carsapp_week2.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CarDao {
    @Query("select * from car")
    LiveData<List<Car>> getAllCar();

    @Query("select * from car where carMaker=:name")
    List<Car> getMaker(String name);

    @Query("select * from car where carModel=:name")
    List<Car> getModel(String name);

    @Query("select * from car where carColor=:name")
    List<Car> getColor(String name);

    @Query("select * from car where carPrice=:name")
    List<Car> getPrice(long name);

    @Query("select * from car where carSeats=:name")
    List<Car> getSeats(int name);

    @Query("select * from car where carYear=:name")
    List<Car> getYear(int name);

    @Query("select * from car where carId=:name")
    List<Car> getCar(String name);


    @Insert
    void addCar(Car car);

    @Query("delete from car where carId= :name")
    void deleteCar(int name);



    @Query("delete FROM car")
    void deleteAllCars();
}
