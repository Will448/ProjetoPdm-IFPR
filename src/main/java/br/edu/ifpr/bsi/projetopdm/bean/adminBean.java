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

    // Método para remover um usuário selecionado
    public void remover(ActionEvent evento) {
        this.usuarioSistema = (UsuarioSistema) evento.getComponent().getAttributes().get("usuarioSelecionado");
        try {
            UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
            dao.remover(usuarioSistema);
            listar(); // Atualiza a lista após remoção
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário removido com sucesso", usuarioSistema.getNome());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao remover usuário", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }

    // Método para preparar a edição (pode abrir uma tela ou diálogo, por exemplo)
    public void editar(ActionEvent evento) {
        this.usuarioSistema = (UsuarioSistema) evento.getComponent().getAttributes().get("usuarioSelecionado");
        // Aqui você pode colocar lógica para navegar para a página de edição ou abrir diálogo
        // Exemplo: FacesContext.getCurrentInstance().getExternalContext().redirect("editarUsuario.xhtml");
        System.out.println("Editar usuário: " + usuarioSistema.getNome());
    }

    // Retorna o usuário logado (admin)
    public UsuarioSistema getUsuarioLogado() {
        return UsuarioSistemaSingleton.getInstance();
    }

    // Retorna o nível de acesso do usuário logado
    public String getNivelAcessoLogado() {
        UsuarioSistema logado = UsuarioSistemaSingleton.getInstance();
        return logado != null ? logado.getNivelAcesso() : null;
    }
}
