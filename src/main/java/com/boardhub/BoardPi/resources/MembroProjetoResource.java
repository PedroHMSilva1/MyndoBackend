package com.boardhub.BoardPi.resources;

import com.boardhub.BoardPi.entities.MembroProjeto;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.services.MembroProjetoService;
import com.boardhub.BoardPi.services.ProjetoService;
import com.boardhub.BoardPi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/membro")
public class MembroProjetoResource {

    @Autowired
    private MembroProjetoService membroProjetoService;
    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<MembroProjeto> listarTodos() {
        return membroProjetoService.getMembrosProjeto();
    }

    @RequestMapping(value = "/{id}")
    public MembroProjeto buscaPorId(@PathVariable long id) {
        return membroProjetoService.getMembroProjeto(id);
    }
    @RequestMapping(value = "/projeto/{id}")
    public List<MembroProjeto> buscaPorProjeto(@PathVariable long id) {
        Projeto p = projetoService.findById(id);
        return membroProjetoService.getPorProjeto(p);
    }
    @RequestMapping(value = "/membro/{idMembro}/projeto/{idProjeto}", method = RequestMethod.POST)
    public MembroProjeto adicionar(@PathVariable long idMembro, @PathVariable long idProjeto, @RequestBody MembroProjeto membroProjeto) {
        membroProjeto.setProjeto(projetoService.findById(idProjeto));
        membroProjeto.setMembro(usuarioService.getUsuario(idMembro));
        return membroProjetoService.addMembroProjeto(membroProjeto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public MembroProjeto alterar(@PathVariable long id, @RequestBody MembroProjeto membroProjeto) {
        membroProjeto.setId(id);
        return membroProjetoService.updateMembroProjeto(membroProjeto);

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remover(@PathVariable long id) {
        MembroProjeto m = membroProjetoService.getMembroProjeto(id);
        if(m != null) {
            membroProjetoService.deleteMembroProjeto(m.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
