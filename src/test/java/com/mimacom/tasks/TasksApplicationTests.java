package com.mimacom.tasks;

import com.mimacom.tasks.entities.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class TasksApplicationTests {

	@Autowired
	TasksController tasksController;

	@Test
	public void testListAllTasks() {
		assertEquals(2, tasksController.allTasks().getContent().size());
	}

	@Test
	public void testListSingleTask() {
		assertEquals(true, tasksController.oneTask(1L).getContent().getDone());
	}

	@Test
	public void testNewTask() {
		assertEquals(false, tasksController.newTask(new Task("mock test", false)).getContent().getDone());
	}

	@Test
	public void testDoneTask() {
		assertEquals(true, tasksController.completeTask(3L).getContent().getDone());
	}

	@Test
	public void testUnDoneTask() {
		assertEquals(false, tasksController.completeTask(3L).getContent().getDone());
	}

	@Test
	public void testUpdateTask() {
		assertEquals("test", tasksController.updateTask(new Task("test", false), 2L).getContent().getDescription());
	}
}
