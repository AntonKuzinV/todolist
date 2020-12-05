package com.itr.todolist.task.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException(int id){
    super("No task of id \"" + id + "\" found!", null, false, false);
  }
}
