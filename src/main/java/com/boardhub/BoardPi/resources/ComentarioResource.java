package com.boardhub.BoardPi.resources;

import com.boardhub.BoardPi.entities.Comentario;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.services.ComentarioService;
import com.boardhub.BoardPi.services.TarefaService;
import com.boardhub.BoardPi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/comentario")
public class ComentarioResource {
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Comentario> listarTodos() {
        return comentarioService.getComentarios();
    }
    @RequestMapping(value = "/tarefa/{id}", method = RequestMethod.GET)
    public List<Comentario> buscaPorTarefa(@PathVariable long id) {
        Tarefa t = tarefaService.getTarefa(id);
        return comentarioService.getPorTarefa(t);
    }

    @RequestMapping(value = "/{id}")
    public Comentario buscaPorId(@PathVariable long id) {
        return comentarioService.getComentario(id);
    }

    @RequestMapping(value = "/tarefa/{idTarefa}/criador/{idCriador}", method = RequestMethod.POST)
    public Comentario adicionar(@PathVariable long idTarefa, @PathVariable long idCriador, @RequestBody Comentario comentario) {
        comentario.setCriador(usuarioService.getUsuario(idCriador));
        comentario.setTarefa(tarefaService.getTarefa(idTarefa));
        return comentarioService.addComentario(comentario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Comentario alterar(@PathVariable long id, @RequestBody Comentario comentario) {
        comentario.setId(id);
        return comentarioService.updateComentario(comentario);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remover(@PathVariable long id) {
        Comentario c = comentarioService.getComentario(id);
        if (c != null) {
            comentarioService.deleteComentario(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
