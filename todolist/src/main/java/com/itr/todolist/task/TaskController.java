package com.itr.todolist.task;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itr.todolist.task.domain.TaskFacade;
import com.itr.todolist.task.dto.TaskDTO;

@RestController
@RequestMapping("/api/tasks")
class TaskController {

  private final TaskFacade taskFacade;

  TaskController(TaskFacade taskFacade) {
    this.taskFacade = taskFacade;
  }

  @GetMapping
  public ResponseEntity<List<TaskDTO>> findAll() {
    return ResponseEntity.ok(taskFacade.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<TaskDTO> findById(@PathVariable int id) {
    return ResponseEntity.ok(taskFacade.findById(id));
  }

  @PostMapping
  public ResponseEntity<TaskDTO> newTask(@Valid @RequestBody TaskDTO task) {
    return ResponseEntity.ok(taskFacade.createTask(task));
  }

  @DeleteMapping("{id}")
  public ResponseEntity deleteTask(@PathVariable int id) {
    taskFacade.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}")
  public ResponseEntity updateTask(@PathVariable int id, @Valid @RequestBody TaskDTO task) {
    taskFacade.update(id, task);
    return ResponseEntity.noContent().build();
  }
}
