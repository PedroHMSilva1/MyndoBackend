package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.entities.Prioridade;
import com.boardhub.BoardPi.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrioridadeRepository extends JpaRepository<Prioridade, Long> {
    public List<Prioridade> findByProjeto(Projeto projeto);
}
