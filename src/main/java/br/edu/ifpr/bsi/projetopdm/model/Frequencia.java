package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_frequencia")
public class Frequencia extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private UsuarioSistema aluno;

    @ManyToOne
    @JoinColumn(name = "id_monitor", nullable = false)
    private UsuarioSistema monitor;

    @ManyToOne
    @JoinColumn(name = "id_monitoria", nullable = false)
    private Monitoria monitoria;

    @Column(name = "total_horas_monitoria")
    private Integer totalHorasMonitoria;

    @Column(name = "data_geracao")
    private LocalDate dataGeracao;

    // === Getters e Setters ===

    public UsuarioSistema getAluno() {
        return aluno;
    }

    public void setAluno(UsuarioSistema aluno) {
        this.aluno = aluno;
    }

    public UsuarioSistema getMonitor() {
        return monitor;
    }

    public void setMonitor(UsuarioSistema monitor) {
        this.monitor = monitor;
    }

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    public Integer getTotalHorasMonitoria() {
        return totalHorasMonitoria;
    }

    public void setTotalHorasMonitoria(Integer totalHorasMonitoria) {
        this.totalHorasMonitoria = totalHorasMonitoria;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
