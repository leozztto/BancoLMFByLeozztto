package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.TipoConta;
import github.leozztto.BancoLMF.repository.TipoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipocontas")
public class TipoContaController {

    private TipoContaRepository tipoContaRepository;

    @Autowired
    public TipoContaController(TipoContaRepository tipoContaRepository){
        this.tipoContaRepository = tipoContaRepository;
    }

    @PostMapping("/newTipoConta")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoConta saveTipoConta(@RequestBody @Valid TipoConta tipoConta) {
        return tipoContaRepository.save(tipoConta);
    }

    @GetMapping("/getTipoConta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoConta findTipoContaById(@PathVariable Integer id) {
        return tipoContaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Conta não encontrado"));
    }

    @DeleteMapping("/delTipoConta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTipoContaById(@PathVariable Integer id) {
        tipoContaRepository
                .findById(id).map(tipoConta -> {
            tipoContaRepository.delete(tipoConta);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Conta não encontrado"));
    }

    @PutMapping("/updETipoConta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTipoContaById(@PathVariable Integer id, @RequestBody @Valid TipoConta tipoContaUpdate) {
        tipoContaRepository
                .findById(id)
                .map(tipoConta -> {
                    tipoConta.setDescricao(tipoContaUpdate.getDescricao());
                    tipoConta.setCodigoOperacao(tipoConta.getCodigoOperacao());
                    return tipoContaRepository.save(tipoConta);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllTipoContas")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoConta> getAllTipoContas(){
        return tipoContaRepository.findAll();
    }
}
