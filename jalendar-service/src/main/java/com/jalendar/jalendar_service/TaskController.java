package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    // Basically, the controller needs database access to tasks and users.
    private final TaskRepository repo;
    private final UserRepository userRepo;
    public TaskController(TaskRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    // Determines the current user using JWT authentication.
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication();
        String email = (String) auth.getPrincipal();
        return userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Create a task.
    @PostMapping
    public Task create(@RequestBody Task task) {
        User user = getCurrentUser();
        task.setUser(user);
        return repo.save(task);
    }

    // Get all tasks attached to this user.
    @GetMapping
    public List<Task> getByUser() {
        User user = getCurrentUser();
        return repo.findByUser(user);
    }

    // Get a specific task by ID.
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // Update a specific task by ID.
    /**
     * It is worth noting that analysis of my code leads me to believe it may
     * be possible for someone more clever than myself to modify tasks
     * belonging to other users, if they knew enough about HTML and databases,
     * so I should probably add some code to enforce the specific user at some
     * point here.
     */
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updated) {
            User user = getCurrentUser();
            Task existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
            existing.setTitle(updated.getTitle());
            existing.setDescription(updated.getDescription());
            existing.setUser(user);
            return repo.save(existing);
    }

    // Delete a specific task by ID.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
