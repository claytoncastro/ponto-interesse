package com.project.pontointeresse.repository;

import com.project.pontointeresse.domain.entities.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicaoRepository extends JpaRepository<Posicao, Long> {
}