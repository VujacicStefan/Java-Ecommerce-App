package com.example.demo.dao;




import com.example.demo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {


    Optional<Role>findById(Long id);


}