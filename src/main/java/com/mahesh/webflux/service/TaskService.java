package com.mahesh.webflux.service;

import com.mahesh.webflux.model.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {
    private final Map<String, Task> taskStore = new ConcurrentHashMap<>();

    public Flux<Task> getAllTasks() {
        return Flux.fromIterable(taskStore.values());
    }

    public Mono<Task> getTaskById(String id) {
        Task task = taskStore.get(id);
            return task != null ? Mono.just(task) : Mono.empty();
    }

    public Mono<Boolean> deleteTaskById(String id) {
        Task task = taskStore.get(id);
        if (task != null) {
            taskStore.remove(id);
            return Mono.just(true);
        }
        return Mono.just(false);
    }

    public Mono<Task> createTask(String title, String description) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(id, title, description, false, LocalDateTime.now());
        taskStore.put(id, task);
        return Mono.just(task);
    }

    public Mono<Task> markTaskCompleted(String id) {
        Task existing = taskStore.get(id);
        if (existing == null) return Mono.empty();
        Task updated = new Task(existing.id(), existing.title(), existing.description(), true, existing.createdAt());
        taskStore.put(id, updated);
        return Mono.just(updated);
    }

    public Mono<Task> createTask(Task task) {
        taskStore.put(task.id(), task);
        return Mono.just(task);
    }
}