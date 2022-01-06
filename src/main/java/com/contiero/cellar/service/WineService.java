package com.contiero.cellar.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.contiero.cellar.domain.Wine;
import com.contiero.cellar.repo.WineRepo;

@Service
public class WineService implements WineServiceMethods<Wine> {

	private WineRepo repo;

	public WineService(WineRepo repo) {
		this.repo = repo;
	}

	@Override
	public Wine create(Wine wine) {
		return repo.save(wine);
	}

	@Override
	public List<Wine> getAll() {		
		return repo.findAll();
	}

	@Override
	public Wine getById(long id) {
		Optional<Wine> optWine = repo.findById(id);
		if(optWine.isPresent()) {
			return optWine.get();
		}
		return null;
	}
	
	

	@Override
	public Wine update(long id, Wine wine) {
		Optional<Wine> existingWine = repo.findById(id);
		if (existingWine.isPresent()) {
			Wine existing = existingWine.get();
			existing.setName(wine.getName());
			existing.setNumberOfBottles(wine.getNumberOfBottles());
			existing.setPrice(wine.getPrice());
			existing.setProducer(wine.getProducer());
			existing.setRegion(wine.getRegion());
			existing.setType(wine.getType());
			existing.setYear(wine.getYear());
			return repo.saveAndFlush(existing);
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}

	@Override
	public List<Wine> getByProducer(String producer) {
		return repo.findWineByProducer(producer);
	}

	
}

