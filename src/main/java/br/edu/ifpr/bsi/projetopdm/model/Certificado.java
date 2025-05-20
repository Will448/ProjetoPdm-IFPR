package br.edu.ifpr.bsi.projetopdm.model;
import jakarta.persistence.*;

@Entity
@Table (name = "tb_certificado")

public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioSistema participante;

    @Column
    private String Titulo;
    @Column
    private String Descricao;

    @Column
    private String data;

    @Column
    private String Duracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioSistema getParticipante() {
        return participante;
    }

    public void setParticipante(UsuarioSistema participante) {
        this.participante = participante;
    }



    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String duracao) {
        Duracao = duracao;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
