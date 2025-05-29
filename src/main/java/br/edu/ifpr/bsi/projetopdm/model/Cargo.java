package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_cargo")
public class Cargo extends GenericModel{

    /*definir cinco  atributos para o funcionario*/

    @Column(nullable = false, name = "nome_cargo")
    private String nome_cargo;

    @JoinColumn
    @OneToMany
    private List<Funcionario> funcionarios;

    @Column(nullable = false, name = "descricao_cargo")
    private String descricao_cargo;

    @Column(nullable = false, name = "carga_horaria")
    private float carga_horaria;

    @Column(nullable = false, name = "departamento")
    private String departamento;

    @Column(nullable = false, name = "contrato_trabalho")
    private String ContratoTrabalho;


    public String getNome_cargo() {
        return nome_cargo;
    }

    public void setNome_cargo(String nome_cargo) {
        this.nome_cargo = nome_cargo;
    }

    public String getDescricao_cargo() {
        return descricao_cargo;
    }

    public void setDescricao_cargo(String descricao_cargo) {
        this.descricao_cargo = descricao_cargo;
    }

    public float getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(float carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getContratoTrabalho() {
        return ContratoTrabalho;
    }

    public void setContratoTrabalho(String contratoTrabalho) {
        ContratoTrabalho = contratoTrabalho;
    }
}

