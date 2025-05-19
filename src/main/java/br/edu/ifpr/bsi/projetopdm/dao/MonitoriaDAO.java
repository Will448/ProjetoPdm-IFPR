package br.edu.ifpr.bsi.projetopdm.dao;
import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MonitoriaDAO extends GenericDAO<Monitoria> {


    public MonitoriaDAO() {
        super();
    }

    public Monitoria buscarPorTitulo (String titulo){
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Monitoria monitoria = null;

        try {
            String hql = "FROM Monitoria u WHERE u.titulo = :titulo";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            query.setParameter("titulo", titulo);

            monitoria = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por titulo: " + e.getMessage());
        } finally {
            session.close();
        }

        return monitoria;
    }

}
