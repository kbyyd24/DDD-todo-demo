package cn.gaoyuexiang.todo.demo.controller;

import cn.gaoyuexiang.todo.demo.model.TodoItem;
import cn.gaoyuexiang.todo.demo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/todo-items")
public class ItemController {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public ItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @PostMapping
    public Map<String, String> createItem(@RequestBody Map<String, String> requestBody) {
        HashMap<String, String> result = new HashMap<>();
        result.put("description", requestBody.get("description"));
        result.put("id", UUID.randomUUID().toString());
        TodoItem todoItem = new TodoItem();
        todoItem.setId(result.get("id"));
        todoItem.setDescription(result.get("description"));
        todoItemRepository.save(todoItem);
        return result;
    }

}
