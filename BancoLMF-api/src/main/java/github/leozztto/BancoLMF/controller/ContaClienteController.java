package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.ContaCliente;
import github.leozztto.BancoLMF.repository.ContaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contaClientes")
public class ContaClienteController {

    private ContaClienteRepository contaClienteRepository;

    @Autowired
    public ContaClienteController(ContaClienteRepository contaClienteRepository) {
        this.contaClienteRepository = contaClienteRepository;
    }

    @PostMapping("/newContaCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaCliente saveContaCliente(@RequestBody @Valid ContaCliente contaCliente) {
        return contaClienteRepository.save(contaCliente);
    }

    @GetMapping("/getContaCliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaCliente findContaClienteById(@PathVariable Integer id) {
        return contaClienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta do Cliente não encontrado"));
    }

    @DeleteMapping("/delContaCliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContaClienteById(@PathVariable Integer id) {
        contaClienteRepository
                .findById(id).map(contaCliente -> {
            contaClienteRepository.delete(contaCliente);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta do Cliente não encontrado"));
    }

    @PutMapping("/updContaCliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContaClienteById(@PathVariable Integer id, @RequestBody @Valid ContaCliente contaClienteUpdate) {
        contaClienteRepository
                .findById(id)
                .map(contaCliente -> {
                    contaCliente.setDescricao(contaClienteUpdate.getDescricao());
                    contaCliente.setCliente(contaClienteUpdate.getCliente());
                    contaCliente.setTipoConta(contaClienteUpdate.getTipoConta());
                    contaCliente.setDataAlteracao(contaClienteUpdate.getDataAlteracao());

                    return contaClienteRepository.save(contaCliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllContaClientes")
    @ResponseStatus(HttpStatus.OK)
    public List<ContaCliente> getAllContaClientes(){
        return contaClienteRepository.findAll();
    }
}
