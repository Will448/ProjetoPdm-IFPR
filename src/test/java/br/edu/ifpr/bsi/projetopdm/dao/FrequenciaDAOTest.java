package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Frequencia;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrequenciaDAOTest {


    @Test
    public void testSalvarFrequencia() {
        UsuarioSistemaDAO usuarioDAO = new UsuarioSistemaDAO();
        UsuarioSistema usuario = usuarioDAO.buscarPorLogin("joao.monitor"); // Altere conforme login existente

        Frequencia frequencia = new Frequencia();
        frequencia.setUsuario(usuario);
        frequencia.setTotalHorasMonitoria(20);
        frequencia.setTotalHorasEventos(5);
        frequencia.setDataGeracao(LocalDate.now());

        FrequenciaDAO dao = new FrequenciaDAO();
        dao.salvar(frequencia);

        assertNotNull(frequencia.getId());
        System.out.println("Frequência salva com ID: " + frequencia.getId());
    }

    @Test
    public void testBuscarPorId() {
        FrequenciaDAO dao = new FrequenciaDAO();
        Frequencia frequencia = dao.buscarPorId(1L); // Substitua pelo ID real existente

        assertNotNull(frequencia);
        System.out.println("Frequência encontrada:");
        System.out.println("ID: " + frequencia.getId());
        System.out.println("Data: " + frequencia.getDataGeracao());
        System.out.println("Usuário: " + frequencia.getUsuario().getNome());
    }

    @Test
    public void testAlterarFrequencia() {
        FrequenciaDAO dao = new FrequenciaDAO();
        Frequencia frequencia = dao.buscarPorId(1L); // Substitua pelo ID real

        assertNotNull(frequencia);
        frequencia.setTotalHorasEventos(10);
        dao.alterarSalvar(frequencia);

        Frequencia alterada = dao.buscarPorId(frequencia.getId());
        assertEquals(10, alterada.getTotalHorasEventos());

        System.out.println("Frequência atualizada:");
        System.out.println("Total Eventos: " + alterada.getTotalHorasEventos());
    }

    @Test
    public void testListarFrequencias() {
        FrequenciaDAO dao = new FrequenciaDAO();
        List<Frequencia> lista = dao.listar();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (Frequencia f : lista) {
            System.out.println("ID: " + f.getId() + " | Usuário: " + f.getUsuario().getLogin());
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