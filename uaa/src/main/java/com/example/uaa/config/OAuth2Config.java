package com.example.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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

    @Autowired
    @Qualifier("userService") //此处必须加上注入  否则会出现UserDetailsService is required错误
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
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
                .authorizedGrantTypes("client_credentials","implicit", "password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60 * 2)
                .refreshTokenValiditySeconds(60 * 60 * 30)
                .and()
                .withClient("client_2")
//                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code","client_credentials", "refresh_token")
                .scopes("app")
                .secret("123456")
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
        //pathMapping 配置自定义授权路径
        endpoints.tokenStore(tokenStore()).accessTokenConverter(jwtTokenEnhancer()).userDetailsService(this.userDetailsService).authenticationManager(authenticationManager).pathMapping("/oauth/confirm_access", "/custom/confirm_access");
        //指定认证管理器 endpoints.authenticationManager(authenticationManager); //指定token存储位置 endpoints.tokenStore(tokenStore()); // token生成方式 endpoints.accessTokenConverter(accessTokenConverter()); endpoints.userDetailsService(userDetailsService);

    }

    /**
     * @author rhf30
     * @descript 开启token验证 允许访问/oauth/check_token
     * @params [security]
     * @return void
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //这个如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter来保护
        //如果没有支持allowFormAuthenticationForClients或者有支持但是url中没有client_id和client_secret的，走basic认证保护
        security.tokenKeyAccess("permitAll()") .checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
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
