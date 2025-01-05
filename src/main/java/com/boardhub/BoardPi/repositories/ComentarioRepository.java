package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.entities.Comentario;
import com.boardhub.BoardPi.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByTarefa(Tarefa tarefa);
}
