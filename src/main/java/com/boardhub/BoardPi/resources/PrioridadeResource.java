package com.boardhub.BoardPi.resources;

import com.boardhub.BoardPi.entities.Prioridade;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.services.PrioridadeService;
import com.boardhub.BoardPi.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/prioridade")
public class PrioridadeResource {
    @Autowired
    private PrioridadeService prioridadeService;
    @Autowired
    private ProjetoService projetoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Prioridade> listarTodos() {
        return prioridadeService.getPrioridades();
    }

    @RequestMapping(value = "/{id}")
    public Prioridade buscaPorId(@PathVariable long id) {
        return prioridadeService.getPrioridade(id);
    }

    @RequestMapping(value = "/projeto/{id}", method = RequestMethod.POST)
    public Prioridade adicionar(@PathVariable long id, @RequestBody Prioridade prioridade) {
        prioridade.setProjeto(projetoService.findById(id));
        return prioridadeService.addPrioridade(prioridade);
    }
    @RequestMapping(value = "/projeto/{id}", method = RequestMethod.GET)
    public List<Prioridade> adicionar(@PathVariable long id) {
        Projeto p = projetoService.findById(id);
        return prioridadeService.getPorProjeto(p);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Prioridade alterar(@PathVariable long id, @RequestBody Prioridade prioridade){
        prioridade.setId(id);
        return prioridadeService.updatePrioridade(prioridade);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remover(@PathVariable long id) {
        Prioridade p = prioridadeService.getPrioridade(id);
        if (p != null) {
            prioridadeService.deletePrioridade(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
