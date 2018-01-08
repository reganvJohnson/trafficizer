package com.lorisoft.trafficizer.services;

import com.lorisoft.trafficizer.command.CarForm;
import com.lorisoft.trafficizer.entity.Car;

import java.util.List;

public interface CarService {

	List<Car> listAll();

	Car getById(Long id);

	Car saveOrUpdate(Car car);

	void delete(Long id);

	Car saveOrUpdateCarForm(CarForm carForm);

}
