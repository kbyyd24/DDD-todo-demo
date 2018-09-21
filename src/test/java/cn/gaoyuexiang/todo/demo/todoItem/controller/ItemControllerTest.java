package cn.gaoyuexiang.todo.demo.todoItem.controller;

import cn.gaoyuexiang.todo.demo.todoItem.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.todoItem.command.UpdateTodoItemDescriptionCommand;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_create_todo_item() throws Exception {
        String description = "This is a new item";
        List<String> checkList = asList("step1", "step2");
        CreateTodoItemCommand command = new CreateTodoItemCommand(description, checkList);
        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/todo-items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", isA(String.class)))
                .andExpect(jsonPath("$.description", is(description)))
                .andExpect(jsonPath("$.checkList[0].status", is("UNCHECKED")))
                .andExpect(jsonPath("$.checkList[0].description", is("step1")))
                .andExpect(jsonPath("$.checkList[1].status", is("UNCHECKED")))
                .andExpect(jsonPath("$.checkList[1].description", is("step2")))
                .andDo(print())
                .andReturn();
        TodoItem todoItem = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TodoItem.class);

        this.mockMvc.perform(get("/api/todo-items/{id}", todoItem.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void should_update_todo_item_description() throws Exception {
        String oldDescription = "This is old description";
        CreateTodoItemCommand createTodoItemCommand = new CreateTodoItemCommand(oldDescription, emptyList());
        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/todo-items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(createTodoItemCommand)))
                .andDo(print())
                .andReturn();
        TodoItem todoItem = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TodoItem.class);

        String newDescription = "This is new description";
        UpdateTodoItemDescriptionCommand command = new UpdateTodoItemDescriptionCommand(newDescription);
        this.mockMvc.perform(
                post("/api/todo-items/{id}/description", todoItem.getId())
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(command))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(newDescription)))
                .andDo(print());
    }
}