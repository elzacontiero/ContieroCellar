package com.contiero.cellar.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.contiero.cellar.domain.Wine;

public interface WineRepo extends JpaRepository<Wine, Long> {

	Optional<Wine> findById(long id);

	List<Wine> findWineByProducer(String producer);

	List<Wine> findWineByType(String type);

	List<Wine> findWineByPriceLessThan(double price);
	
	List<Wine> findWineByRegion(String region);
	
	List<Wine> findWineByTypeAndPriceLessThan(String type, double price);
}
