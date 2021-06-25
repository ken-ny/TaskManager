package com.mimacom.tasks;

import com.mimacom.tasks.commons.Errors;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super(Errors.TASK_NOT_EXISTS.toString() + id);
    }
}
