package com.boardhub.BoardPi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCadastro;

    @OneToMany (mappedBy = "criador", orphanRemoval = true, cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Projeto> projetos;

    @OneToMany (mappedBy = "criador", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Comentario> comentarios;

    @OneToMany (mappedBy = "criador")
    @JsonIgnore
    public List<Tarefa> tarefasCriadas;

    @OneToMany (mappedBy = "responsavel")
    @JsonIgnore
    public List<Tarefa> tarefasResponsavel;


    @OneToMany (mappedBy = "membro",  orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MembroProjeto> membroDe;

    public Usuario() {

    }

    public Usuario(Long id, String nome, String email, String senha, LocalDateTime dataCadastro, List<Projeto> projetos, List<MembroProjeto> membroDe) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.projetos = projetos;
        this.membroDe = membroDe;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
        this.projetos = new ArrayList<>();
        this.membroDe = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<MembroProjeto> getMembroDe() {
        return membroDe;
    }

    public void setMembroDe(List<MembroProjeto> membroDe) {
        this.membroDe = membroDe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
