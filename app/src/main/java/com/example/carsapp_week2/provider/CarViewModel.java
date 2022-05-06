package com.example.carsapp_week2.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarViewModel extends AndroidViewModel {
    private CarRepository myRepository;
    private LiveData<List<Car>> myAllCars;

    public CarViewModel(@NonNull Application application) {
        super(application);
        myRepository = new CarRepository(application);
        myAllCars = myRepository.getAllCars();
    }
    public LiveData<List<Car>> getMyAllCars(){return myAllCars;}
    public void insert(Car car){myRepository.insert(car);}
    public void deleteAll(){myRepository.deleteAll();}
}
