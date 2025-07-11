
package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Avaliacao;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.hibernate.Session;

import java.util.List;

public class AvaliacaoDAO extends GenericDAO<Avaliacao> {

    public AvaliacaoDAO() {
        super();
    }

    public List<Avaliacao> buscarAvaliacoesPorAluno(UsuarioSistema aluno) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<Avaliacao> resultado = null;


        try {
            resultado = session
                    .createQuery("FROM Avaliacao a WHERE a.aluno = :aluno", Avaliacao.class)
                    .setParameter("aluno", aluno)
                    .getResultList();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }

        return resultado;
    }
}
