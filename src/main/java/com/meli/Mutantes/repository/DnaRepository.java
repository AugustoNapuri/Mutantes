package com.meli.Mutantes.repository;

import com.meli.Mutantes.entities.DnaCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DnaCase, Long> {

    Long countByIsMutantTrue();
}
