package com.example.demo.service;


import com.example.demo.Entity.Users;
import org.springframework.stereotype.Service;
import java.util.function.Function;


@Service
public class UsersDtoMapper implements Function<Users,UsersDto> {
    @Override
    public UsersDto apply(Users users) {         //transfer User to UserDto
        return new UsersDto(
                users.getUsername(),
                users.getFirsName(),
                users.getLastName(),
                users.getEmail()
        );



    }
}
