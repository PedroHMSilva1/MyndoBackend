package com.boardhub.BoardPi.services;

import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.entities.Usuario;
import com.boardhub.BoardPi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private MembroProjetoRepository membroProjetoRepository;
    @Autowired
    private ListaRepository listaRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    public Projeto findById(Long id) {
        return projetoRepository.findById(id).orElse(null);
    }

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public List<Projeto> findByTitulo(String titulo) {
        return projetoRepository.findByTitulo(titulo);
    }

    public List<Projeto> findByCriador(Usuario criador){
        return projetoRepository.findByCriador(criador);
    }
    public List<Projeto> getProjetosParticipados(Usuario usuario){
        return projetoRepository.findProjectsByMembro(usuario);
    }
    public Projeto addProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }
    @Transactional
    public void updateProjeto(Projeto projeto) {
        projetoRepository.updateProjeto(projeto.getDataAlteracao(), projeto.getTitulo(), projeto.getId());
    }
    public void deleteProjeto(long id) {
        projetoRepository.deleteById(id);
    }
}
