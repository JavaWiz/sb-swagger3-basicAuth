package com.javawiz.task.controller;

import com.javawiz.task.model.Task;
import com.javawiz.task.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@SecurityRequirement(name = "TaskAPISecureScheme")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getTask() {
        return taskService.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public Task editTask(@PathVariable long id, @RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        Task existingTask = taskService.findById(id);
        taskService.delete(existingTask);
    }
}
