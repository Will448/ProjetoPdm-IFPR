package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private UsuarioSistema aluno;

    @ManyToOne
    @JoinColumn(name = "monitoria_id")
    private Monitoria monitoria;

    @Column
    private LocalDate data;

    @Column
    private Double nota;

    // Getters e Setters

    public Long getId() {
        return id;
    }

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


}
