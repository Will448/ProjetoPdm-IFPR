package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.InscricaoDAO;
import br.edu.ifpr.bsi.projetopdm.model.Inscricao;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Named("inscricaoBean")
@SessionScoped
public class InscricaoBean implements Serializable {

    private List<Inscricao> minhasInscricoes = new ArrayList<>();

    @Inject
    private avaliacaoBean avaliacaoBean;  // injetado para usar prepararAvaliacao

    @PostConstruct
    public void init() {
        atualizarMinhasInscricoes();
    }

    public void atualizarMinhasInscricoes() {
        UsuarioSistema aluno = UsuarioSistemaSingleton.getInstance();
        if (aluno != null) {
            InscricaoDAO dao = new InscricaoDAO();
            minhasInscricoes = dao.buscarInscricoesPorAluno(aluno);
        } else {
            minhasInscricoes = new ArrayList<>();
        }
    }

    public void prepararAvaliacao(Inscricao inscricao) {
        avaliacaoBean.prepararAvaliacao(inscricao);
    }

    // Getter e setter
    public List<Inscricao> getMinhasInscricoes() {
        return minhasInscricoes;
    }

    public void setMinhasInscricoes(List<Inscricao> minhasInscricoes) {
        this.minhasInscricoes = minhasInscricoes;
    }
}
