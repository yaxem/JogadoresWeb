package dw.pagamento.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codPagamento;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private int mes;

    @Column(nullable = false)
    private float valor;

    @ManyToOne
    @JoinColumn(name = "cod_jogador", nullable = false)
    @JsonBackReference
    private Jogador jogador;

    // Getters e setters
    public long getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(long codPagamento) {
        this.codPagamento = codPagamento;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    // Construtores
    public Pagamento(int ano, int mes, float valor, Jogador jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }

    public Pagamento() {}
}
