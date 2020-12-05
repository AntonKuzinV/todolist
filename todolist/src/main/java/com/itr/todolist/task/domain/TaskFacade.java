package com.itr.todolist.task.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import com.itr.todolist.task.dto.TaskDTO;
import com.itr.todolist.task.dto.exceptions.TaskNotFoundException;

@Transactional
public class TaskFacade {

  private TaskRepository taskRepository;

  public TaskFacade(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<TaskDTO> findAll() {
    return this.taskRepository.findAll().stream().map(Task::dto).collect(Collectors.toList());
  }

  public TaskDTO findById(int id) {
    return this.taskRepository.findById(id).map(Task::dto).orElseThrow(() -> new TaskNotFoundException(id));
  }

  public TaskDTO createTask(TaskDTO task) {
    return this.taskRepository.save(Task.create(task)).dto();
  }

  public void deleteById(int id) {
    if (!this.taskRepository.existsById(id)) {
      throw new TaskNotFoundException(id);
    }
    this.taskRepository.deleteById(id);
  }

  public void update(int id, TaskDTO task) {
    if (!this.taskRepository.existsById(id)) {
      throw new TaskNotFoundException(id);
    }
    task.setId(id);
    this.taskRepository.save(Task.from(task));
  }
}
