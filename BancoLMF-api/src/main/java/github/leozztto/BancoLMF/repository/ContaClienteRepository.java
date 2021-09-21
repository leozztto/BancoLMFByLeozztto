package github.leozztto.BancoLMF.repository;

import github.leozztto.BancoLMF.model.ContaCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaClienteRepository extends JpaRepository<ContaCliente, Integer> {

    Optional<ContaCliente> findByUsername(String username);
}
