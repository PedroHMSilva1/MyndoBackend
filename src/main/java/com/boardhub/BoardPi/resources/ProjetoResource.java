package com.boardhub.BoardPi.resources;

import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.entities.Usuario;
import com.boardhub.BoardPi.services.ProjetoService;
import com.boardhub.BoardPi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/projeto")
public class ProjetoResource {

    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(projetoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> getAllProjetos() {
        return ResponseEntity.ok(projetoService.findAll());
    }


    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Projeto>> getProjetosByTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(projetoService.findByTitulo(titulo));
    }

    @RequestMapping(value = "/criador/{id}", method = RequestMethod.POST)
    public ResponseEntity adicionar(@PathVariable long id, @RequestBody Projeto projeto) {
        Usuario c = usuarioService.getUsuario(id);
        if(c == null)
            return ResponseEntity.status(404).body(new String("Criador do projeto não foi encontrado"));
        projeto.setCriador(c);
        if(projeto.getTitulo().equals(""))
            projeto.setTitulo(null);
        projeto = projetoService.addProjeto(projeto);
        return ResponseEntity.ok().body(projeto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Projeto alterar(@PathVariable long id, @RequestBody Projeto projeto){
        Projeto p = projetoService.findById(id);
        p.setTitulo(projeto.getTitulo());
        p.setDataAlteracao(projeto.getDataAlteracao());
        if(p.getTitulo().equals(""))
            p.setTitulo(null);
        projetoService.updateProjeto(p);
        return p;
    }
    @RequestMapping(value = "/criador/{id}")
    public List<Projeto> getProjetos(@PathVariable(value = "id") long id){
        try{
            Usuario u = usuarioService.getUsuario(id);
            List<Projeto> lp =  projetoService.findByCriador(u);
            return lp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @RequestMapping(value = "/membro/{id}")
    public List<Projeto> getProjetoParticipando(@PathVariable() long id){
        try{
            Usuario u = usuarioService.getUsuario(id);
            List<Projeto> lp =  projetoService.getProjetosParticipados(u);
            return lp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remover(@PathVariable long id) {
        Projeto p = projetoService.findById(id);
        if(p != null){
            projetoService.deleteProjeto(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
