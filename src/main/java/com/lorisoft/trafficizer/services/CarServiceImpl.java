package com.lorisoft.trafficizer.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorisoft.trafficizer.command.CarForm;
import com.lorisoft.trafficizer.converters.CarFormToCar;
import com.lorisoft.trafficizer.entity.Car;
import com.lorisoft.trafficizer.repositories.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private CarFormToCar carFormToCar;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarFormToCar carFormToCar) {
        this.carRepository = carRepository;
        this.carFormToCar = carFormToCar;
    }


    @Override
    public List<Car> listAll() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add); //fun with Java 8
        return cars;
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public Car saveOrUpdate(Car car) {
        carRepository.save(car);
        return car;
    }

    @Override
    public void delete(Long id) {
        carRepository.delete(id);

    }

    @Override
    public Car saveOrUpdateCarForm(CarForm carForm) {
        Car savedCar = saveOrUpdate(carFormToCar.convert(carForm));

        System.out.println("Saved Car Id: " + savedCar.getId());
        return savedCar;
    }
}
