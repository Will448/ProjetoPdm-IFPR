package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.FrequenciaDAO;
import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.dao.UsuarioSistemaDAO;
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

    private Monitoria monitoria = new Monitoria();  // ✅ CAMPO ADICIONADO

    private Frequencia frequencia = new Frequencia();

    private final MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
    private final FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    @PostConstruct
    public void listarMonitorias() {
       try {
            UsuarioSistema professor = UsuarioSistemaSingleton.getInstance();
            if (professor != null) {
                monitorias = monitoriaDAO.listarPorUsuarioId(professor.getId());
            }

            // Exemplo de buscar todos monitores (mude conforme sua DAO)
            monitores = monitoriaDAO.buscarTodosMonitores(); // Crie esse método na DAO se ainda não tiver
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar monitorias/monitores", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }



    public void registrarFrequencia(Monitoria monitoria) {
        try {
            UsuarioSistema monitor = UsuarioSistemaSingleton.getInstance();

            Frequencia novaFrequencia = new Frequencia();
            novaFrequencia.setMonitor(monitor);
            novaFrequencia.setMonitoria(monitoria);
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
    public void salvar() {
        try {
            UsuarioSistema professor = UsuarioSistemaSingleton.getInstance();
            monitoria.setUsuarioSistema(professor); // Define o usuário atual como responsável pela monitoria

            monitoriaDAO.salvar(monitoria); // Salva a nova monitoria

            monitoria = new Monitoria(); // Limpa o formulário
            listarMonitorias(); // Atualiza listas após salvar

            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Monitoria salva com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar monitoria", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    private List<UsuarioSistema> monitores; // nova lista
    private UsuarioSistema monitorSelecionado; // monitor escolhido
    public String editarMonitoria(Monitoria m) {
        this.monitoria = m;
        return "/pages/NovaMonitoria.xhtml?faces-redirect=true";
    }
    public void listarTodasMonitorias() {
        try {
            this.monitorias = monitoriaDAO.listarTodas();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar todas as monitorias", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }
    public void removerMonitoria(Monitoria monitoria) {
        try {
            monitoriaDAO.remover(monitoria);
            listarTodasMonitorias(); // Atualiza a lista após remover

            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Monitoria removida com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao remover monitoria", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    // Getters e setters:
    public List<UsuarioSistema> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<UsuarioSistema> monitores) {
        this.monitores = monitores;
    }

    public UsuarioSistema getMonitorSelecionado() {
        return monitorSelecionado;
    }

    public void setMonitorSelecionado(UsuarioSistema monitorSelecionado) {
        this.monitorSelecionado = monitorSelecionado;
    }

    // ✅ GETTERS E SETTERS NOVOS

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    // Getters e Setters já existentes

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
