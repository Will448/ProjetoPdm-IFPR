package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends  GenericModel{

    /*definir cinco  atributos para o funcionario*/

    @Column( name = "nome_funcionario")//nulltable não permite que o valor seja nulo
    private String nome;

    @Column( name = "cpf_funcionario")
    private String cpf_func;

    @Column( name = "dta_funcionario")
    private String dta_nascimento;

    @Column(name = "num_funcionario")
    private String numero_telefone;

    @Column( name = "sexo_funcionario")
    private String sexo_funcionario;

    @JoinColumn //é uma coluna de junçao
    @ManyToOne //N,M um para  muitos para verificar e real
    private Cargo cargo;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)//é usado para alterar os dados de pagamento dentro do funcionario
    public Pagamento pagamento;

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

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}

