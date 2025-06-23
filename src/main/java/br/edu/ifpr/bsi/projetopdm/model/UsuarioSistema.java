package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_sistema")
public class UsuarioSistema extends GenericModel {

    @Column(nullable = false, unique = true, name = "ra")
    private String ra;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "telefone")
    private String telefone;

    @Column(nullable = false, name = "curso")
    private String curso;

    @Column(nullable = false, name = "periodo_academico")
    private String periodoAcademico;

    @Column(nullable = false, name = "login")
    private String login;

    @Column(nullable = false, name = "senha")
    private String senha;

    @Column(nullable = false, name = "nivel_acesso")
    private String nivelAcesso; // ALUNO, MONITOR, COORDENADOR, ADMIN

    // Getters e Setters
    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

}