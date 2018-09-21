package cn.gaoyuexiang.todo.demo.todoItem;

import cn.gaoyuexiang.todo.demo.todoItem.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.todoItem.command.UpdateTodoItemDescriptionCommand;
import cn.gaoyuexiang.todo.demo.todoItem.exception.NotFoundException;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import cn.gaoyuexiang.todo.demo.todoItem.repository.TodoItemRepository;
import cn.gaoyuexiang.todo.demo.todoItem.service.TodoItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoItemApplicationService {

    private final TodoItemFactory todoItemFactory;
    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemApplicationService(TodoItemFactory todoItemFactory, TodoItemRepository todoItemRepository) {
        this.todoItemFactory = todoItemFactory;
        this.todoItemRepository = todoItemRepository;
    }

    @Transactional
    public TodoItem createTodoItem(CreateTodoItemCommand command) {
        TodoItem todoItem = todoItemFactory.create(command.getDescription(), command.getCheckList());
        todoItemRepository.save(todoItem);
        return todoItem;
    }

    @Transactional
    public TodoItem getTodoItem(String id) {
        return todoItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find TodoItem by " + id));
    }

    @Transactional
    public TodoItem updateDescription(String id, UpdateTodoItemDescriptionCommand command) {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find TodoItem by " + id));
        todoItem.updateDescription(command.getDescription());
        // automatically save todoItem when transaction committed by JPA
        return todoItem;
    }
}
