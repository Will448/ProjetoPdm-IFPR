package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Avaliacao;
import br.edu.ifpr.bsi.projetopdm.model.Evento;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.junit.jupiter.api.Test;

import java.time.LocalDate; // ✅ Importação adicionada
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvalDAOTest {

    @Test
    public void testSalvarAvaliacao() {
        UsuarioSistemaDAO usuarioDAO = new UsuarioSistemaDAO();
        MonitoriaDAO monitoriaDAO = new MonitoriaDAO();

        UsuarioSistema aluno = usuarioDAO.buscarPorLogin("ana.alterada"); // Ajuste conforme necessário
        Monitoria monitoria = monitoriaDAO.buscarPorId(53L); // Ajuste conforme necessário

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAluno(aluno);
        avaliacao.setMonitoria(monitoria);
        avaliacao.setNota(9.5);
        avaliacao.setData(LocalDate.parse("2025-05-19")); // ✅ Conversão de String para LocalDate

        AvaliacaoDAO dao = new AvaliacaoDAO();
        dao.salvar(avaliacao);

        assertNotNull(avaliacao.getId());
        System.out.println("Avaliação salva com ID: " + avaliacao.getId());
    }

    @Test
    public void testBuscarPorId() {
        AvaliacaoDAO dao = new AvaliacaoDAO();
        Avaliacao avaliacao = dao.buscarPorId(3L); // Substitua por um ID existente

        assertNotNull(avaliacao);
        System.out.println("Avaliação encontrada:");
        System.out.println("ID: " + avaliacao.getId());
        System.out.println("Aluno: " + avaliacao.getAluno().getNome());
    }

    @Test
    public void testAlterarAvaliacao() {
        AvaliacaoDAO dao = new AvaliacaoDAO();
        Avaliacao avaliacao = dao.buscarPorId(1L); // Substitua por um ID existente

        assertNotNull(avaliacao);
        avaliacao.setNota(8.0);
        dao.alterarSalvar(avaliacao);

        Avaliacao alterada = dao.buscarPorId(avaliacao.getId());
        assertEquals(8.0f, alterada.getNota());

        System.out.println("Avaliação atualizada:");
        System.out.println("Nota: " + alterada.getNota());
    }

    @Test
    public void testListarAvaliacoes() {
        AvaliacaoDAO dao = new AvaliacaoDAO();
        List<Avaliacao> lista = dao.listar();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (Avaliacao a : lista) {
            System.out.println("ID: " + a.getId() +
                    " - Nota: " + a.getNota() +
                    " - Monitoria: " + (a.getMonitoria() != null ? a.getMonitoria().getTitulo() : "Sem monitoria"));
        }
    }

    @Test
    public void testRemoverAvaliacao() {
        AvaliacaoDAO dao = new AvaliacaoDAO();
        Avaliacao avaliacao = dao.buscarPorId(3L); // Substitua por um ID real

        assertNotNull(avaliacao);
        dao.remover(avaliacao);

        Avaliacao removida = dao.buscarPorId(avaliacao.getId());
        assertNull(removida);
        System.out.println("Avaliação removida com sucesso.");
    }
}
