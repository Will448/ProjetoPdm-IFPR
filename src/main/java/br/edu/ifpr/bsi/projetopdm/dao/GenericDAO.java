package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<Entidade> {

    private Class<Entidade> classe;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.classe = (Class<Entidade>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void salvar(Entidade entidade) {
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            transacao = session.beginTransaction();
            session.persist(entidade); // ou session.save(entidade)
            transacao.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public void inserir(Entidade entidade) {
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            transacao = session.beginTransaction();
            session.persist(entidade);
            transacao.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public void remover(Entidade entidade) {
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            transacao = session.beginTransaction();
            session.remove(entidade);
            transacao.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public void alterarSalvar(Entidade entidade) {
        Transaction transacao = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            transacao = session.beginTransaction();
            session.merge(entidade);
            transacao.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Entidade> listar() {
        List<Entidade> resultado = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entidade> criteria = builder.createQuery(classe);
            Root<Entidade> root = criteria.from(classe);
            criteria.select(root);
            resultado = session.createQuery(criteria).getResultList();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }

        return resultado;
    }

    public Entidade buscarPorId(Long id) {
        Entidade resultado = null;
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entidade> criteria = builder.createQuery(classe);
            Root<Entidade> root = criteria.from(classe);
            criteria.select(root).where(builder.equal(root.get("id"), id));

            List<Entidade> resultados = session.createQuery(criteria).getResultList();
            if (!resultados.isEmpty()) {
                resultado = resultados.get(0);
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }

        return resultado;
    }

}
