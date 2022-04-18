package com.s0qva.todobackend.model.enumeration;

public enum TaskStatus {
    CREATED("The task has been created"),
    IN_PROGRESS("The task is being processed"),
    COMPLETED("The task has been completed");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
