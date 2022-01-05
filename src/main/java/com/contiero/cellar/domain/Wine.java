package com.contiero.cellar.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Wine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	@NotNull(message = "Wine name")
	private String name;
	
	@Column
	private String type;
	
	@Column
	private String producer;
	
	@Column
	private double price;
	
	@Column
	private int year;
	
	@Column
	private String region;
	
	@Column
	private int numberOfBottles;
	
	
	public Wine() {
	}


	public Wine(long id, String name, String type, String producer, double price, int year, 
			String region, int numberOfBottles) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.producer = producer;
		this.price = price;
		this.year = year;
		this.region = region;
		this.numberOfBottles = numberOfBottles;
	}
	
	
	public Wine(String name) {
		super();
		this.name = name;	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(String producer) {
		this.producer = producer;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public int getNumberOfBottles() {
		return numberOfBottles;
	}


	public void setNumberOfBottles(int numberOfBottles) {
		this.numberOfBottles = numberOfBottles;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + 
				", name=" + name + 
				", type=" + type + 
				", producer=" + producer +
				", price=" + price + 
				", year=" + year + 
				", region=" + region + 
				", numBottles=" + numberOfBottles +
				"]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, numberOfBottles, price, producer, region, type, year);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		return Objects.equals(name, other.name) && numberOfBottles == other.numberOfBottles
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(producer, other.producer) && Objects.equals(region, other.region)
				&& Objects.equals(type, other.type) && year == other.year;
	}



}
