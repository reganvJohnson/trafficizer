package com.lorisoft.trafficizer.repositories;


import com.lorisoft.trafficizer.entity.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface CarRepository extends CrudRepository<Car, Long> {
}
