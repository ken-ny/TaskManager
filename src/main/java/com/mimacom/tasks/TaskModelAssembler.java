package com.mimacom.tasks;

import com.mimacom.tasks.entities.Task;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<Task, EntityModel<Task>> {
    @Override
    public EntityModel<Task> toModel(Task task) {

        return EntityModel.of(task, //
                linkTo(methodOn(TasksController.class).oneTask(task.getId())).withSelfRel(),
                linkTo(methodOn(TasksController.class).allTasks()).withRel("tasks"));
    }
}
