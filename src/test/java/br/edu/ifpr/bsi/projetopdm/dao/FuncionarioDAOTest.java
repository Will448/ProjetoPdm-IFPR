package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Funcionario;
import br.edu.ifpr.bsi.projetopdm.model.Pagamento;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

public class FuncionarioDAOTest {

    @Test
    public void inserir(){
        Funcionario funcionario = new Funcionario();

        funcionario.setNome("Ernesto");
        funcionario.setSexo_funcionario("M");
        funcionario.setCpf_func("124789633371");
        funcionario.setDta_nascimento("14/09/1979");
        funcionario.setNumero_telefone("48988741236");

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.inserir(funcionario);
    }

    @Test
    public void remover() {
        Funcionario funcionario = new Funcionario();
        funcionario.setID(102L);

        FuncionarioDAO dao = new FuncionarioDAO();
        dao.remover(funcionario);
    }

    @Test
    public void listar() {
        FuncionarioDAO dao = new FuncionarioDAO();
        for (Funcionario funcionario : dao.listar()){

            System.out.println("\n ID: " + funcionario.getID());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Numero de telefone: " + funcionario.getNumero_telefone());

        }
    }

    @Test

    public void alterarSalvar(){

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Valiati");
        funcionario.setSexo_funcionario("Masculino");
        funcionario.setCpf_func("34567890112");
        funcionario.setDta_nascimento("14/05/1396");
        funcionario.setNumero_telefone("45898636987");

        FuncionarioDAO dao = new FuncionarioDAO();
      //  dao.alterarSalvar(funcionario);


        Funcionario funcionario2 = new Funcionario();

        funcionario2.setID(253L);
        funcionario2.setNome("Lucas");
        funcionario2.setSexo_funcionario("Masculino");
        funcionario2.setCpf_func("45698710000");
        funcionario2.setDta_nascimento("17/04/1925");
        funcionario2.setNumero_telefone("78945612301");


        dao.alterarSalvar(funcionario2);
    }

    @Test
    public void buscarPorId() {
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.buscarPorId(252L);
        System.out.println("Id: " + funcionario.getNome());
    }

    @Test
    public void removerPorId(){
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.buscarPorId(153L);
        dao.remover(funcionario);
        System.out.println("Removido com sucesso");
    }

    @Test
    public void inserirPagamento(){
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento("07-05-2025");
        pagamento.setValor(2.500);
        pagamento.setTipoPagamento("Depósito");
        pagamento.setNomeCargo("Aux. Adm.");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Eduardo");
        funcionario.setCpf_func("123.456.789-10");
        funcionario.setSexo_funcionario("M");
        funcionario.setNumero_telefone("46");
        funcionario.setDta_nascimento("30/09/1998");
        funcionario.setPagamento(pagamento);
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.inserir(funcionario);
    }
}
