package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Inscricao;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public List<Inscricao> buscarInscricoesPorAluno(UsuarioSistema aluno) {
        try (Session session = HibernateHelper.getFabricaDeSessoes().openSession()) {
            String hql = "FROM Inscricao i WHERE i.aluno.id = :alunoId";
            Query<Inscricao> query = session.createQuery(hql, Inscricao.class);
            query.setParameter("alunoId", aluno.getId());
            return query.list();
        }
    }

    // Novo método para salvar ou atualizar uma inscrição
    public void salvarOuAtualizar(Inscricao inscricao) {
        Transaction transaction = null;
        try (Session session = HibernateHelper.getFabricaDeSessoes().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(inscricao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            // Aqui você pode lançar uma RuntimeException ou logar o erro conforme necessidade
        }
    }
}
