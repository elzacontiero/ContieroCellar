package com.contiero.cellar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contiero.cellar.domain.Wine;

public interface WineRepo extends JpaRepository<Wine, Long>{

}
