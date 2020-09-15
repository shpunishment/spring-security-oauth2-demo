package com.shpun.oauth2.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: security配置，如果需要对service-1的url进行控制，需要添加 WebSecurityConfigurerAdapter 配置，可配置子系统中哪些接口需要auth-server的认证，配置非受保护URL等。
 * @Author: sun
 * @Date: 2020/4/27 11:59
 */
@Configuration
// @EnableOAuth2Sso 注解 在继承 WebSecurityConfigurerAdapter 类的上面时，代表着在该子类配置的基础上增强 OAuth2Sso 相关配置。
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ClientWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 安全过滤器链配置，自定义安全访问策略。可配置客户端不受保护的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                // 访问 / /home 不用授权
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                // 权限不足跳转 /401
                .exceptionHandling().accessDeniedPage("/401");
    }

    /**
     * 核心过滤器配置，更多使用ignoring()用来忽略对静态资源的控制和过滤微服务间feign的接口
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/js/**");
    }
}
