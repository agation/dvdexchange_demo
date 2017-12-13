package com.agjava.dvdexchange;

import com.agjava.dvdexchange.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "fonts/**", "/signup", "/signin")
                .permitAll()
                .antMatchers("/user/list**").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/signin").usernameParameter("name")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
