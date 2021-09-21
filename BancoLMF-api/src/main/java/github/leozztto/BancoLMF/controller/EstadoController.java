package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.Estado;
import github.leozztto.BancoLMF.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;

    @Autowired
    public EstadoController(EstadoRepository estadoRepository){
        this.estadoRepository = estadoRepository;
    }

    @PostMapping("/newEstado")
    @ResponseStatus(HttpStatus.CREATED)
    public Estado saveEstado(@RequestBody @Valid Estado estado) {
        return estadoRepository.save(estado);
    }

    @GetMapping("/getEstado/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estado findEstadoById(@PathVariable Integer id) {
        return estadoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado não encontrado"));
    }

    @DeleteMapping("/delEstado/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEstadoById(@PathVariable Integer id) {
        estadoRepository
                .findById(id).map(estado -> {
            estadoRepository.delete(estado);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado não encontrado"));
    }

    @PutMapping("/updEstado/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEstadoById(@PathVariable Integer id, @RequestBody @Valid Estado estadoUpdate) {
        estadoRepository
                .findById(id)
                .map(estado -> {
                    estado.setNome(estadoUpdate.getNome());
                    estado.setSigla(estadoUpdate.getSigla());
                    return estadoRepository.save(estado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllEstados")
    @ResponseStatus(HttpStatus.OK)
    public List<Estado> getAllEstados(){
        return estadoRepository.findAll();
    }
}
