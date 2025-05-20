package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Evento;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventoDAOTest {

    @Test
    public void testSalvarEvento() {
        Evento evento = new Evento();
        evento.setTitulo("Feira de Ciências");
        evento.setDescricao("Mostra de projetos científicos dos alunos.");
        evento.setData("2025-09-15");
        evento.setLocal("IFPR - Auditório");

        Evento evento2 = new Evento();
        evento2.setTitulo("Semana da Tecnologia");
        evento2.setDescricao("Palestras e workshops sobre inovação e tecnologia.");
        evento2.setData("2025-10-20");
        evento2.setLocal("IFPR - Bloco D, Sala D13");

        EventoDAO dao = new EventoDAO();
        dao.salvar(evento2);
        dao.salvar(evento);

        assertNotNull(evento2.getId());
        System.out.println("Evento salvo com ID: " + evento2.getId());
    }

    @Test
    public void testBuscarPorTitulo() {
        EventoDAO dao = new EventoDAO();
        Evento evento = dao.buscarPorTitulo("Feira de Ciências");

        assertNotNull(evento);
        assertEquals("Feira de Ciências", evento.getTitulo());
        System.out.println("Evento encontrado: " + evento.getTitulo());
    }

    @Test
    public void testAlterarEvento() {
        /*
        EventoDAO dao = new EventoDAO();
        Evento evento = dao.buscarPorTitulo("Feira de Ciências");

        assertNotNull(evento);
        evento.setTitulo("Mostra de cursos do IFPR");
        dao.alterarSalvar(evento);

        Evento atualizado = dao.buscarPorId(evento.getId());

        System.out.println("Evento atualizado: " + atualizado.getDescricao());
        */
            EventoDAO dao = new EventoDAO();
            Evento evento = dao.buscarPorId(3L); // Substitua 1L pelo ID desejado

            assertNotNull(evento);
            evento.setTitulo("SEPIN");

            dao.alterarSalvar(evento);

            Evento atualizado = dao.buscarPorId(evento.getId());
            System.out.println("Evento atualizado: " + atualizado.getTitulo() + " - " + atualizado.getDescricao());
        }



    @Test
    public void testListarEventos() {
        EventoDAO dao = new EventoDAO();
        List<Evento> eventos = dao.listar();

        assertFalse(eventos.isEmpty());
        for (Evento e : eventos) {
            System.out.println("Evento: " + e.getId() + " - " + e.getTitulo());
        }
    }

    @Test
    public void removerEventoPorId() {
        EventoDAO dao = new EventoDAO();
        Evento evento = dao.buscarPorId(1L); // Substitua 1L pelo ID do evento que deseja remover
        if (evento != null) {
            dao.remover(evento);
            System.out.println("Evento removido com sucesso");
        } else {
            System.out.println("Evento não encontrado");
        }
    }
}