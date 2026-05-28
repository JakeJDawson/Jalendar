package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * This file basically allows database access for multiple other files, despite
 * how short it is. Pretty critical, and it works because JPA is awesome.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
