package com.mimacom.tasks.commons;

public enum Errors {

    TASK_NOT_EXISTS("Could not find task ");

    String description;

    Errors(String error) {
        this.setDescription(error);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return this.getDescription();
    }
}
