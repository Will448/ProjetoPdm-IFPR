package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_ocorrencia_funcionario")
public class OcorrenciaFuncionario extends GenericModel {

    @Column(nullable = false, name = "tipo_ocorrencia")
    private String tipoOcorrencia; // Ex: "Advertência", "Elogio", "Falta", etc.

    @Column(nullable = false, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "data_ocorrencia")
    private String dataOcorrencia;

    @Column(nullable = false, name = "responsavel_registro")
    private String responsavelRegistro;

    @Column(nullable = false, name = "gravidade")
    private String gravidade; // Ex: "Leve", "Moderada", "Grave"

    @Column(nullable = false, name = "status")
    private String status; // Ex: "Registrada", "Resolvida", "Em análise"


    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getResponsavelRegistro() {
        return responsavelRegistro;
    }

    public void setResponsavelRegistro(String responsavelRegistro) {
        this.responsavelRegistro = responsavelRegistro;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(String dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
