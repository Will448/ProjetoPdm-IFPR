package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.UsuarioSistemaDAO;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void logar() {
        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
        UsuarioSistema usuario = dao.buscarPorLogin(login);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            UsuarioSistemaSingleton.setInstance(usuario);

            try {
                FacesContext context = FacesContext.getCurrentInstance();
                String contextPath = context.getExternalContext().getRequestContextPath();

                switch (usuario.getNivelAcesso().toUpperCase()) {
                    case "ALUNO":
                        context.getExternalContext().redirect(contextPath + "/pages/aluno.xhtml");
                        System.out.println("login no aluno feito com sucesso!");
                        break;
                    case "MONITOR":
                        context.getExternalContext().redirect(contextPath + "/pages/monitor.xhtml");
                        System.out.println("login no monitor feito com sucesso!");
                        break;
                    case "COORDENADOR":
                        context.getExternalContext().redirect(contextPath + "/pages/coordenador.xhtml");
                        System.out.println("login no monitor feito com sucesso!");
                        break;
                    case "ADMIN":
                        context.getExternalContext().redirect(contextPath + "/pages/admin.xhtml");
                        System.out.println("login no admin feito com sucesso!");
                        break;
                    default:
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Nível de acesso inválido", ""));
                }
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no redirecionamento", e.getMessage()));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos", ""));
        }
    }

    public void logout() {

            // 1. Primeiro limpa o singleton
            UsuarioSistemaSingleton.resetInstance();

            FacesContext facesContext = FacesContext.getCurrentInstance();
            String contextPath = facesContext.getExternalContext().getRequestContextPath();

            try {
                // 2. Faz o redirect ANTES de invalidar a sessão
                String logoutURL = contextPath + "/pages/index.xhtml";
                facesContext.getExternalContext().redirect(logoutURL);

                // 3. Invalida a sessão DEPOIS do redirect
                facesContext.getExternalContext().invalidateSession();

                System.out.println("Logout feito com sucesso!");
            } catch (IOException e) {
                System.out.println("ERRO no logout: " + e.getMessage());
                e.printStackTrace();
            }
            }

}


