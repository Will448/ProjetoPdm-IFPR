package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Frequencia;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrequenciaDAOTest {

    @Test
    public void testSalvarFrequencia() {
        UsuarioSistemaDAO usuarioDAO = new UsuarioSistemaDAO();
        MonitoriaDAO monitoriaDAO = new MonitoriaDAO();

        UsuarioSistema monitor = usuarioDAO.buscarPorLogin("joao.monitor"); // Login real
        UsuarioSistema aluno = usuarioDAO.buscarPorLogin("joao.aluno");     // Login real de um aluno
        Monitoria monitoria = monitoriaDAO.buscarPorId(1L);                 // ID real da monitoria

        Frequencia frequencia = new Frequencia();
        frequencia.setMonitor(monitor);            // agora é monitor
        frequencia.setAluno(aluno);                // novo: aluno vinculado
        frequencia.setMonitoria(monitoria);        // nova relação com monitoria
        frequencia.setTotalHorasMonitoria(20);
        frequencia.setDataGeracao(LocalDate.now());

        FrequenciaDAO dao = new FrequenciaDAO();
        dao.salvar(frequencia);

        assertNotNull(frequencia.getId());
        System.out.println("Frequência salva com ID: " + frequencia.getId());
    }

    @Test
    public void testBuscarPorId() {
        FrequenciaDAO dao = new FrequenciaDAO();
        Frequencia frequencia = dao.buscarPorId(1L); // Substitua pelo ID real

        assertNotNull(frequencia);
        System.out.println("Frequência encontrada:");
        System.out.println("ID: " + frequencia.getId());
        System.out.println("Data: " + frequencia.getDataGeracao());
        System.out.println("Monitor: " + frequencia.getMonitor().getNome());
        System.out.println("Aluno: " + frequencia.getAluno().getNome());
    }

    @Test
    public void testAlterarFrequencia() {
        FrequenciaDAO dao = new FrequenciaDAO();
        Frequencia frequencia = dao.buscarPorId(1L); // Substitua pelo ID real

        assertNotNull(frequencia);
        frequencia.setTotalHorasMonitoria(30); // alterando para testar
        dao.alterarSalvar(frequencia);

        Frequencia alterada = dao.buscarPorId(frequencia.getId());
        assertEquals(30, alterada.getTotalHorasMonitoria());

        System.out.println("Frequência atualizada:");
        System.out.println("Total Horas Monitoria: " + alterada.getTotalHorasMonitoria());
    }

    @Test
    public void testListarFrequencias() {
        FrequenciaDAO dao = new FrequenciaDAO();
        List<Frequencia> lista = dao.listar();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (Frequencia f : lista) {
            System.out.println("ID: " + f.getId() +
                    " | Monitor: " + f.getMonitor().getLogin() +
                    " | Aluno: " + f.getAluno().getLogin());
        }
    }

    @Test
    public void testRemoverFrequencia() {
        FrequenciaDAO dao = new FrequenciaDAO();
        Frequencia frequencia = dao.buscarPorId(2L); // Substitua pelo ID real

        assertNotNull(frequencia);
        dao.remover(frequencia);

        Frequencia removida = dao.buscarPorId(frequencia.getId());
        assertNull(removida);
        System.out.println("Frequência removida com sucesso.");
    }
}
