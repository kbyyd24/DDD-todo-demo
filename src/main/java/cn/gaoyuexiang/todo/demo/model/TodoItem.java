package cn.gaoyuexiang.todo.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todo_item")
@NoArgsConstructor
@Setter
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
}
