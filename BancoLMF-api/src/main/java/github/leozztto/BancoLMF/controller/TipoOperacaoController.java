package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.TipoOperacao;
import github.leozztto.BancoLMF.repository.TipoOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipoOperacoes")
public class TipoOperacaoController {

    private TipoOperacaoRepository tipoOperacaoRepository;

    @Autowired
    public TipoOperacaoController(TipoOperacaoRepository tipoOperacaoRepository) {
        this.tipoOperacaoRepository = tipoOperacaoRepository;
    }

    @PostMapping("/newTipoOperacao")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoOperacao saveTipoOperacao(@RequestBody @Valid TipoOperacao tipoOperacao) {
        return tipoOperacaoRepository.save(tipoOperacao);
    }

    @GetMapping("/getTipoOperacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoOperacao findTipoOperacaoById(@PathVariable Integer id) {
        return tipoOperacaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo Operação não encontrado"));
    }

    @DeleteMapping("/delTipoOperacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoOperacaoById(@PathVariable Integer id) {
        tipoOperacaoRepository
                .findById(id).map(tipoOperacao -> {
            tipoOperacaoRepository.delete(tipoOperacao);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo Operação não encontrado"));
    }

    @PutMapping("/updTipoOperacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTipoOperacaoById(@PathVariable Integer id, @RequestBody @Valid TipoOperacao tipoOperacaoUpdate) {
        tipoOperacaoRepository
                .findById(id)
                .map(tipoOperacao -> {
                    tipoOperacao.setDescricao(tipoOperacao.getDescricao());

                    return tipoOperacaoRepository.save(tipoOperacao);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllTipoOperacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoOperacao> getAllTipoOperacoes(){
        return tipoOperacaoRepository.findAll();
    }
}
