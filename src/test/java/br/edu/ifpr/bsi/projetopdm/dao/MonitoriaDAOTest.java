package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonitoriaDAOTest {

    @Test
    public void testSalvarMonitoria() {
        Monitoria monitoria = new Monitoria();
        monitoria.setTitulo("Monitoria de POO");
        monitoria.setDescricao("Programação Orientada a Objetos em Java");
        monitoria.setDataHora("2025-03-25");
        monitoria.setLocal("IFPR");

        Monitoria monitoria2 = new Monitoria();
        monitoria.setTitulo("Monitoria de WEB");
        monitoria.setDescricao("HTML,CSS E JS");
        monitoria.setDataHora("2025-01-23");
        monitoria.setLocal("IFPR");

        MonitoriaDAO dao = new MonitoriaDAO();
       // dao.salvar(monitoria2);
        dao.salvar(monitoria);

        assertNotNull(monitoria.getId());
        System.out.println("Monitoria salva com ID: " + monitoria.getId());
    }

    @Test
    public void testBuscarPorTitulo() {
        MonitoriaDAO dao = new MonitoriaDAO();

        Monitoria monitoria = dao.buscarPorTitulo("Monitoria de POO");
        assertNotNull(monitoria);
        assertEquals("Monitoria de POO", monitoria.getTitulo());

        System.out.println("ID: " + monitoria.getId());
        System.out.println("Título: " + monitoria.getTitulo());
    }

    @Test
    public void testAlterarMonitoria() {
        MonitoriaDAO dao = new MonitoriaDAO();

        // Busca monitoria pelo título para garantir que exista
        Monitoria monitoria = dao.buscarPorTitulo("Monitoria de POO");
        assertNotNull(monitoria, "Monitoria para alteração não encontrada");

        monitoria.setDescricao("Design Patterns");
        dao.alterarSalvar(monitoria);

        Monitoria atualizado = dao.buscarPorId(monitoria.getId());

        System.out.println("\n--- MONITORIA ATUALIZADA ---");
        System.out.println("ID: " + atualizado.getId());
        System.out.println("Título: " + atualizado.getTitulo());
        System.out.println("Descricao: " + atualizado.getDescricao());

    }

    @Test
    public void testListarMonitorias() {
        MonitoriaDAO dao = new MonitoriaDAO();
        List<Monitoria> lista = dao.listar();

        assertFalse(lista.isEmpty());
        for (Monitoria m : lista) {
            System.out.println("ID: " + m.getId() + " - Título: " + m.getTitulo());
        }
    }

    @Test
    public void testRemoverPorId() {
        MonitoriaDAO dao = new MonitoriaDAO();

        Long idParaRemover = 3L; // substitua pelo ID real que deseja remover

        Monitoria monitoria = dao.buscarPorId(idParaRemover);
        dao.remover(monitoria);
        Monitoria removida = dao.buscarPorId(idParaRemover);
        assertNull(removida);

        System.out.println("Monitoria removida com sucesso (ID: " + idParaRemover + ")");
    }

}
