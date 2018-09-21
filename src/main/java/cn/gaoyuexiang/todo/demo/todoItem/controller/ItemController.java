package cn.gaoyuexiang.todo.demo.todoItem.controller;

import cn.gaoyuexiang.todo.demo.todoItem.TodoItemApplicationService;
import cn.gaoyuexiang.todo.demo.todoItem.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.todoItem.command.UpdateTodoItemDescriptionCommand;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todo-items")
public class ItemController {

    private final TodoItemApplicationService todoItemApplicationService;

    @Autowired
    public ItemController(TodoItemApplicationService todoItemApplicationService) {
        this.todoItemApplicationService = todoItemApplicationService;
    }

    @PostMapping
    public TodoItem createItem(@RequestBody CreateTodoItemCommand command) {
        return todoItemApplicationService.createTodoItem(command);
    }

    @GetMapping("{id}")
    public TodoItem getTodoItem(@PathVariable("id") String id) {
        return todoItemApplicationService.getTodoItem(id);
    }

    @PostMapping("{id}/description")
    public TodoItem updateDescription(@PathVariable("id") String id,
                                      @RequestBody UpdateTodoItemDescriptionCommand command) {
        return todoItemApplicationService.updateDescription(id, command);
    }

}
