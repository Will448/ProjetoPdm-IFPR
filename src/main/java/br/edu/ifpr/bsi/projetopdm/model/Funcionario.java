package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcinario")
public class Funcionario extends  GenericModel{

    /*definir cinco  atributos para o funcionario*/

    @Column(nullable = false, name = "nome_funcionario")
    private String nome;

    @Column(nullable = false, name = "cpf_funcionario")
    private String cpf_func;

    @Column(nullable = false, name = "dta_funcionario")
    private String dta_nascimento;

    @Column(nullable = false, name = "num_funcionario")
    private String numero_telefone;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_func() {
        return cpf_func;
    }

    public void setCpf_func(String cpf_func) {
        this.cpf_func = cpf_func;
    }

    public String getDta_nascimento() {
        return dta_nascimento;
    }

    public void setDta_nascimento(String dta_nascimento) {
        this.dta_nascimento = dta_nascimento;
    }

    public String getNumero_telefone() {
        return numero_telefone;
    }

    public void setNumero_telefone(String numero_telefone) {
        this.numero_telefone = numero_telefone;
    }

    public String getSexo_funcionario() {
        return sexo_funcionario;
    }

    public void setSexo_funcionario(String sexo_funcionario) {
        this.sexo_funcionario = sexo_funcionario;
    }

    @Column(nullable = false, name = "sexo_funcionario")
    private String sexo_funcionario;

}

