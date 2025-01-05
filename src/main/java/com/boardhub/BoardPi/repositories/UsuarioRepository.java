package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.dto.LoginDTO;
import com.boardhub.BoardPi.entities.Usuario;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query(value = "SELECT id, email FROM usuario WHERE email = :email AND senha = sha2(:senha, 224)", nativeQuery = true)
    Optional<Tuple> validateUser(@Param("email") String email, @Param("senha") String senha);

    @Query("select u from Usuario u where u.email like :email")
    List<Usuario> findAllByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES (:nome, :email, sha2(:senha, 224), :dataCadastro)", nativeQuery = true)
    void saveManual(@Param("nome") String nome,
                       @Param("email") String email,
                       @Param("senha") String senha,
                       @Param("dataCadastro") LocalDateTime dataCadastro);
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario set nome = :nome, email = :email, senha = sha2(:senha, 224) where id = :id", nativeQuery = true)
    void updManual(@Param("nome") String nome,
                    @Param("email") String email,
                    @Param("senha") String senha,
                    @Param("id") long id);



    @Modifying
    @Query("update Tarefa t set t.criador = null where t.criador = :usuario")
    public void updateTarefaDoUsuario(Usuario usuario);

    @Modifying
    @Query("update Tarefa t set t.responsavel = null where t.responsavel = :usuario")
    public void updateResponsabilidadeDoUsuario(Usuario usuario);



}
