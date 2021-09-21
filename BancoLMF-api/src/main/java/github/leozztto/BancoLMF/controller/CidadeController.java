package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.Cidade;
import github.leozztto.BancoLMF.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    @Autowired
    public CidadeController(CidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }

    @PostMapping("/newCidade")
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade saveCidade(@RequestBody @Valid Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @GetMapping("/getCidade/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade findCidadeById(@PathVariable Integer id) {
        return cidadeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada"));
    }

    @DeleteMapping("/delCidade/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCidadeById(@PathVariable Integer id) {
        cidadeRepository
                .findById(id).map(cidade -> {
            cidadeRepository.delete(cidade);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada"));
    }

    @PutMapping("/updCidade/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCidadeById(@PathVariable Integer id, @RequestBody @Valid Cidade cidadeUpdate) {
        cidadeRepository
                .findById(id)
                .map(cidade -> {
                    cidade.setNome(cidadeUpdate.getNome());
                    cidade.setEstado(cidadeUpdate.getEstado());
                    return cidadeRepository.save(cidade);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllCidades")
    @ResponseStatus(HttpStatus.OK)
    public List<Cidade> getAllCidades(){
        return cidadeRepository.findAll();
    }
}
