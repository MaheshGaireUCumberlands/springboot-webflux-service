package com.mahesh.webflux.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Task(
        String id,
        String title,
        String description,
        boolean completed,
        LocalDateTime createdAt
) {
    public Task withCompleted(boolean completed) {
        return new Task(this.id, this.title, this.description, completed, this.createdAt);
    }
}
