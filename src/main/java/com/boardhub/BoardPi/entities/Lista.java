package com.boardhub.BoardPi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Lista {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private int maxTarefas;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn
    @JsonIgnore
    private Projeto projeto;

    @OneToMany (mappedBy = "lista", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Tarefa> tarefas;

    @Transient
    private long idProjeto;

    public Lista(Long id, String titulo, int maxTarefas, Projeto projeto) {
        this.id = id;
        this.titulo = titulo;
        this.maxTarefas = maxTarefas;
        this.projeto = projeto;
        setIdProjeto();
    }

    public Lista() {
    }

    public Lista(String titulo, int maxTarefas, Projeto projeto) {
        this.titulo = titulo;
        this.maxTarefas = maxTarefas;
        this.projeto = projeto;
        setIdProjeto();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getMaxTarefas() {
        return maxTarefas;
    }

    public void setMaxTarefas(int maxTarefas) {
        this.maxTarefas = maxTarefas;
    }


    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getIdProjeto() {
        return projeto != null? projeto.getId() : 0;
    }

    public void setIdProjeto() {
        this.idProjeto = this.projeto.getId();
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", maxTarefas=" + maxTarefas +
                ", idProjeto=" + projeto.getId() +
                ", projeto=" + projeto +
                '}';
    }
}
