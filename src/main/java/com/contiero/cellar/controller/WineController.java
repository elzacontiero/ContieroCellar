package com.contiero.cellar.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contiero.cellar.domain.Wine;
import com.contiero.cellar.service.WineService;


@RestController
@RequestMapping("/wine")
public class WineController {
	private WineService service;
	
	private WineController(WineService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Wine> createWine(@RequestBody Wine wine) {
		return new ResponseEntity<Wine>(service.create(wine), HttpStatus.CREATED);
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Wine>> readAll() {
		List<Wine> listOfWine = service.getAll();
		return new ResponseEntity<List<Wine>>(listOfWine, HttpStatus.OK);
	}
	
	@GetMapping("/readById/{id}")
	public ResponseEntity<Wine> readById(@PathVariable long id) {
		Wine wine = service.getById(id);
		return new ResponseEntity<Wine>(wine, HttpStatus.OK);
	}
	
	@GetMapping("/readByProducer/{producer}")
	public ResponseEntity<List<Wine>> readByProducer(@PathVariable String producer){
		List<Wine> listOfWine = service.getByProducer(producer);
		return new ResponseEntity<List<Wine>>(listOfWine, HttpStatus.OK);
	}
	
	@GetMapping("/readByType/{type}")
	public ResponseEntity<List<Wine>> readByType(@PathVariable String type) {
		List<Wine> wines = service.getByType(type);
		return new ResponseEntity<List<Wine>>(wines, HttpStatus.OK);
	}

	@GetMapping("/readCheaperThan/{price}")
	public ResponseEntity<List<Wine>> readCheaperThan(@PathVariable double price) {
		List<Wine> wines = service.getCheaperThan(price);
		return new ResponseEntity<List<Wine>>(wines, HttpStatus.OK);
	}
	
	@GetMapping("/readByRegion/{region}")
	public ResponseEntity<List<Wine>> readByRegion(@PathVariable String region) {
		List<Wine> wines = service.getByRegion(region);
		return new ResponseEntity<List<Wine>>(wines, HttpStatus.OK);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Wine> update(@PathVariable long id, @RequestBody Wine wine) { 
		return new ResponseEntity<Wine>(service.update(id, wine), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
	
		if (service.delete(id)) {
			return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}
}
