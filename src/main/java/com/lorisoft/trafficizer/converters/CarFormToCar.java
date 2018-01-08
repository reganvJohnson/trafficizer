package com.lorisoft.trafficizer.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lorisoft.trafficizer.command.CarForm;
import com.lorisoft.trafficizer.entity.Car;

@Component
public class CarFormToCar implements Converter<CarForm, Car> {

    @Override
    public Car convert(CarForm carForm) {
        Car car = new Car();
        if (carForm.getId() != null  && !StringUtils.isEmpty(carForm.getId())) {
            car.setId(new Long(carForm.getId()));
        }
        
        car.setNumber(carForm.getNumber());
        car.setCarrier_code(carForm.getCarrier_code());
        car.setCarrier(carForm.getCarrier());
        car.setAar_code(carForm.getAar_code());
        car.setDescription(carForm.getDescription());
        return car;
    }
}
