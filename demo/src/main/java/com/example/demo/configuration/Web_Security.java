package com.example.demo.configuration;

import com.example.demo.Entity.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Web_Security {


    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private Users users;

    private CustomerAuthenticationProvider customerAuthenticationProvider;

    public Web_Security(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,CustomerAuthenticationProvider customerAuthenticationProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.customerAuthenticationProvider=customerAuthenticationProvider;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/login")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        http.sessionManagement((sessionManagement) ->
                                sessionManagement
                                        .sessionConcurrency((sessionConcurrency) ->
                                                sessionConcurrency
                                                        .maximumSessions(1)
                                                        .expiredUrl("/login?expired")
                                        ));

        return http.build();
    }



}

