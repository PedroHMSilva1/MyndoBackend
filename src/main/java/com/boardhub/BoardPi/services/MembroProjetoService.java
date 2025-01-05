package com.boardhub.BoardPi.services;

import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.MembroProjeto;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.repositories.MembroProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroProjetoService {
    @Autowired
    private MembroProjetoRepository membroProjetoRepository;
    public List<MembroProjeto> getMembrosProjeto() {
        return membroProjetoRepository.findAll();
    }
    public MembroProjeto getMembroProjeto(long id) {
        return membroProjetoRepository.findById(id).orElse(null);
    }
    public List<MembroProjeto> getPorProjeto(Projeto projeto) {
        return membroProjetoRepository.findByProjeto(projeto);
    }
    public MembroProjeto addMembroProjeto(MembroProjeto membroProjeto) {
        return membroProjetoRepository.save(membroProjeto);
    }
    public MembroProjeto updateMembroProjeto(MembroProjeto membroProjeto) {
        return membroProjetoRepository.save(membroProjeto);
    }
    public void deleteMembroProjeto(long id) {
        membroProjetoRepository.deleteById(id);
    }
}
