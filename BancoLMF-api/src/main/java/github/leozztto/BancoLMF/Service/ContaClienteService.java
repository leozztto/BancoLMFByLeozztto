package github.leozztto.BancoLMF.Service;

import github.leozztto.BancoLMF.model.ContaCliente;
import github.leozztto.BancoLMF.repository.ContaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContaClienteService implements UserDetailsService {

    @Autowired
    private ContaClienteRepository contaClienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ContaCliente contaCliente = contaClienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
        return User.builder()
                .username(contaCliente.getUsername())
                .password(contaCliente.getPassword())
                .roles("USER")
                .build();
    }
}
