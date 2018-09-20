package cn.gaoyuexiang.todo.demo.controller;

import cn.gaoyuexiang.todo.demo.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.model.TodoItem;
import cn.gaoyuexiang.todo.demo.repository.TodoItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoItemRepository todoItemRepository;

    @Test
    public void should_create_todo_item() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String description = "This is a new item";
        CreateTodoItemCommand command = new CreateTodoItemCommand(description);
        String commandJson = objectMapper.writeValueAsString(command);
        this.mockMvc.perform(
                post("/api/todo-items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(commandJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", isA(String.class)))
                .andExpect(jsonPath("$.description", is(description)))
                .andDo(print());
        verify(todoItemRepository).save(any(TodoItem.class));
    }
}