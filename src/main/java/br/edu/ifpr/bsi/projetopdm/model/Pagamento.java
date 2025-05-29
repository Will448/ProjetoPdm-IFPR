package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento extends GenericModel {


    @Column(nullable = false, name = "data_pagamento")
    private String dataPagamento;

    @Column(nullable = false, name = "valor")
    private Double valor;

    @Column(nullable = false, name = "tipo_pagamento")
    private String tipoPagamento;

    @Column(nullable = false, name = "nome_cargo")
    private String nomeCargo;

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }


}