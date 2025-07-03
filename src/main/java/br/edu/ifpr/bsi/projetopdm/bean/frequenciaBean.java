package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.FrequenciaDAO;
import br.edu.ifpr.bsi.projetopdm.dao.InscricaoDAO;
import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.model.Frequencia;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("frequenciaBean")
@SessionScoped
public class frequenciaBean implements Serializable {

    private Frequencia frequencia;
    private List<Monitoria> monitorias;
    private Monitoria monitoriaSelecionada;

    private List<UsuarioSistema> usuariosInscritos;

    private UsuarioSistema alunoSelecionado;

    @PostConstruct
    public void init() {
        frequencia = new Frequencia();
        monitoriaSelecionada = new Monitoria();
        usuariosInscritos = new ArrayList<>();
        carregarMonitorias();
    }

    public void carregarMonitorias() {
        try {
            MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
            UsuarioSistema monitor = UsuarioSistemaSingleton.getInstance();
            monitorias = monitoriaDAO.listarPorUsuarioId(monitor.getId());
        } catch (Exception e) {
            monitorias = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar monitorias", e.getMessage()));
        }
    }

    public String prepararRegistro(Monitoria monitoria) {
        this.monitoriaSelecionada = monitoria;
        this.frequencia = new Frequencia();
        this.frequencia.setMonitor(monitoria.getUsuarioSistema());
        this.frequencia.setMonitoria(monitoria);
        this.frequencia.setDataGeracao(LocalDate.now());

        try {
            InscricaoDAO inscricaoDAO = new InscricaoDAO();
            this.usuariosInscritos = inscricaoDAO.buscarUsuariosPorMonitoria(monitoria.getId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar inscritos", e.getMessage()));
        }

        return "/pages/frequencia.xhtml?faces-redirect=true";
    }

    public void registrarFrequencia() {
        try {
            frequencia.setAluno(alunoSelecionado);
            FrequenciaDAO dao = new FrequenciaDAO();
            dao.salvar(frequencia);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Frequência registrada com sucesso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar frequência", e.getMessage()));
        }
    }

    // Getters e Setters

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    public List<Monitoria> getMonitorias() {
        return monitorias;
    }

    public Monitoria getMonitoriaSelecionada() {
        return monitoriaSelecionada;
    }

    public void setMonitoriaSelecionada(Monitoria monitoriaSelecionada) {
        this.monitoriaSelecionada = monitoriaSelecionada;
    }

    public List<UsuarioSistema> getUsuariosInscritos() {
        return usuariosInscritos;
    }

    public void setUsuariosInscritos(List<UsuarioSistema> usuariosInscritos) {
        this.usuariosInscritos = usuariosInscritos;
    }

    public UsuarioSistema getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(UsuarioSistema alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }
}
