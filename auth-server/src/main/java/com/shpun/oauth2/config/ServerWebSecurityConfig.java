package com.shpun.oauth2.config;

import com.shpun.oauth2.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: security配置，用户数据，自定义登录页，成功失败Handler，session，配置非受保护URL等
 * @Author: sun
 * @Date: 2020/4/28 11:57
 */
@Configuration
public class ServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    /**
     * 认证管理器配置，用于信息获取来源(UserDetails)以及密码校验规则(PasswordEncoder)
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 核心过滤器配置，更多使用ignoring()用来忽略对静态资源的控制
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/js/**");
    }

    /**
     * 安全过滤器链配置，自定义安全访问策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // /login 和 /oauth/authorize 路径配置为不需要任何身份验证，其他所有路径必须经过验证
                .antMatchers("/login", "/oauth/authorize").permitAll()
                // 其他请求都需要已认证
                .anyRequest().authenticated()
                .and()
                // 使用表单登录
                .formLogin()
                // 自定义username 和password参数
                .usernameParameter("login_username")
                .passwordParameter("login_password")
                // 自定义登录页地址
                .loginPage("/loginPage")
                // 验证表单的地址，由过滤器 UsernamePasswordAuthenticationFilter 拦截处理
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                // 默认为 /logout ，登出后默认跳转到 /login?logout ,上面修改了登录页地址后回跳到 /loginPage?logout
                .logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                // 无效会话
                .invalidateHttpSession(true)
                // 清除身份验证
                .clearAuthentication(true)
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
