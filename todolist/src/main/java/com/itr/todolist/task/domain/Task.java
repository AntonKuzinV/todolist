package com.itr.todolist.task.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.itr.todolist.task.dto.TaskDTO;

@Entity
class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int    id;
  private int    priority;
  private String text;
  //0 - wait, 1 in-progress, 2 done
  private int    state;

  Task() {
  }

  private Task(int priority, String text) {
    this.priority = priority;
    this.text = text;
    this.state = 0;
  }

  private Task(int id, int priority, String text, int state) {
    this.id = id;
    this.priority = priority;
    this.text = text;
    this.state = state;
  }

  TaskDTO dto() {
    return TaskDTO.builder()
        .withId(this.id)
        .withPriority(this.priority)
        .withState(this.state)
        .withText(this.text).build();
  }

  static Task create(TaskDTO dto) {
    return new Task(dto.getPriority(), dto.getText());
  }

  static Task from(TaskDTO task) {
    return new Task(task.getId(), task.getPriority(), task.getText(), task.getState());
  }
}
