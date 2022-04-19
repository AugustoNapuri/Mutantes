package com.meli.Mutantes.repository;

import com.meli.Mutantes.entities.AdnCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdnRepository extends JpaRepository<AdnCase, Long> {

    Long countByIsMutantTrue();
}
