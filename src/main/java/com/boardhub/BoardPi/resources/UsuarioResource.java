package com.boardhub.BoardPi.resources;

import com.boardhub.BoardPi.dto.LoginDTO;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.entities.Usuario;
import com.boardhub.BoardPi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @RequestMapping(value = "/{id}")
    public Usuario getUsuario(@PathVariable() long id){
        return usuarioService.getUsuario(id);
    }
    @RequestMapping(value = "/email/{email}")
    public List<Usuario> getUsuariosPorEmail(@PathVariable() String email){
        return usuarioService.buscarPorEmail(email);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginDTO> login(@RequestBody Usuario usuario) {
        try {
            LoginDTO u = usuarioService.validaUsuario(usuario.getEmail(), usuario.getSenha());
            if(u == null)
                throw new Exception("Email ou senha incorretos");
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity salvar(@RequestBody Usuario usuario) {
        try {
            usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deletar(@PathVariable long id) {
        try{
            Usuario u = usuarioService.getUsuario(id);
            usuarioService.deleteUsuario(u);
            return ResponseEntity.ok().body(u);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Usuario alterar(@PathVariable long id, @RequestBody Usuario usuario){
        usuario.setId(id);
        usuarioService.updateUsuario(usuario);
        return usuario;
    }
}
