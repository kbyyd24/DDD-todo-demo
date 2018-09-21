package cn.gaoyuexiang.todo.demo.todoItem.service;

import cn.gaoyuexiang.todo.demo.todoItem.model.CheckItem;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TodoItemFactory {

    public TodoItem create(String description, List<String> checkList) {
        List<CheckItem> checkItemList = checkList.stream()
                .map(CheckItem::new)
                .collect(toList());
        return new TodoItem(description, checkItemList);
    }
}
