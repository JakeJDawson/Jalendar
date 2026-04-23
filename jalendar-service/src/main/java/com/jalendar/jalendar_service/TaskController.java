package com.jalendar.jalendar_service;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskRepository repo;
    private final UserRepository userRepo;

    public TaskController(TaskRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    // CREATE
    @PostMapping
    public Task create(@RequestParam(required = true) Long userID,
        @RequestBody Task task) {
            if(userID == null) {
                throw new RuntimeException("User must be logged in!");
            }

            User user = userRepo.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
            task.setUser(user);
            return repo.save(task);
    }

    // READ ALL
    @GetMapping
    public List<Task> getByUser(@RequestParam Long userID) {
        return repo.findByUserId(userID);
    }

    // READ ONE
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updated) {
        updated.setId(id);
        return repo.save(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
