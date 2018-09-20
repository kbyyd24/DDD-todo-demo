package cn.gaoyuexiang.todo.demo.service;

import cn.gaoyuexiang.todo.demo.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.model.CheckItem;
import cn.gaoyuexiang.todo.demo.model.TodoItem;
import cn.gaoyuexiang.todo.demo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

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
        List<CheckItem> checkList = command.getCheckList().stream()
                .map(description -> {
                    CheckItem checkItem = new CheckItem();
                    checkItem.setDescription(description);
                    checkItem.setStatus(CheckItem.Status.UNCHECKED);
                    return checkItem;
                })
                .collect(toList());
        todoItem.setCheckList(checkList);
        todoItemRepository.save(todoItem);
        return todoItem;
    }
}
