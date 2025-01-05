package com.boardhub.BoardPi.resources;


import com.boardhub.BoardPi.entities.Lista;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.services.ListaService;
import com.boardhub.BoardPi.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/lista")
public class ListaResource {

    @Autowired
    private ListaService listaService;

    @Autowired
    private ProjetoService projetoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Lista> listarTodos() {
        return listaService.getListas();
    }

    @RequestMapping(value = "/{id}")
    public Lista buscaPorId(@PathVariable long id) {
        return listaService.getLista(id);
    }
    @RequestMapping(value = "/projeto/{id}")
    public List<Lista> buscaPorProjeto(@PathVariable long id) {
        Projeto p = projetoService.findById(id);
        return listaService.getPorProjeto(p);
    }

    @RequestMapping(value = "/projeto/{id}", method = RequestMethod.POST)
    public Lista adicionar(@PathVariable long id, @RequestBody Lista lista){
        lista.setProjeto(projetoService.findById(id));
        return listaService.addLista(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Lista alterar(@PathVariable long id, @RequestBody Lista lista) {
        lista.setId(id);
        return listaService.updateLista(lista);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remover(@PathVariable long id) {
        Lista l = listaService.getLista(id);
        if (l != null) {
            listaService.deleteLista(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
