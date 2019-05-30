package com.example.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/1010:21
 * @Description: 客户端授权服务相关配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    public OAuth2Config() {
        super();
    }

    @Autowired
    @Qualifier(value = "authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return void
     * @author rhf30
     * @descript 配置客户端信息
     * @params [clients]
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_1")
                //此处密码在验证的时候DaoAuthenticationProvider的additionalAuthenticationChecks方法验证时会处理
                .secret("123456")
                .scopes("server")
                .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
                .accessTokenValiditySeconds(60 * 30)
                .refreshTokenValiditySeconds(60 * 60)
                .and()
                .withClient("client_2")
//                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("app")
                .secret(bCryptPasswordEncoder().encode("123456"))
                .accessTokenValiditySeconds(60 * 30)
                .refreshTokenValiditySeconds(60 * 60);
    }

    /**
     * @return void
     * @author rhf30
     * @descript 配置授权token的节点和token服务
     * @params [endpoints]
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).accessTokenConverter(jwtTokenEnhancer()).authenticationManager(authenticationManager);
        //指定认证管理器 endpoints.authenticationManager(authenticationManager); //指定token存储位置 endpoints.tokenStore(tokenStore()); // token生成方式 endpoints.accessTokenConverter(accessTokenConverter()); endpoints.userDetailsService(userDetailsService);

    }

    @Bean
    public TokenStore tokenStore() {
        TokenStore tokenStore = new JwtTokenStore(jwtTokenEnhancer()) ;
        return tokenStore;


    }


    @Bean("jwtTokenEnhancer")
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
        return jwtAccessTokenConverter;

    }
}
