package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** 
 * This file is basically the way the database accesses tasks, despite how
 * short it is. JPA is pretty awesome. 
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
