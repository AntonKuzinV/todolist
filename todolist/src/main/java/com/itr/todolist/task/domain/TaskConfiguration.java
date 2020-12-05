package com.itr.todolist.task.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TaskConfiguration {

  TaskFacade taskFacade() {
    return taskFacade(new InMemoryTaskRepository());
  }

  @Bean
  TaskFacade taskFacade(TaskRepository taskRepository) {
    return new TaskFacade(taskRepository);
  }
}
