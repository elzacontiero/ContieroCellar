package com.contiero.cellar.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.contiero.cellar.domain.Wine;

public interface WineRepo extends JpaRepository<Wine, Long> {
	
	List<Wine> findWineByProducer(String producer);

}
