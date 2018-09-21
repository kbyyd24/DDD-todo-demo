package cn.gaoyuexiang.todo.demo.todoItem.service;

import cn.gaoyuexiang.todo.demo.todoItem.model.CheckItem;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class TodoItemFactory {

    public TodoItem create(String description, List<String> checkList) {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(UUID.randomUUID().toString());
        todoItem.setDescription(description);
        List<CheckItem> checkItemList = checkList.stream()
                .map(checkDescription -> {
                    CheckItem checkItem = new CheckItem();
                    checkItem.setId(UUID.randomUUID().toString());
                    checkItem.setDescription(checkDescription);
                    checkItem.setStatus(CheckItem.Status.UNCHECKED);
                    return checkItem;
                })
                .collect(toList());
        todoItem.setCheckList(checkItemList);
        return todoItem;
    }
}
