package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_inscricao")
public class Inscricao extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relação com o aluno (UsuarioSistema com perfil ALUNO)
    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private UsuarioSistema aluno;

    // Relação com a monitoria
    @ManyToOne
    @JoinColumn(name = "id_monitoria")
    private Monitoria monitoria;

    // ✅ Nova relação com o evento
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    // GETTERS e SETTERS

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioSistema getAluno() {
        return aluno;
    }

    public void setAluno(UsuarioSistema aluno) {
        this.aluno = aluno;
    }

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
