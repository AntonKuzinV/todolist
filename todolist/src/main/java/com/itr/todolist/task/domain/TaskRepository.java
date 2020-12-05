package com.itr.todolist.task.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

interface TaskRepository extends Repository<Task, Integer> {

  List<Task> findAll();

  boolean existsById(int id);

  void deleteById(int id);

  Task save(Task task);

  Optional<Task> findById(int id);
}
