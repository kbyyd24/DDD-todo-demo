package cn.gaoyuexiang.todo.demo.service;

import cn.gaoyuexiang.todo.demo.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.model.TodoItem;
import cn.gaoyuexiang.todo.demo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }


    public TodoItem createTodoItem(CreateTodoItemCommand command) {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(UUID.randomUUID().toString());
        todoItem.setDescription(command.getDescription());
        todoItemRepository.save(todoItem);
        return todoItem;
    }
}
