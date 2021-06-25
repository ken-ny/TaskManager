package com.mimacom.tasks.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Task {

    private @Id @GeneratedValue Long id;
    private String description;
    private Boolean done;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Task() {
    }

    public Task(String description, Boolean done) {
        this.description = description;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status='" + this.done + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.done);
    }
}
