package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.FrequenciaDAO;
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
import java.util.List;

@Named("monitoriaBean")
@SessionScoped
public class monitoriaBean implements Serializable {

    private List<Monitoria> monitorias;

    private Monitoria monitoriaSelecionada;

    private Frequencia frequencia = new Frequencia();

    private final MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
    private final FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    @PostConstruct
    public void listarMonitorias() {
        try {
            UsuarioSistema monitor = UsuarioSistemaSingleton.getInstance();
            if (monitor != null) {
                monitorias = monitoriaDAO.listarPorUsuarioId(monitor.getId());
            }
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar monitorias", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    public void registrarFrequencia(Monitoria monitoria) {
        try {
            UsuarioSistema monitor = UsuarioSistemaSingleton.getInstance();

            Frequencia novaFrequencia = new Frequencia();
            novaFrequencia.setMonitor(monitor); // monitor é do tipo UsuarioSistema
            novaFrequencia.setDataGeracao(LocalDate.now());
            novaFrequencia.setTotalHorasMonitoria(2);

            frequenciaDAO.salvar(novaFrequencia);

            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Frequência registrada com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar frequência", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    // Getters e Setters

    public List<Monitoria> getMonitorias() {
        return monitorias;
    }

    public void setMonitorias(List<Monitoria> monitorias) {
        this.monitorias = monitorias;
    }

    public Monitoria getMonitoriaSelecionada() {
        return monitoriaSelecionada;
    }

    public void setMonitoriaSelecionada(Monitoria monitoriaSelecionada) {
        this.monitoriaSelecionada = monitoriaSelecionada;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }
}
