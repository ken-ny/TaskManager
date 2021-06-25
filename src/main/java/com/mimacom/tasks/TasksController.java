package com.mimacom.tasks;

import com.mimacom.tasks.entities.Task;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TasksController {

    private final TasksRepository repository;
    private final TaskModelAssembler assembler;

    TasksController(TasksRepository repository, TaskModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * List all the tasks in the BD
     * @return List of all the existing tasks
     */
    @GetMapping("/tasks")
    CollectionModel<EntityModel<Task>> allTasks() {
        List<EntityModel<Task>> tasks = repository.findAll().stream()
                .map(assembler :: toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks, linkTo(methodOn(TasksController.class).allTasks()).withSelfRel());

    }

    /**
     * Creates a new task with an automatic id
     * @param newTask data of the new tas to create
     * @return The created task with the new id
     */
    @PostMapping("/task")
    EntityModel<Task> newTask(@RequestBody Task newTask) {
        return assembler.toModel(repository.save(newTask));
    }

    /**
     * Recovers all the information regarding a task
     * @param id id of the task we want to recover
     * @return Returns the description and current status of the task or an error in case the task doesn't exist
     */
    @GetMapping("/tasks/{id}")
    EntityModel<Task> oneTask(@PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return assembler.toModel(task);
    }

    /**
     * Sets a task as complete or, in case the task was already completed, sets it as uncompleted.
     * @param id id of the task to change
     * @return The updated information of the task, or an error in case the task doesn't exist
     */
    @PutMapping("/task/{id}/complete")
    EntityModel<Task> completeTask(@PathVariable Long id) {
        return repository.findById(id)
                .map(task -> {
                    task.setDone(!task.getDone());
                    return assembler.toModel(repository.save(task));
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Updates the information on a task
     * @param updatedTask new information
     * @param id id of the task we want to update
     * @return The updated information of the task, or an error in case the task doesn't exist
     */
    @PutMapping("/task/{id}")
    EntityModel<Task> updateTask(@RequestBody Task updatedTask, @PathVariable Long id) {

        return repository.findById(id)
                .map(task -> {
                    task.setDone(updatedTask.getDone());
                    task.setDescription(updatedTask.getDescription());
                    return assembler.toModel(repository.save(task));
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Deletes a task
     * @param id id of the task we want to delete
     */
    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
