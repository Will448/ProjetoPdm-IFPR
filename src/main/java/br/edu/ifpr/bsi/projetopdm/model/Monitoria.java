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
        private String Local;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)//é usado para alterar os dados de pagamento dentro do monitor
    public UsuarioSistema usuarioSistema;

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    @Column
        private String certificado;


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


}
