package com.boardhub.BoardPi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Prioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne (cascade = CascadeType.REFRESH)
    @JsonIgnore
    private Projeto projeto;

    @Transient
    private long idProjeto;

    @OneToMany(orphanRemoval = false, cascade = CascadeType.MERGE, mappedBy = "tagPrioridade")
    @JsonIgnore
    public List<Tarefa> tarefas;

    @Column
    private String prioridade;

    @Column
    private String cor;

    public Prioridade(Long id, Projeto projeto, String prioridade, String cor) {
        this.id = id;
        this.projeto = projeto;
        this.prioridade = prioridade;
        this.cor = cor;
        setIdProjeto();
    }
    public Prioridade() {

    }

    public Prioridade(Projeto projeto,String prioridade, String cor) {
        this.projeto = projeto;
        this.prioridade = prioridade;
        this.cor = cor;
        setIdProjeto();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public long getIdProjeto() {
        return projeto != null? projeto.getId() : 0;
    }

    public void setIdProjeto() {
        this.idProjeto = projeto.getId();
    }

    @Override
    public String toString() {
        return "Prioridade{" +
                "id=" + id +
                ", projeto=" + projeto +
                ", idProjeto=" + getIdProjeto() +
                ", prioridade='" + prioridade + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }
}
