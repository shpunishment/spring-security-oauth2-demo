package com.shpun.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Description: 资源服务器配置，
 * @Author: sun
 * @Date: 2020/4/28 11:52
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-1";

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 添加特定于资源服务器的属性
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());

        resources
                .resourceId(RESOURCE_ID)
                .tokenServices(defaultTokenServices)
        ;
    }

    /**
     * 使用此配置安全资源的访问规则，配置需要token验证的url。 默认情况下，所有不在"/oauth/**"中的资源都受到保护。
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // /api/* 都需要token 会被 OAuth2AuthenticationProcessingFilter 处理，获取token
        http
                .requestMatchers()
                .antMatchers("/api/*")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    /**
     * 配置redis，使用redis存token
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

}
