package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("monitorBean")
@SessionScoped
public class monitorBean implements Serializable {

    private List<Monitoria> monitorias;

    public List<Monitoria> getMonitorias() {
        return monitorias;
    }

    @PostConstruct
    public void carregarMonitoriasDoUsuario() {
        UsuarioSistema usuarioLogado = UsuarioSistemaSingleton.getInstance();
        if (usuarioLogado != null) {
            MonitoriaDAO dao = new MonitoriaDAO();
            this.monitorias = dao.listarPorUsuarioId(usuarioLogado.getId());
        }
    }

    public UsuarioSistema getUsuarioLogado() {
        return UsuarioSistemaSingleton.getInstance();
    }
}
