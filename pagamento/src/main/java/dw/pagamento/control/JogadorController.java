package dw.pagamento.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dw.pagamento.model.Jogador;
import dw.pagamento.repository.JogadorRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping("/")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome) {
        try{
            List<Jogador> lj = new ArrayList<Jogador>();

            if(nome == null){
                jogadorRepository.findAll().forEach(lj::add);
            }
            else{
                jogadorRepository.findByNomeContaining(nome);
            }

            if(lj.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lj, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) {

        try{
            Jogador novojog = jogadorRepository.save(jogador);
            return new ResponseEntity<>(novojog, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable long id) {

        Optional<Jogador> data = jogadorRepository.findById(id);

        if(data.isPresent()){
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable long id, @RequestBody Jogador jogador) {
        
        Optional<Jogador> data = jogadorRepository.findById(id);

        if(data.isPresent()){
            Jogador jogupdate = data.get();
            jogupdate.setNome(jogador.getNome());
            jogupdate.setEmail(jogador.getEmail());
            jogupdate.setDatanasc(jogador.getDatanasc());

            return new ResponseEntity<>(jogadorRepository.save(jogupdate), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable long id) {
        try {
            jogadorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllJogadores() {
        try {
            jogadorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
