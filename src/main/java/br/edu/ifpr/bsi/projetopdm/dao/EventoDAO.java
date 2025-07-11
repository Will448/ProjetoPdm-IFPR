
package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Evento;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EventoDAO extends GenericDAO<Evento> {

    public EventoDAO() {
        super();
    }

    /**
     * Busca um evento pelo título exato.
     * @param titulo Título do evento.
     * @return Evento encontrado ou null.
     */
    public Evento buscarPorTitulo(String titulo) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Evento evento = null;

        try {
            String hql = "FROM Evento e WHERE e.titulo = :titulo";
            Query<Evento> query = session.createQuery(hql, Evento.class);
            query.setParameter("titulo", titulo);
            evento = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return evento;
    }

    /**
     * Lista todos os eventos onde o usuário está como participante.
     * @param usuarioId ID do usuário.
     * @return Lista de eventos.
     */
    public List<Evento> listarPorUsuarioId(Long usuarioId) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<Evento> lista = null;

        try {
            String hql = "SELECT e FROM Evento e JOIN e.participantes p WHERE p.id = :usuarioId";
            Query<Evento> query = session.createQuery(hql, Evento.class);
            query.setParameter("usuarioId", usuarioId);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lista;
    }
}
