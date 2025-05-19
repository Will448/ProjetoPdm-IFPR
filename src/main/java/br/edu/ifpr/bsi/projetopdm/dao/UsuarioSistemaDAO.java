package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UsuarioSistemaDAO extends GenericDAO<UsuarioSistema> {

    public UsuarioSistemaDAO() {
        super();
    }

    public UsuarioSistema buscarPorLogin(String login) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        UsuarioSistema usuario = null;

        try {
            String hql = "FROM UsuarioSistema u WHERE u.login = :login";
            Query<UsuarioSistema> query = session.createQuery(hql, UsuarioSistema.class);
            query.setParameter("login", login);

            usuario = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por login e senha: " + e.getMessage());
        } finally {
            session.close();
        }

        return usuario;
    }
}
