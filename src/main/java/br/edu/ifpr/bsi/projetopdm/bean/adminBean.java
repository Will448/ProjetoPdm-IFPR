package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.UsuarioSistemaDAO;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named("usuarioSistemaBean")
@SessionScoped
public class adminBean implements Serializable {

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
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar usuários", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    public void remover(ActionEvent evento) {
        this.usuarioSistema = (UsuarioSistema) evento.getComponent().getAttributes().get("usuarioSelecionado");
        try {
            UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
            dao.remover(usuarioSistema);
            listar();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário removido com sucesso", usuarioSistema.getNome());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao remover usuário", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    public void editar(ActionEvent evento) {
        this.usuarioSistema = (UsuarioSistema) evento.getComponent().getAttributes().get("usuarioSelecionado");
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroUsuario.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void novoUsuario() {
        this.usuarioSistema = new UsuarioSistema();
        try {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroUsuario.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar() {
        try {
            UsuarioSistemaDAO dao = new UsuarioSistemaDAO();

            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true); // manter mensagens

            if (usuarioSistema.getId() == null) {
                dao.salvar(usuarioSistema);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com sucesso!"));
            } else {
                dao.alterarSalvar(usuarioSistema);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário atualizado com sucesso!"));
            }

            listar();
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar usuário", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    public void cancelar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao redirecionar", e);
        }
    }

    public UsuarioSistema getUsuarioLogado() {
        return UsuarioSistemaSingleton.getInstance();
    }

    public String getNivelAcessoLogado() {
        UsuarioSistema logado = UsuarioSistemaSingleton.getInstance();
        return logado != null ? logado.getNivelAcesso() : null;
    }
}
