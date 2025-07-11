package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MonitoriaDAO extends GenericDAO<Monitoria> {

    public MonitoriaDAO() {
        super();
    }

    public Monitoria buscarPorTitulo(String titulo) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Monitoria monitoria = null;

        try {
            String hql = "FROM Monitoria m WHERE m.titulo = :titulo";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            query.setParameter("titulo", titulo);
            monitoria = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


        return monitoria;
    }

    public List<Monitoria> listarPorAlunoId(Long alunoId) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<Monitoria> lista = null;

        try {
            String hql = "SELECT m FROM Monitoria m JOIN m.alunosInscritos a WHERE a.id = :alunoId";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            query.setParameter("alunoId", alunoId);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lista;
    }

    // ✅ Método necessário para os beans monitorBean e frequenciaBean
    public List<Monitoria> listarPorUsuarioId(Long usuarioId) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<Monitoria> lista = null;

        try {
            String hql = "FROM Monitoria m WHERE m.usuarioSistema.id = :usuarioId";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            query.setParameter("usuarioId", usuarioId);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lista;
    }
    public List<UsuarioSistema> buscarTodosMonitores() {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<UsuarioSistema> lista = null;

        try {
            String hql = "FROM UsuarioSistema u WHERE u.perfil = 'MONITOR'";
            Query<UsuarioSistema> query = session.createQuery(hql, UsuarioSistema.class);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lista;
    }
    public List<Monitoria> listarTodas() {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<Monitoria> lista = null;

        try {
            String hql = "FROM Monitoria";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lista;
    }
}
