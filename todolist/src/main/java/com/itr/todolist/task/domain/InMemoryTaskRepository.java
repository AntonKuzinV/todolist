package com.itr.todolist.task.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.itr.todolist.task.dto.TaskDTO;

class InMemoryTaskRepository implements TaskRepository {

  private Map<Integer, Task> storage = new ConcurrentHashMap<>();
  private AtomicInteger      lastID  = new AtomicInteger();

  @Override
  public List<Task> findAll() {
    return new ArrayList<>(storage.values());
  }

  @Override
  public boolean existsById(int id) {
    return storage.containsKey(id);
  }

  @Override
  public void deleteById(int id) {
    storage.remove(id);
  }

  @Override
  public Task save(Task task) {
    TaskDTO dto = task.dto();
    dto.setId(lastID.incrementAndGet());
    Task newTask = Task.from(dto);
    storage.put(dto.getId(), newTask);
    return newTask;
  }

  @Override
  public Optional<Task> findById(int id) {
    return Optional.ofNullable(storage.get(id));
  }
}
