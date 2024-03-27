package com.example.demo.configuration;


import com.example.demo.Entity.Users;


import com.example.demo.dao.UsersDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    UsersDao usersDao;

    public CustomUserDetailsService(UsersDao usersDao) {
        this.usersDao=usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersDao.findByUsername(username);
        if (username == null) {
            return null;
        }
        else {
            return new CustomUserDetails(users);
        }

    }

}
