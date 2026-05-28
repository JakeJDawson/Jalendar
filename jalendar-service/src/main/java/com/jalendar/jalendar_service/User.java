package com.jalendar.jalendar_service;

// Import statements.
import jakarta.persistence.*;
import java.util.List;

/**
 * This file effectively defines what a user is in the database. Without it,
 * users wouldn't work at all. The @Entity tag is there to show that the class
 * maps to a database table.
 */
@Entity
// Explicitly nameing the database table.
@Table(name = "jUser")
public class User {
    // Unique identifier for each user, auto-incremented by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title and description for each task, not inherently unique.
    /**
     * Small note, based on my other code, email MIGHT be forced to be unique.
     * I definitely want it to be unique, so I'll come back to make sure of
     * that at some point, but this file specifically DOESN'T define it as
     * unique at this moment.
     */
    private String email;
    private String password;

    // How it connects to a task, that being, one user to many tasks.
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    // Getters.
    public Long getId() { return id; }
    public String getEmail() { return email; }
    /** 
     * This REALLY shouldn't have a getter. That seems really bad. Currently
     * the application needs the getter to work, but as I'm currently going
     * through to review my code, and commenting things that I've neglected
     * to, I wanted to make a note for myself to try and remove the need for
     * this at some point.
     */
    public String getPassword() { return password; }

    // Setters.
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
