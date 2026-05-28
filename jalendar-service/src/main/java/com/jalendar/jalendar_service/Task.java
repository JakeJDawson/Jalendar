package com.jalendar.jalendar_service;

// Import statements.
import jakarta.persistence.*;

/**
 * This file effectively defines what a task is in the database. Without it,
 * tasks wouldn't work at all. The @Entity tag is there to show that the class
 * maps to a database table.
 */
@Entity
public class Task {
    // Unique identifier for each task, auto-incremented by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title and description for each task, not inherently unique.
    private String title;
    private String description;

    // How it connects to a user, that being, many tasks to one user.
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    // Getters.
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public User getUser() { return user; }

    // Setters.
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String desc) { this.description = desc; }
    public void setUser(User user) { this.user = user; }
}
