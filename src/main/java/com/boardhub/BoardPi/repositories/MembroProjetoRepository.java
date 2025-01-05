package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.entities.MembroProjeto;
import com.boardhub.BoardPi.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembroProjetoRepository extends JpaRepository<MembroProjeto, Long> {
    public List<MembroProjeto> findByProjeto(Projeto projeto);
}
