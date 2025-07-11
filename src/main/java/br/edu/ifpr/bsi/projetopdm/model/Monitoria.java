package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_monitoria")
public class Monitoria extends GenericModel {

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String dataHora;

    @Column
    private String local;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificado_id")
    private Certificado certificado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private UsuarioSistema usuarioSistema;

    // Construtor padrão inicializando o Certificado
    public Monitoria() {
        this.certificado = new Certificado();
    }

    // Getters e Setters

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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }
}
