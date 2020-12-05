package com.itr.todolist.task;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.itr.todolist.task.domain.TaskFacade;
import com.itr.todolist.task.dto.TaskDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  TaskFacade taskFacade;

  @Before
  public void setTaskFacade() {
    TaskDTO task = TaskDTO.builder()
        .withId(1)
        .withPriority(1)
        .withState(1)
        .withText("task")
        .build();
    when(taskFacade.findById(anyInt()))
        .thenReturn(task);
  }

  @Test
  public void findById() throws Exception {
    mockMvc.perform(get("/api/tasks/{id}", "1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("{\"id\":1,\"priority\":1,\"text\":\"task\",\"state\":1}")))
        .andDo(print());
  }
}