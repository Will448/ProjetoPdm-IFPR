package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.dao.EventoDAO;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.Evento;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("professorBean")
@SessionScoped
public class professorBean implements Serializable {

    private List<Monitoria> monitorias;
    private List<Evento> eventosParticipando;

    @PostConstruct
    public void carregarMonitoriasEEventosDoProfessor() {
        UsuarioSistema professorLogado = UsuarioSistemaSingleton.getInstance();
        if (professorLogado != null) {
            MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
            EventoDAO eventoDAO = new EventoDAO();

            this.monitorias = monitoriaDAO.listarPorAlunoId(professorLogado.getId());
            this.eventosParticipando = eventoDAO.listarPorUsuarioId(professorLogado.getId());
        }
    }

    public List<Monitoria> getMonitorias() {
        return monitorias;
    }

    public List<Evento> getEventosParticipando() {
        return eventosParticipando;
    }

    public UsuarioSistema getUsuarioLogado() {
        return UsuarioSistemaSingleton.getInstance();
    }
}