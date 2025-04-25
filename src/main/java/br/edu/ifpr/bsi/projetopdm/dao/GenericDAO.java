package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Funcionario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;

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
            System.out.println("Ocorreu um erro na execução da transação");
        } finally {
            session.close();
        }

    }

}