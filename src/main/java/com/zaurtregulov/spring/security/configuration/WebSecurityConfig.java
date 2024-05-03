package com.zaurtregulov.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("EMPLOYEE").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("zaur").password("zaur").roles("EMPLOYEE").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("andrey").password("andrey").roles("HR").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("zahar").password("zahar").roles("MANAGER", "HR").build());
        return manager;
    }
}
