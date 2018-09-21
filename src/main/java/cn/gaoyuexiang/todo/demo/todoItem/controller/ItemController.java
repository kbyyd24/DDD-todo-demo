package cn.gaoyuexiang.todo.demo.todoItem.controller;

import cn.gaoyuexiang.todo.demo.todoItem.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import cn.gaoyuexiang.todo.demo.todoItem.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todo-items")
public class ItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public ItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public TodoItem createItem(@RequestBody CreateTodoItemCommand command) {
        return todoItemService.createTodoItem(command);
    }

}
