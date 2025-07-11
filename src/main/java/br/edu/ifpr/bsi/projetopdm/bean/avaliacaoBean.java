package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.AvaliacaoDAO;
import br.edu.ifpr.bsi.projetopdm.model.Avaliacao;
import br.edu.ifpr.bsi.projetopdm.model.Inscricao;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("avaliacaoBean")
@ViewScoped
public class avaliacaoBean implements Serializable {

    private Avaliacao avaliacao = new Avaliacao();
    private List<Avaliacao> minhasAvaliacoes = new ArrayList<>();

    @PostConstruct
    public void init() {
        UsuarioSistema aluno = UsuarioSistemaSingleton.getInstance();
        buscarMinhasAvaliacoes(aluno);
    }

    // Adicione método para preparar avaliação para uma inscrição
    public void prepararAvaliacao(Inscricao inscricaoSelecionada) {
        avaliacao = new Avaliacao();
        avaliacao.setAluno(UsuarioSistemaSingleton.getInstance());
        avaliacao.setMonitoria(inscricaoSelecionada.getMonitoria());
        //avaliacao.setNota(0.0);
    }

    // Salvar avaliação usando os dados preenchidos no objeto avaliacao
    public void salvarAvaliacao() {
        System.out.println("Nota recebida: " + avaliacao.getNota());
        System.out.println("Nota enviada do form: " + avaliacao.getNota());
// Verifique se a nota chega corretamente
        avaliacao.setData(LocalDate.now());

        AvaliacaoDAO dao = new AvaliacaoDAO();
        dao.salvar(avaliacao);

        buscarMinhasAvaliacoes(UsuarioSistemaSingleton.getInstance());

        avaliacao = new Avaliacao();
    }

    public void buscarMinhasAvaliacoes(UsuarioSistema aluno) {
        AvaliacaoDAO dao = new AvaliacaoDAO();
        minhasAvaliacoes = dao.buscarAvaliacoesPorAluno(aluno);
    }

    // Getters e setters
    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Avaliacao> getMinhasAvaliacoes() {
        return minhasAvaliacoes;
    }

    public void setMinhasAvaliacoes(List<Avaliacao> minhasAvaliacoes) {
        this.minhasAvaliacoes = minhasAvaliacoes;
    }
}
