package cn.gaoyuexiang.todo.demo.todoItem.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "todo_item")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class TodoItem {
    @Id
    private String id;
    private String description;
    @ElementCollection
    @CollectionTable(
            name = "check_item",
            joinColumns = @JoinColumn(name = "todo_item_id")
    )
    private List<CheckItem> checkList;

    public TodoItem(String description, List<CheckItem> checkList) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.checkList = checkList;
    }
}
