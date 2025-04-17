package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_departamento")
public class Departamento extends GenericModel {
    @Column(nullable = false, name = "nome_departamento")
    private String nome;

    @Column(nullable = false, name = "ramal")
    private String ramal;

    @Column(nullable = false, name = "responsavel")
    private String responsavel;


    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }




}
