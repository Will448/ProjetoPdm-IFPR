package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Inscricao;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InscricaoDAOTest {

    @Test
    public void testSalvarInscricao() {
        UsuarioSistemaDAO usuarioDAO = new UsuarioSistemaDAO();
        MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
        InscricaoDAO inscricaoDAO = new InscricaoDAO();

        // Use IDs que já existem no seu banco
        UsuarioSistema aluno = usuarioDAO.buscarPorId(1L);
        assertNotNull(aluno);

        Monitoria monitoria = monitoriaDAO.buscarPorId(1L);
        assertNotNull(monitoria);

        Inscricao inscricao = new Inscricao();
        inscricao.setAluno(aluno);
        inscricao.setMonitoria(monitoria);

        inscricaoDAO.salvar(inscricao);

        assertNotNull(inscricao.getId());
        System.out.println("Inscrição salva com ID: " + inscricao.getId());
    }

    @Test
    public void testBuscarPorId() {
        InscricaoDAO dao = new InscricaoDAO();

        Long idBusca = 1L; // ID de inscrição que existe no banco
        Inscricao inscricao = dao.buscarPorId(idBusca);
        assertNotNull(inscricao);

        System.out.println("ID: " + inscricao.getId());
        System.out.println("Aluno: " + inscricao.getAluno().getNome());
        System.out.println("Monitoria: " + inscricao.getMonitoria().getTitulo());
    }

    @Test
    public void testAlterarInscricao() {
        InscricaoDAO dao = new InscricaoDAO();

        Long idAlterar = 1L; // ID existente no banco
        Inscricao inscricao = dao.buscarPorId(idAlterar);
        assertNotNull(inscricao);

        MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
        Monitoria novaMonitoria = monitoriaDAO.buscarPorId(2L);
        assertNotNull(novaMonitoria);

        inscricao.setMonitoria(novaMonitoria);
        dao.alterarSalvar(inscricao);

        Inscricao atualizado = dao.buscarPorId(idAlterar);
        assertEquals(novaMonitoria.getId(), atualizado.getMonitoria().getId());

        System.out.println("Inscrição alterada para nova monitoria (ID: " + atualizado.getId() + ")");
    }

    @Test
    public void testListarInscricoes() {
        InscricaoDAO dao = new InscricaoDAO();
        List<Inscricao> lista = dao.listar();

        assertFalse(lista.isEmpty());
        for (Inscricao i : lista) {
            System.out.println("ID: " + i.getId() + " - Aluno: " + i.getAluno().getNome() + " - Monitoria: " + i.getMonitoria().getTitulo());
        }
    }

    @Test
    public void testRemoverPorId() {
        InscricaoDAO dao = new InscricaoDAO();

        Long idParaRemover = 3L; // ID existente no banco que pode ser removido
        Inscricao inscricao = dao.buscarPorId(idParaRemover);
        assertNotNull(inscricao);

        dao.remover(inscricao);

        Inscricao removida = dao.buscarPorId(idParaRemover);
        assertNull(removida);

        System.out.println("Inscrição removida com sucesso (ID: " + idParaRemover + ")");
    }

    @Test
    public void testBuscarUsuariosPorMonitoria() {
        InscricaoDAO dao = new InscricaoDAO();

        Long monitoriaId = 53L; // Monitoria existente no banco
        List<UsuarioSistema> alunos = dao.buscarUsuariosPorMonitoria(monitoriaId);

        assertFalse(alunos.isEmpty());

        for (UsuarioSistema aluno : alunos) {
            System.out.println("Aluno inscrito na monitoria " + monitoriaId + ": " + aluno.getNome());
        }
    }
}
