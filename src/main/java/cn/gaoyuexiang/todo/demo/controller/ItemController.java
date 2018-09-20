package cn.gaoyuexiang.todo.demo.controller;

import cn.gaoyuexiang.todo.demo.model.TodoItem;
import cn.gaoyuexiang.todo.demo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/todo-items")
public class ItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public ItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public TodoItem createItem(@RequestBody Map<String, String> requestBody) {
        return todoItemService.createTodoItem(requestBody.get("description"));
    }

}
