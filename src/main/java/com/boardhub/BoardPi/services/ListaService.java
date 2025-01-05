package com.boardhub.BoardPi.services;

import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.repositories.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {
    @Autowired
    private ListaRepository listaRepository;

    public List<Lista> getListas() {
        return listaRepository.findAll();
    }
    public Lista getLista(long id) {
        return listaRepository.findById(id).orElse(null);
    }
    public Lista addLista(Lista lista) {
        return listaRepository.save(lista);
    }
    public Lista updateLista(Lista lista) {
        return listaRepository.save(lista);
    }
    public void deleteLista(long id) {
        listaRepository.deleteById(id);
    }
    public List<Lista> getPorProjeto(Projeto projeto) {
        return listaRepository.findByProjeto(projeto);
    }
}
