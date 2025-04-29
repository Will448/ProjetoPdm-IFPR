package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Funcionario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/* o operador dimante transforma os metodos dela conforme necessário*/
public class GenericDAO <Entidade>{
    private Class<Entidade> classe; /*guardo uma classe*/

    public  GenericDAO(){
        this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static void inserir(Funcionario entidade){

        Transaction transacao = null; //funciona como verificador e transporte das informações
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            transacao = session.beginTransaction();
            session.persist(entidade);//neste formato utilizo o persist para o inserir
            transacao.commit();

        }catch (RuntimeException e) {
            if (transacao != null){
                transacao.rollback();
            }
        System.out.println("Ocorreu um erro na execução da transação");
        } finally {
            session.close();
        }

    }
    public Entidade buscarPorId(Long id) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        try {
            return session.find(classe, id);
        } finally {
            session.close();
        }
    }

    public void remover(Entidade entidade){
        Transaction transacao = null; //funciona como verificador e transporte das informações
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try {
            transacao = session.beginTransaction();
            session.remove(entidade);//neste formato utilizo o remove para o deletar
            transacao.commit();

        }catch (RuntimeException e) {
            if (transacao != null){
                transacao.rollback();
            }
            e.printStackTrace();
            System.out.println("Ocorreu um erro na execução da transação");
        } finally {
            session.close();
        }

    }

    public List<Entidade> listar(){

        List<Entidade> resultado = null;

        Session session = HibernateHelper.getFabricaDeSessoes().openSession();

        try{
            CriteriaBuilder bilder = session.getCriteriaBuilder();
            CriteriaQuery<Entidade> criteria = bilder.createQuery(classe);

            Root<Entidade> root = criteria.from(classe);
            criteria.select(root);
            resultado = session.createQuery(criteria).getResultList();

        }catch(RuntimeException erro){
            //Não precisa fazer rollback
            //porque não existe transação em uma consulta
            System.out.println("Ocorreu um erro ao listar os dados.");
        }finally{
            session.close();
        }

        return resultado;
    }

}