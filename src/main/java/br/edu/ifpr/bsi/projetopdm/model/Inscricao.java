package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_inscricao")
public class Inscricao extends GenericModel {


    // Relação com o aluno (UsuarioSistema com perfil ALUNO)
    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private UsuarioSistema aluno;

    // Relação com a monitoria
    @ManyToOne
    @JoinColumn(name = "id_monitoria", nullable = false)
    private Monitoria monitoria;

    // Getters e Setters

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
}
