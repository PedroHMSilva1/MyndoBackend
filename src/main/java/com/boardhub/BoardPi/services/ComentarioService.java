package com.boardhub.BoardPi.services;

import com.boardhub.BoardPi.entities.Comentario;
import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    public List<Comentario> getComentarios() {
        return comentarioRepository.findAll();
    }
    public List<Comentario> getPorTarefa(Tarefa tarefa) {
        return comentarioRepository.findByTarefa(tarefa);
    }
    public Comentario getComentario(long id) {
        return comentarioRepository.findById(id).orElse(null);
    }
    public Comentario addComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
    public Comentario updateComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
    public void deleteComentario(long id) {
        comentarioRepository.deleteById(id);
    }
}
