package br.edu.ifpr.bsi.projetopdm.helpers;

import jakarta.persistence.Table;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class HibernateHelperTest {

    @Test
    public void conectar(){
        //realiza a abertura das sessões para o banco
       Session sessao = HibernateHelper.getFabricaDeSessoes().openSession();
        //fecha a sessão do banco
       sessao.close();

    }



}
