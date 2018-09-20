package cn.gaoyuexiang.todo.demo.repository;

import cn.gaoyuexiang.todo.demo.model.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, String> {
}
