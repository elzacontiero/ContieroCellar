package com.contiero.cellar.service;

import java.util.List;

public interface WineServiceMethods<T> {

	//Create
	T create(T t);
	
	//Read All
	List<T> getAll();
	
	//Read By Id
	T getById(long id);
	
	//Update
	T update(long id, T t);
	
	//Delete
	boolean delete(long id);
	
}
