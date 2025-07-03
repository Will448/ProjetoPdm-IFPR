package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Inscricao;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class InscricaoDAO extends GenericDAO<Inscricao> {

    public List<UsuarioSistema> buscarUsuariosPorMonitoria(Long monitoriaId) {
        try (Session session = HibernateHelper.getFabricaDeSessoes().openSession()) {
            String hql = "SELECT i.aluno FROM Inscricao i WHERE i.monitoria.id = :monitoriaId";
            Query<UsuarioSistema> query = session.createQuery(hql, UsuarioSistema.class);
            query.setParameter("monitoriaId", monitoriaId);
            return query.list();
        }
    }
}
