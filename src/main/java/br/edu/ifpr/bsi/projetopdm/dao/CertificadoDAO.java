package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Certificado;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CertificadoDAO {

    public CertificadoDAO() {
        super();
    }

    public Certificado buscarPorTitulo (String titulo){
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Certificado certificado = null;

        try {
            String hql = "FROM Certificado u WHERE u.titulo = :titulo";
            Query<Certificado> query = session.createQuery(hql, Certificado.class);
            query.setParameter("titulo", titulo);

            certificado = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por titulo: " + e.getMessage());
        } finally {
            session.close();
        }

        return certificado;
    }
}
