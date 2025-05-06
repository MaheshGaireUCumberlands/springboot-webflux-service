package com.mahesh.webflux.controller;

import com.mahesh.webflux.model.Task;
import com.mahesh.webflux.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@WebFluxTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    private WebTestClient webTestClient;

    private Task mockTask;

    @BeforeEach
    void setUp() {
        mockTask = new Task(
                UUID.randomUUID().toString(),
                "Test Task",
                "Unit testing the REST controller",
                false,
                LocalDateTime.now()
        );
    }

    @Test
    void shouldReturnAllTasks() {
        Mockito.when(taskService.getAllTasks()).thenReturn(Flux.just(mockTask));

        webTestClient.get()
                .uri("/api/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Task.class)
                .hasSize(1)
                .contains(mockTask);
    }

    @Test
    void shouldReturnTaskById() {
        Mockito.when(taskService.getTaskById(mockTask.id())).thenReturn(Mono.just(mockTask));

        webTestClient.get()
                .uri("/api/tasks/{id}", mockTask.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Task.class)
                .isEqualTo(mockTask);
    }

    @Test
    void shouldCreateTask() {
        Mockito.when(taskService.createTask(Mockito.any(Task.class))).thenReturn(Mono.just(mockTask));

        webTestClient.post()
                .uri("/api/tasks/test")
                .bodyValue(mockTask)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Task.class)
                .isEqualTo(mockTask);
    }


    @Test
    void shouldCompleteTask() {
        // Mock the service behavior, assuming the service method marks the task as completed.
        Mockito.when(taskService.markTaskCompleted(mockTask.id())).thenReturn(Mono.just(mockTask.withCompleted(true)));

        webTestClient.put()
                .uri("/api/tasks/{id}/complete", mockTask.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.completed").isEqualTo(true);
    }
}
