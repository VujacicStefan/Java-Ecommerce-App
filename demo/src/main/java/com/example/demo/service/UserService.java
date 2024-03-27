package com.example.demo.service;


import com.example.demo.Entity.Role;
import com.example.demo.Entity.Users;
import com.example.demo.configuration.CustomUserDetailsService;
import com.example.demo.configuration.Web_Security;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UsersDao;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    UsersDao usersDao;
    RoleDao roleDao;
    CustomUserDetailsService detailsService;
    Web_Security web;

    Role roles;

    AuthenticationManager authenticationManager;

    public UserService(UsersDao usersDao, RoleDao roleDao, CustomUserDetailsService detailsService, Web_Security web,Role roles) {
        this.usersDao = usersDao;
        this.roleDao = roleDao;
        this.detailsService = detailsService;
        this.web = web;
        this.roles=roles;
    }

    public void deleteUser(long id) {
        usersDao.findById(id);
        if (usersDao.existsById(id)) {
            usersDao.deleteById(id);
        }
        else {
            System.out.println("User does not exist");
        }
    }


    public Users register_user(Users user) {
        if (detailsService != null) {
          detailsService.loadUserByUsername(user.getUsername());
        }
        return usersDao.save(user);
    }

    public UsersDto findUser(UsersDto user,long id) {
      usersDao.findById(id);
      if (usersDao.existsById(id)) {
          return user;
      }
      return null;
    }


    public Users registerNewUser(Users user) {
        try {
            Optional<Role> role = roleDao.findById(roles.getId());
            Set<Role> userRoles = new HashSet<>();
            role.ifPresent(userRoles::add);
            user.setRole(userRoles);
            String password = web.passwordEncoder().encode(user.getPassword());
            user.setPassword(password);
            return usersDao.save(user);
        } catch (Exception ex) {
            System.out.println("No such element");
        }

        return null;
    }




    public Users login_user (Users user)  {
        Authentication authentication;
        try  {
         authentication=authenticationManager.authenticate (
                 new UsernamePasswordAuthenticationToken(user.getPassword(),user.getUsername()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException(badCredentialsException.getLocalizedMessage());
        }
        return null;
    }


    public Object update_user(long id, Users users) {
        // get the user from DB by id
        // update with new value getting from request
        Users existingUser = usersDao.findById(id).get();
        existingUser.setFirsName(users.getFirsName());
        existingUser.setLastName(users.getLastName());
        existingUser.setEmail(users.getEmail());
        existingUser.setPhoneNumber(users.getPhoneNumber());
        if (usersDao.findByUsername(users.getUsername()).isPresent()){
            return "username exist";
        }
        else {
           existingUser.setUsername(users.getUsername());
        }
        return usersDao.save(existingUser);
    }



    public Users update_field_of_user(long id, Map<String, Optional> map) {

        Users users = usersDao.findById(id).get();

        for (Map.Entry<String, Optional> hm : map.entrySet()) {

            String keyFromMap = hm.getKey();

            if (keyFromMap.equals("firstName")) {

                Optional<Object> oa = hm.getValue();
                String value = oa.map(Object::toString).orElse(null);
                users.setFirsName(value);

            }

            if (keyFromMap.equals("username")) {

                Optional<Object> oa = hm.getValue();
                String value1 = oa.map(Object::toString).orElse(null);
               users.setUsername(value1);

            }

            if (keyFromMap.equals("email")) {

                Optional<Object> oa = hm.getValue();
                String value2 = oa.map(Object::toString).orElse(null);
                users.setEmail(value2);

            }

            if (keyFromMap.equals("lastName")) {

                Optional<Integer> oa = hm.getValue();
                String value3 = oa.map(Object::toString).orElse(null);
                users.setLastName(value3);

            }


        }

        return usersDao.save(users);
    }








}
