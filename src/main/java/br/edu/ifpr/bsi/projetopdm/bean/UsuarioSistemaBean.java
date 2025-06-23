package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.UsuarioSistemaDAO;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("usuarioSistemaBean")
@ViewScoped
public class UsuarioSistemaBean implements Serializable {

    private UsuarioSistema usuarioSistema;
    private List<UsuarioSistema> usuariosSistema;

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public List<UsuarioSistema> getUsuariosSistema() {
        return usuariosSistema;
    }

    public void setUsuariosSistema(List<UsuarioSistema> usuariosSistema) {
        this.usuariosSistema = usuariosSistema;
    }

    @PostConstruct
    public void listar() {
        try {
            UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
            this.usuariosSistema = dao.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar usuários", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    public void remover(ActionEvent evento) {
        this.usuarioSistema = (UsuarioSistema) evento.getComponent().getAttributes().get("usuarioSelecionado");
        System.out.println(usuarioSistema.getNome());
    }

    public void editar(ActionEvent evento) {
        this.usuarioSistema = (UsuarioSistema) evento.getComponent().getAttributes().get("usuarioSelecionado");
        System.out.println(usuarioSistema.getNome());
    }
}
