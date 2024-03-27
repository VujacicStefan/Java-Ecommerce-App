package com.example.demo.controller;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Users;
import com.example.demo.service.UserService;
import com.example.demo.service.UsersDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
public class UserController {

    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/register_User")
    public Users register_User(@RequestBody Users user) {
        return service.register_user(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/for admin")
    public String url_admin () {
        return "this URL is accessible only to admin";
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/for user")
    public String url_user () {
        return "this URL is accessible only to user";
    }

    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @PostMapping ("/register_new_user")
    public Users register_new_user(@RequestBody Users user1) {
        return service.registerNewUser(user1);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @GetMapping("find/user/{id}")
    public UsersDto findUsers(@RequestBody UsersDto usersDto, @PathVariable long id) {
        return service.findUser(usersDto,id);
    }

     @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @DeleteMapping({"delete/user/{id}"})
    public String deleteUser(@PathVariable long id) {
        service.deleteUser(id);
        return "Deleted";
    }

    @PreAuthorize("hasAnyRole('CUSTOMER''ADMIN')")
    @GetMapping({"/login"})
    public String users(@RequestBody Users users1) {
         service.login_user(users1);
         return "login";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Users> productUpdate(@PathVariable long id, @RequestBody Users users) {
        service.update_user(id,users);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("users", "value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("patch/product/{id}")
    public ResponseEntity<Users> productPatch(@PathVariable long id, @RequestBody Map<String, Optional> map) {
        service.update_field_of_user(id, map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "value");
        return ResponseEntity.ok().headers(httpHeaders).build();
    }




}
