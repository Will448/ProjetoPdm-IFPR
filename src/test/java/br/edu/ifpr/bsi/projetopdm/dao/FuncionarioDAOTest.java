package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Funcionario;
import org.junit.jupiter.api.Test;

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
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.buscarPorId(1L); // Buscar do banco

        if (funcionario != null) {
            dao.remover(funcionario);
        } else {
            System.out.println("Funcionário com ID 1 não encontrado.");
        }
    }

}
