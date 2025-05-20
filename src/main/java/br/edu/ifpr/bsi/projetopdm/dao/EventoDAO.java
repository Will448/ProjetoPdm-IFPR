package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Evento;
import org.hibernate.Session;
import org.hibernate.query.Query;
import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;

public class EventoDAO extends GenericDAO<Evento> {

    public Evento buscarPorTitulo(String titulo) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Evento evento = null;

        try {
            String hql = "FROM Evento e WHERE e.titulo = :titulo";
            Query<Evento> query = session.createQuery(hql, Evento.class);
            query.setParameter("titulo", titulo);
            evento = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar evento por título: " + e.getMessage());
        } finally {
            session.close();
        }

        return evento;
    }
}