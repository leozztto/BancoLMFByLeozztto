package github.leozztto.BancoLMF.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENTE_ID = "my-angular-app";
    private static final String CLIENT_SECRET = "@321";
    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 30 * 60;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${security.jwt.signing-key}")
    private String signingKey;

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(signingKey);
        return tokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENTE_ID)
                .secret(CLIENT_SECRET)
                .scopes(SCOPE_READ, SCOPE_WRITE)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD).accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    }
}