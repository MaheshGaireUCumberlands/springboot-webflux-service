package com.mahesh.webflux.controller;

import com.mahesh.webflux.model.Task;
import com.mahesh.webflux.service.TaskService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tasks")
@Tag(name="Tasks Endpoint", description = "Controller methods to interact with Tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(description = "Get a list of all tasks")
    @GetMapping
    public Flux<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(description = "Get details about a task based on its ID")
    @GetMapping("/{id}")
    public Mono<Task> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @Operation(description = "Create a task")
    @PostMapping
    public Mono<Task> createTask(
            @RequestParam @Parameter(description = "Summarized title of a task") String title,
            @RequestParam @Parameter(description = "Description of the task") String description) {
        return taskService.createTask(title, description);
    }

    @Hidden
    @PostMapping("/test")
    public Mono<Task> createTaskForTest(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @Operation(description = "Mark a existing task as Completed (if not already Completed")
    @PutMapping("/{id}/complete")
    public Mono<Task> completeTask(
            @PathVariable @Parameter(description = "ID of the task to be marked as completed") String id) {
        return taskService.markTaskCompleted(id);
    }
}