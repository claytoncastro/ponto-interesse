package com.project.pontointeresse.repository;

import com.project.pontointeresse.domain.entities.PontoInteresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoInteresseRepository extends JpaRepository<PontoInteresse, Long> {
}