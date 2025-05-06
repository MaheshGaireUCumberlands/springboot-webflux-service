package com.mahesh.webflux.service;

import com.mahesh.webflux.model.Task;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class TaskServiceTest {

    private final TaskService taskService = new TaskService();

    @Test
    void testCreateAndRetrieveTask() {
        var title = "Test Task";
        var description = "Test Description";

        var createdTaskMono = taskService.createTask(title, description);

        StepVerifier.create(createdTaskMono)
                .expectNextMatches(task -> task.title().equals(title) && !task.completed())
                .verifyComplete();

        StepVerifier.create(taskService.getAllTasks())
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testMarkTaskCompleted() {
        var task = taskService.createTask("Title", "Desc").block();

        StepVerifier.create(taskService.markTaskCompleted(task.id()))
                .expectNextMatches(updated -> updated.completed())
                .verifyComplete();
    }
}
