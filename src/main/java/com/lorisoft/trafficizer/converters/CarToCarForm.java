package com.lorisoft.trafficizer.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lorisoft.trafficizer.command.CarForm;
import com.lorisoft.trafficizer.entity.Car;

@Component
public class CarToCarForm implements Converter<Car, CarForm> {
    @Override
    public CarForm convert(Car car) {
        CarForm carForm = new CarForm();
        carForm.setId(car.getId());
        carForm.setAar_code(car.getAar_code());
        carForm.setCarrier(car.getCarrier());
        carForm.setCarrier_code(car.getCarrier_code());
        carForm.setDescription(car.getDescription());
        carForm.setNumber(car.getNumber());
		return carForm;
    }
}
