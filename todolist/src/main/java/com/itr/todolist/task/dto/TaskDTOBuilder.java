package com.itr.todolist.task.dto;

public final class TaskDTOBuilder {

  private int    id;
  private int    priority;
  private String text;
  private int    state;

  TaskDTOBuilder() {
  }


  public TaskDTOBuilder withId(int id) {
    this.id = id;
    return this;
  }

  public TaskDTOBuilder withPriority(int priority) {
    this.priority = priority;
    return this;
  }

  public TaskDTOBuilder withText(String text) {
    this.text = text;
    return this;
  }

  public TaskDTOBuilder withState(int state) {
    this.state = state;
    return this;
  }

  public TaskDTO build() {
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(this.id);
    taskDTO.setPriority(this.priority);
    taskDTO.setState(this.state);
    taskDTO.setText(this.text);
    return taskDTO;
  }
}
