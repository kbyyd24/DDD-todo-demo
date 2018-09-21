package cn.gaoyuexiang.todo.demo.todoItem.repository;

import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, String> {
}
