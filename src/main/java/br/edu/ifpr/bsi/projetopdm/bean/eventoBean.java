package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.EventoDAO;
import br.edu.ifpr.bsi.projetopdm.dao.UsuarioSistemaDAO;
import br.edu.ifpr.bsi.projetopdm.model.Evento;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("eventoBean")
@SessionScoped
public class eventoBean implements Serializable {

    private Evento evento = new Evento();
    private List<Evento> eventos = new ArrayList<>();
    private List<UsuarioSistema> usuarios = new ArrayList<>();

    @PostConstruct
    public void init() {
        carregarEventos();
        carregarUsuarios();
    }

    public void salvarEvento() {
        EventoDAO dao = new EventoDAO();
        dao.salvar(evento);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Evento salvo com sucesso!"));

        evento = new Evento();

        // Redireciona para a lista
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("coordenador.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editarEvento(Evento eventoSelecionado) {
        this.evento = eventoSelecionado;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("evento.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removerEvento(Evento evento) {
        EventoDAO dao = new EventoDAO();
        dao.remover(evento);
        carregarEventos();
    }

    public void selecionarEvento(Evento eventoSelecionado) {
        this.evento = eventoSelecionado;
    }

    public void limparEvento() {
        this.evento = new Evento();
    }

    public void carregarEventos() {
        EventoDAO dao = new EventoDAO();
        eventos = dao.listar();
    }

    public void carregarUsuarios() {
        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
        usuarios = dao.listar();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<UsuarioSistema> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioSistema> usuarios) {
        this.usuarios = usuarios;
    }
}
