package com.boardhub.BoardPi.repositories;

import com.boardhub.BoardPi.entities.Projeto;
import com.boardhub.BoardPi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    Optional<Projeto> findById(Long id);
    List<Projeto> findAll();
    List<Projeto> findByTitulo(String titulo);
    List<Projeto> findByCriador(Usuario criador);
    //@Query("select p from Projeto p where p.criador = :u")
    //public List<Projeto> findProjectsByUsuario(Usuario u);

    @Query("select p from Projeto p inner join MembroProjeto mp on mp.membro = :u where mp.projeto = p")
    public List<Projeto> findProjectsByMembro(Usuario u);

    @Modifying
    @Query("update Projeto p set p.dataAlteracao = :dataAlt, p.titulo = :titulo where p.id = :id")
    void updateProjeto(LocalDateTime dataAlt, String titulo, long id);
}
