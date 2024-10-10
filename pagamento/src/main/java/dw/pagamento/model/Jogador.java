package dw.pagamento.model;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codJogador;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 8)
    private String datanasc;

    @OneToMany(mappedBy = "jogador")
    @JsonManagedReference
    private List<Pagamento> pagamentos;

    // Getters e setters
    public long getCodJogador() {
        return codJogador;
    }

    public void setCodJogador(long codJogador) {
        this.codJogador = codJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    // Construtores
    public Jogador() {}

    public Jogador(String nome, String email, String datanasc, List<Pagamento> pagamentos) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
        this.pagamentos = pagamentos;
    }
}
