package com.mimacom.tasks;

import com.mimacom.tasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Long> {
}
