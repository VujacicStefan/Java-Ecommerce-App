package com.example.demo.dao;

import com.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(Long id);
}
