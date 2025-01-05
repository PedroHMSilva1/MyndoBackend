package com.boardhub.BoardPi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataAlteracao;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn
    @JsonIgnore
    private Usuario criador;

    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "projeto", orphanRemoval = true)
    @JsonIgnore
    public List<Lista> listas;

    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "projeto", orphanRemoval = true)
    @JsonIgnore
    public List<MembroProjeto> membros;

    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "projeto", orphanRemoval = true)
    @JsonIgnore
    public List<Prioridade> prioridades;

    @Transient
    private long idCriador;

    public Projeto() {
    }

    public Projeto(Long id, String titulo, LocalDateTime dataCriacao, LocalDateTime dataAlteracao, Usuario criador, List<Lista> listas, List<MembroProjeto> membros, List<Prioridade> prioridades) {
        this.id = id;
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
        this.criador = criador;
        this.listas = listas;
        this.membros = membros;
        this.prioridades = prioridades;
        setIdCriador();
    }

    public Projeto(String titulo, Usuario criador) {
        this.titulo = titulo;
        this.criador = criador;
        this.dataAlteracao = LocalDateTime.now();
        this.dataCriacao = LocalDateTime.now();
        this.membros = new ArrayList<>();
        this.listas = new ArrayList<>();
        this.prioridades = new ArrayList<>();
        setIdCriador();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public List<MembroProjeto> getMembros() {
        return membros;
    }

    public void setMembros(List<MembroProjeto> membros) {
        this.membros = membros;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }
    public List<Prioridade> getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(List<Prioridade> prioridades) {
        this.prioridades = prioridades;
    }


    public long getIdCriador() {
        return criador != null? criador.getId() : 0;
    }

    public void setIdCriador() {
        this.idCriador = criador.getId();
    }



    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataAlteracao=" + dataAlteracao +
                ", idCriador=" + criador.getId() +
                ", criador="+criador +
                '}';
    }
}
