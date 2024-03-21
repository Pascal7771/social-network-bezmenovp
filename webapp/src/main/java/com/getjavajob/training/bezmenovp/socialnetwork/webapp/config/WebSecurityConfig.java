package com.getjavajob.training.bezmenovp.socialnetwork.webapp.config;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Roles;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountDetailsService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.handler.FailHandler;
import com.getjavajob.training.bezmenovp.socialnetwork.service.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private AccountService accountService;
    private SuccessHandler successHandler;
    private FailHandler failHandler;

    @Autowired
    public WebSecurityConfig(AccountService accountService, SuccessHandler successHandler, FailHandler failHandler) {
        this.accountService = accountService;
        this.successHandler = successHandler;
        this.failHandler = failHandler;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/account/create").permitAll()
                .antMatchers(HttpMethod.GET, "/login", "/account/create", "/error-page").permitAll()
                .antMatchers("/check-database-connection").permitAll()
                .antMatchers(HttpMethod.POST, "/account/update-form-file", "/account/delete").hasAnyAuthority(Roles.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
                .successHandler(successHandler).failureHandler(failHandler)
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true).permitAll()
                .and()
                .rememberMe().authenticationSuccessHandler(successHandler).rememberMeParameter("remember")
                .rememberMeCookieName("remember").tokenValiditySeconds(Integer.MAX_VALUE)
                .and()
                .exceptionHandling()
                .and()
                .csrf().disable();
    }

    public UserDetailsService getUserDetailsService() {
        return new AccountDetailsService(accountService);
    }

}