package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_certificado")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioSistema participante;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String data;

    @Column
    private String duracao;

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioSistema getParticipante() {
        return participante;
    }

    public void setParticipante(UsuarioSistema participante) {
        this.participante = participante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
