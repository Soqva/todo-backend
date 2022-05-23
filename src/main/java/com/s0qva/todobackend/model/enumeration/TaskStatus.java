package com.s0qva.todobackend.model.enumeration;

public enum TaskStatus {
    CREATED("The task has been created"),
    COMPLETED("The task has been completed");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
