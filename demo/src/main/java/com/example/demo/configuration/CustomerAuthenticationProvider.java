package com.example.demo.configuration;


import com.example.demo.Entity.Authorities;
import com.example.demo.Entity.Users;
import com.example.demo.dao.UsersDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;


@Configuration
public class CustomerAuthenticationProvider implements AuthenticationProvider {


    Users users;
    UsersDao usersDao;
    PasswordEncoder passwordEncoder;

    public CustomerAuthenticationProvider(Users users, UsersDao userDao, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.usersDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();
        String password = authentication.getCredentials().toString();
        users= usersDao.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not existing"));
        if (passwordEncoder.matches(password, users.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password,getAuthorities(users.getAuthorities()));
        }
        else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private Set<SimpleGrantedAuthority>getAuthorities(Set<Authorities>authorities) {
       Set<SimpleGrantedAuthority> list = new HashSet<>();
       for (Authorities auth : authorities) {
           list.add(new SimpleGrantedAuthority(auth.getAuthority()));
       }
      return null;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
