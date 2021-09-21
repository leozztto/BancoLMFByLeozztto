package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.MovimentoOperacao;
import github.leozztto.BancoLMF.repository.MovimentoOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movimentoOperacoes")
public class MovimentoOperacaoController {

    private MovimentoOperacaoRepository movimentoOperacaoRepository;

    @Autowired
    public MovimentoOperacaoController(MovimentoOperacaoRepository movimentoOperacaoRepository) {
        this.movimentoOperacaoRepository = movimentoOperacaoRepository;
    }

    @PostMapping("/newMovimentoOperacao")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentoOperacao saveMovimentoOperacao(@RequestBody @Valid MovimentoOperacao movimentoOperacao) {
        return movimentoOperacaoRepository.save(movimentoOperacao);
    }

    @GetMapping("/getMovimentoOperacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovimentoOperacao findMovimentoOperacaoById(@PathVariable Integer id) {
        return movimentoOperacaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento da Operação não encontrado"));
    }

    @DeleteMapping("/delMovimentoOperacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovimentoOperacaoById(@PathVariable Integer id) {
        movimentoOperacaoRepository
                .findById(id).map(movimentoOperacao -> {
            movimentoOperacaoRepository.delete(movimentoOperacao);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento da Operação não encontrado"));
    }

    @PutMapping("/updMovimentoOperacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovimentoOperacaoById(@PathVariable Integer id, @RequestBody @Valid MovimentoOperacao movimentoOperacaoUpdate) {
        movimentoOperacaoRepository
                .findById(id)
                .map(movimentoOperacao -> {
                    movimentoOperacao.setDescricao(movimentoOperacaoUpdate.getDescricao());
                    movimentoOperacao.setContaOrigem(movimentoOperacaoUpdate.getContaOrigem());
                    movimentoOperacao.setContaDestino(movimentoOperacaoUpdate.getContaDestino());
                    movimentoOperacao.setValorOperacao(movimentoOperacaoUpdate.getValorOperacao());
                    movimentoOperacao.setObservacao(movimentoOperacaoUpdate.getObservacao());

                    return movimentoOperacaoRepository.save(movimentoOperacao);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllMovimentoOperacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimentoOperacao> getAllMovimentoOperacoes(){
        return movimentoOperacaoRepository.findAll();
    }
}
