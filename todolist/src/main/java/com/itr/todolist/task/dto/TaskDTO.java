package com.itr.todolist.task.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

public class TaskDTO {

  private int    id;
  private int    priority;
  @NotNull
  private String text;
  private int    state;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public static TaskDTOBuilder builder() {
    return new TaskDTOBuilder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskDTO taskDTO = (TaskDTO) o;
    return id == taskDTO.id &&
        priority == taskDTO.priority &&
        state == taskDTO.state &&
        Objects.equals(text, taskDTO.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, priority, text, state);
  }
}
