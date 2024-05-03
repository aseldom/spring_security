package com.zaurtregulov.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("EMPLOYEE").build());
//        manager.createUser(User.withDefaultPasswordEncoder().username("zaur").password("zaur").roles("EMPLOYEE").build());
//        manager.createUser(User.withDefaultPasswordEncoder().username("andrey").password("andrey").roles("HR").build());
//        manager.createUser(User.withDefaultPasswordEncoder().username("zahar").password("zahar").roles("MANAGER", "HR").build());
//        return manager;
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("andrey")
                .password(passwordEncoder.encode("andrey"))
                .roles("HR")
                .build());
        manager.createUser(User.withUsername("zahar")
                .password(passwordEncoder.encode("zahar"))
                .roles("MANAGER", "HR")
                .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((user) -> user
                        .requestMatchers("/").hasAnyRole("HR", "EMPLOYEE", "MANAGER")
                        .requestMatchers("/hr_info/**").hasRole("HR")
                        .requestMatchers("/manager_info/**").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
