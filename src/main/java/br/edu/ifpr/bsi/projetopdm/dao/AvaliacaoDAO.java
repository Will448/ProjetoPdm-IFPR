package br.edu.ifpr.bsi.projetopdm.dao;
import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Avaliacao;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AvaliacaoDAO extends GenericDAO<Avaliacao>{

    public AvaliacaoDAO() {
        super();
    }

    public Avaliacao buscarPorID (String id){
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Avaliacao avaliacao = null;

        try {
            String hql = "FROM Avaliacao u WHERE u.id = :id";
            Query<Avaliacao> query = session.createQuery(hql, Avaliacao.class);
            query.setParameter("id", id);

            avaliacao = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por id: " + e.getMessage());
        } finally {
            session.close();
        }

        return avaliacao;
    }
}