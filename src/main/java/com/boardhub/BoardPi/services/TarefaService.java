package com.boardhub.BoardPi.services;


import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> getTarefas() {
        return tarefaRepository.findAll();
    }
    public Tarefa getTarefa(long id) {
        return tarefaRepository.findById(id).orElse(null);
    }
    public Tarefa addTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    public Tarefa updateTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    public void deleteTarefa(long id) {
        tarefaRepository.deleteById(id);
    }
    public List<Tarefa> getPorLista(Lista lista){
        return tarefaRepository.findByLista(lista);
    }
}
