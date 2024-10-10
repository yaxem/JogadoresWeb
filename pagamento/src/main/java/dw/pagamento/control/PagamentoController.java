package dw.pagamento.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dw.pagamento.model.Pagamento;
import dw.pagamento.model.Jogador;
import dw.pagamento.repository.PagamentoRepository;
import dw.pagamento.repository.JogadorRepository;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping("/")
    public ResponseEntity<List<Pagamento>> getAllPagamentos(@RequestParam(required = false) Integer ano) {
        List<Pagamento> pagamentos = (ano == null) 
            ? pagamentoRepository.findAll() 
            : pagamentoRepository.findByAno(ano);

        return pagamentos.isEmpty() 
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
            : new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        return jogadorRepository.findById(pagamento.getJogador().getCodJogador())
            .map(jogador -> {
                pagamento.setJogador(jogador);
                Pagamento savedPagamento = pagamentoRepository.save(pagamento);
                return new ResponseEntity<>(savedPagamento, HttpStatus.CREATED);
            })
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable long id) {
        return pagamentoRepository.findById(id)
            .map(pagamento -> new ResponseEntity<>(pagamento, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable long id, @RequestBody Pagamento pagamentoDetails) {
        return pagamentoRepository.findById(id)
            .map(pagamento -> {
                pagamento.setAno(pagamentoDetails.getAno());
                pagamento.setMes(pagamentoDetails.getMes());
                pagamento.setValor(pagamentoDetails.getValor());
                if (pagamentoDetails.getJogador() != null) {
                    Optional<Jogador> jogadorOpt = jogadorRepository.findById(pagamentoDetails.getJogador().getCodJogador());
                    jogadorOpt.ifPresent(pagamento::setJogador);
                }
                return new ResponseEntity<>(pagamentoRepository.save(pagamento), HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable long id) {
        try {
            pagamentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPagamentos() {
        try {
            pagamentoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Pagamento>> getPagamentosByAno(@PathVariable int ano) {
        List<Pagamento> pagamentos = pagamentoRepository.findByAno(ano);
        return pagamentos.isEmpty() 
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
            : new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    // @GetMapping("/jogador/{id}")
    // public ResponseEntity<List<Pagamento>> getPagamentosByJogador(@PathVariable long id) {
    //     return jogadorRepository.findById(id)
    //         .map(jogador -> {
    //             List<Pagamento> pagamentos = pagamentoRepository.findByJogador(jogador);
    //             return pagamentos.isEmpty() 
    //                 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
    //                 : new ResponseEntity<>(pagamentos, HttpStatus.OK);
    //         })
    //         .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }
}
