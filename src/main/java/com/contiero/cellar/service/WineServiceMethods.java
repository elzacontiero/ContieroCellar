package com.contiero.cellar.service;

import java.util.List;

public interface WineServiceMethods<T> {

	//Create
	T create(T t);
	
	//Read All
	List<T> getAll();
	
	//Read By Id
	T getById(long id);
	
	// 
	List<T> getByProducer(String producer);
	
	List<T> getByType(String type);

	List<T> getCheaperThan(double price);
	
	List<T> getByRegion(String region);
	
	//Update
	T update(long id, T t);
	
	//Delete
	boolean delete(long id);
	
}
