package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_frequencia")

public class Frequencia {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private UsuarioSistema usuario;
            @Column
        private int totalHorasMonitoria;
            @Column
        private int totalHorasEventos;
         @Column
        private LocalDate dataGeracao;

        // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioSistema getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSistema usuario) {
        this.usuario = usuario;
    }

    public int getTotalHorasMonitoria() {
        return totalHorasMonitoria;
    }

    public void setTotalHorasMonitoria(int totalHorasMonitoria) {
        this.totalHorasMonitoria = totalHorasMonitoria;
    }

    public int getTotalHorasEventos() {
        return totalHorasEventos;
    }

    public void setTotalHorasEventos(int totalHorasEventos) {
        this.totalHorasEventos = totalHorasEventos;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
