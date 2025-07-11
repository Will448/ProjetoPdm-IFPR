package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Frequencia;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FrequenciaDAO extends GenericDAO<Frequencia> { // herda os métodos

    public FrequenciaDAO() {
        super();
    }

    public Frequencia buscarPorID(String id) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Frequencia frequencia = null;

        try {
            String hql = "FROM Frequencia f WHERE f.id = :id";
            Query<Frequencia> query = session.createQuery(hql, Frequencia.class);
            query.setParameter("id", Long.parseLong(id)); // conversão de string para long

            frequencia = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar frequência por id: " + e.getMessage());
        } finally {
            session.close();
        }

        return frequencia;
    }
}