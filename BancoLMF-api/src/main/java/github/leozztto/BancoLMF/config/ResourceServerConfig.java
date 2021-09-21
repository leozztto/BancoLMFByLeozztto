package github.leozztto.BancoLMF.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/clientes/newCliente").permitAll()
                .antMatchers("/api/clientes/**").authenticated()
                .antMatchers("/api/contaClientes/newContaCliente").permitAll()
                .antMatchers("/api/contaClientes/**").authenticated()
                .antMatchers("/api/movimentoOperacoes/**").authenticated()
                .antMatchers("/api/tipocontas/**").authenticated()
                .antMatchers("/api/tipoOperacoes/**").authenticated()
                .anyRequest().denyAll();
    }
}
