package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contrato_trabalho")
public class ContratoTrabalho extends GenericModel {

    @Column(nullable = false, name = "tipo_contrato")
    private String tipoContrato;

    @Column(nullable = false, name = "data_inicio")
    private String dataInicio;

    @Column(nullable = false, name = "salario_base")
    private float salario_base;

    @Column(nullable = false, name = "data_fimm")
    private String dataFim;


    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public float getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(float salario_base) {
        this.salario_base = salario_base;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

}