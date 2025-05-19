package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioSistemaDAOTest {

    @Test
    public void testSalvarUsuarioSistema() {
        UsuarioSistema usuario1 = new UsuarioSistema();
        usuario1.setRa("202512345");
        usuario1.setNome("João Monitor");
        usuario1.setEmail("joao@ifpr.edu.br");
        usuario1.setTelefone("41999998888");
        usuario1.setCurso("Informática");
        usuario1.setPeriodoAcademico("4º período");
        usuario1.setLogin("joao.monitor");
        usuario1.setSenha("senha123");
        usuario1.setNivelAcesso("MONITOR");

        UsuarioSistema usuario2 = new UsuarioSistema();
        usuario2.setRa("202512346");
        usuario2.setNome("Debora Martins");
        usuario2.setEmail("Debora@ifpr.edu.br");
        usuario2.setTelefone("41999997777");
        usuario2.setCurso("Informática");
        usuario2.setPeriodoAcademico("3º período");
        usuario2.setLogin("debora.monitor");
        usuario2.setSenha("senha456");
        usuario2.setNivelAcesso("MONITOR");

        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
        dao.salvar(usuario1);
        dao.salvar(usuario2);

        assertNotNull(usuario1.getId());
        assertNotNull(usuario2.getId());

    }

    @Test
    public void testBuscarPorLoginESenha() {
        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
        UsuarioSistema usuario = dao.buscarPorLogin("joao.monitor");

        assertNotNull(usuario);
        assertEquals("joao.monitor", usuario.getLogin());

        System.out.println("\n--- USUÁRIO DO SISTEMA ---");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Telefone: " + usuario.getTelefone());
    }
    @Test
    public void listarUsuarios() {
        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
        for (UsuarioSistema usuario : dao.listar()) {
            System.out.println("\n--- USUÁRIO DO SISTEMA ---");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Login: " + usuario.getLogin());
            System.out.println("Telefone: " + usuario.getTelefone());
        }
    }

    @Test
    public void removerPorId(){
        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();
        UsuarioSistema usuariosistema = dao.buscarPorId(153L);
        dao.remover(usuariosistema);
        System.out.println("Removido com sucesso");
    }

    @Test
    public void testAlterarSalvarUsuarioSistema() {
        // Criar novo usuário
        UsuarioSistema usuario = new UsuarioSistema();
        usuario.setRa("202599999");
        usuario.setNome("Carlos Tester");
        usuario.setEmail("carlos@ifpr.edu.br");
        usuario.setTelefone("41988887777");
        usuario.setCurso("Sistemas de Informação");
        usuario.setPeriodoAcademico("5º período");
        usuario.setLogin("carlos.tester");
        usuario.setSenha("teste123");
        usuario.setNivelAcesso("ADMIN");

        UsuarioSistemaDAO dao = new UsuarioSistemaDAO();

        // Salvar usuário novo
        dao.salvar(usuario);

        // Alterar dados de um usuário já existente
        UsuarioSistema usuario2 = new UsuarioSistema();
        usuario2.setId(53L); // Certifique-se de que esse ID exista no banco de dados
        usuario2.setRa("202588888");
        usuario2.setNome("Ana Alterada");
        usuario2.setEmail("ana@ifpr.edu.br");
        usuario2.setTelefone("41987776666");
        usuario2.setCurso("Informática");
        usuario2.setPeriodoAcademico("6º período");
        usuario2.setLogin("ana.alterada");
        usuario2.setSenha("novaSenha");
        usuario2.setNivelAcesso("MONITOR");

        dao.alterarSalvar(usuario2);

        // Verificação básica
        assertNotNull(usuario.getId());
        System.out.println("Usuário novo salvo com ID: " + usuario.getId());
    }

}
