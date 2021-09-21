package github.leozztto.BancoLMF.controller;

import github.leozztto.BancoLMF.model.Cliente;
import github.leozztto.BancoLMF.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/newCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/getCliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findClienteById(@PathVariable Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("/delCliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClienteById(@PathVariable Integer id) {
        clienteRepository
                .findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/updCliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClienteById(@PathVariable Integer id, @RequestBody @Valid Cliente clienteUpdate) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteUpdate.getNome());
                    cliente.setCpf(clienteUpdate.getCpf());
                    cliente.setCnpj(clienteUpdate.getCnpj());
                    cliente.setTipoPessoa(clienteUpdate.getTipoPessoa());
                    cliente.setDataNasc(clienteUpdate.getDataNasc());
                    cliente.setDataCriacao(clienteUpdate.getDataCriacao());
                    cliente.setGenero(clienteUpdate.getGenero());
                    cliente.setCepEnd(clienteUpdate.getCepEnd());
                    cliente.setRuaEnd(clienteUpdate.getRuaEnd());
                    cliente.setNumeroEnd(clienteUpdate.getNumeroEnd());
                    cliente.setBairroEnd(clienteUpdate.getBairroEnd());
                    cliente.setComplEnd(clienteUpdate.getComplEnd());
                    cliente.setCidadeEnd(clienteUpdate.getCidadeEnd());
                    cliente.setTelefone(clienteUpdate.getTelefone());
                    cliente.setCelular(clienteUpdate.getCelular());
                    cliente.setEmail(clienteUpdate.getEmail());
                    cliente.setDataAlteracao(clienteUpdate.getDataAlteracao());

                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllClientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }
}
