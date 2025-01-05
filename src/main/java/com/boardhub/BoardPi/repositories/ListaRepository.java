package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    public List<Lista> findByProjeto(Projeto projeto);
}
