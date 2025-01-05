package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Prioridade;
import com.boardhub.BoardPi.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    public List<Tarefa> findByLista(Lista lista);

    @Modifying
    @Transactional
    @Query("update Tarefa t set t.tagPrioridade = null where t.tagPrioridade = :prioridade")
    public void setPrioridadeNull(Prioridade prioridade);
}
