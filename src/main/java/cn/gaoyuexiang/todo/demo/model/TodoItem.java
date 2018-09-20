package cn.gaoyuexiang.todo.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo_item")
@NoArgsConstructor
@Setter
@Getter
public class TodoItem {
    @Id
    private String id;
    private String description;
}
