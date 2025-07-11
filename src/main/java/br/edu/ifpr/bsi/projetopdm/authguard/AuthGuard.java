package br.edu.ifpr.bsi.projetopdm.authguard;

import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/pages/admin.xhtml",
        "/pages/monitor.xhtml",
        "/pages/coordenador.xhtml",
        "/pages/aluno.xhtml",
        "/pages/monitoria.xhtml",
        "/pages/NovaMonitoria.xhtml",
        "/pages/evento.xhtml",
        "/pages/editar.xhtml",
        "/pages/frequencia.xhtml",
        "/pages/minhasInscricoes.xhtml"

        // Adicione outras páginas protegidas aqui, EXCETO login.xhtml
})
public class AuthGuard implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthGuard inicializado");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        System.out.println("=== DEBUG AuthGuard ===");
        System.out.println("Request URI: " + requestURI);
        System.out.println("Context Path: " + contextPath);

        // Obtém o usuário logado do Singleton
        UsuarioSistema usuario = UsuarioSistemaSingleton.getInstance();

        // Verifica se o usuário está logado
        if (usuario == null) {
            System.out.println("AuthGuard inicializado");
            System.out.println("Usuário não autenticado. Redirecionando para login...");

            // Monta o caminho correto para login
            String loginPath = contextPath + "/pages/login.xhtml";
            System.out.println("Redirecionando para: " + loginPath);

            httpResponse.sendRedirect(loginPath);
            return;
        }

        // Verifica acesso baseado na URL e nível do usuário
        String nivelAcesso = usuario.getNivelAcesso().toUpperCase();

        if (!isAutorizado(requestURI, nivelAcesso)) {
            System.out.println("Acesso negado para " + usuario.getNome() +
                    " (nível: " + nivelAcesso + ") na página: " + requestURI);

            // Em vez de erro 403, redireciona para uma página de "acesso negado" ou login
            String loginPath = contextPath + "/pages/login.xhtml?erro=acesso_negado";
            httpResponse.sendRedirect(loginPath);
            return;
        }

        // Se passou por todas as verificações, permite o acesso
        System.out.println("Acesso permitido para " + usuario.getNome() +
                " (nível: " + nivelAcesso + ") na página: " + requestURI);
        chain.doFilter(request, response);
    }

    private boolean isAutorizado(String requestURI, String nivelAcesso) {
        System.out.println("Verificando autorização para: " + requestURI + " com nível: " + nivelAcesso);

        // Regras de autorização baseadas na URL
        if (requestURI.contains("/admin.xhtml")) {
            return "ADMIN".equals(nivelAcesso);
        }

        if (requestURI.contains("/coordenador.xhtml")) {
            return "COORDENADOR".equals(nivelAcesso) || "ADMIN".equals(nivelAcesso);
        }

        if (requestURI.contains("/monitor.xhtml")) {
            return "MONITOR".equals(nivelAcesso) ||
                    "COORDENADOR".equals(nivelAcesso) ||
                    "ADMIN".equals(nivelAcesso);
        }

        // Por padrão, permite acesso
        return true;
    }

    @Override
    public void destroy() {
        System.out.println("AuthGuard destruído");
    }
}