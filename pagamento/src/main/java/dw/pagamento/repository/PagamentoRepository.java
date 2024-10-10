package dw.pagamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import dw.pagamento.model.Jogador;
import dw.pagamento.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByAno(int ano);
    List<Pagamento> findByJogador(Jogador jogador);
}
