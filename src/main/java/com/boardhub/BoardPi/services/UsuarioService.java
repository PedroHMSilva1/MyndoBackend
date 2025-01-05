package com.boardhub.BoardPi.services;

import com.boardhub.BoardPi.dto.LoginDTO;
import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.entities.Tarefa;
import com.boardhub.BoardPi.entities.Usuario;
import com.boardhub.BoardPi.repositories.UsuarioRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuario(long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public void updateUsuario(Usuario usuario){
        usuarioRepository.updManual(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getId());
    }

    @Transactional
    public void deleteUsuario(Usuario u){
        //usuarioRepository.deleteUsuarioDeMembro(u);
        //usuarioRepository.deleteComentarios(u);
        //usuarioRepository.updateCriador(u);
        usuarioRepository.updateTarefaDoUsuario(u);
        usuarioRepository.updateResponsabilidadeDoUsuario(u);
        usuarioRepository.deleteById(u.getId());
    }
    public void saveUsuario(Usuario usuario){
        usuarioRepository.saveManual(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDataCadastro());
    }

    public LoginDTO validaUsuario(String email, String senha) throws Exception{
        Optional<Tuple> result = usuarioRepository.validateUser(email, senha);

        if (result.isPresent()) {
            LoginDTO dto;
            dto = result.stream()
                    .map(l -> new LoginDTO(l.get(0, Long.class), l.get(1, String.class)))
                    .findFirst().orElse(null);

            return dto;
        }
        return null;
    }
    public List<Usuario> buscarPorEmail(String email){
        email = email + "%";
        return usuarioRepository.findAllByEmail(email);
    }
}
