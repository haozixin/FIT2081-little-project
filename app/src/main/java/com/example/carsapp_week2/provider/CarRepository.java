package com.example.carsapp_week2.provider;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepository {

    private CarDao myCarDao;
    private LiveData<List<Car>> myAllCars;

    CarRepository(Application application){
        CarDatabase db = CarDatabase.getDatabase(application);
        myCarDao = db.carDao();
        myAllCars = myCarDao.getAllCar();
    }

    LiveData<List<Car>> getAllCars() {return  myAllCars;}

    void insert(Car car){
        CarDatabase.databaseWriteExecutor.execute(() -> myCarDao.addCar(car));
    }

    void deleteAll(){
        CarDatabase.databaseWriteExecutor.execute(() ->{myCarDao.deleteAllCars();});
    }
}
