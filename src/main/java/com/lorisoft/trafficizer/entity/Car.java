package com.lorisoft.trafficizer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	@GeneratedValue
	private Long id;
	private String number;
	private String carrier_code;
	private String carrier;
	private String aar_code;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCarrier_code() {
		return carrier_code;
	}

	public void setCarrier_code(String carrier_code) {
		this.carrier_code = carrier_code;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getAar_code() {
		return aar_code;
	}

	public void setAar_code(String aar_code) {
		this.aar_code = aar_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}