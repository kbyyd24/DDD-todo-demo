package cn.gaoyuexiang.todo.demo.todoItem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.UUID;

import static cn.gaoyuexiang.todo.demo.todoItem.model.CheckItem.Status.UNCHECKED;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class CheckItem {

    private String id;
    private String description;
    private Status status;

    public CheckItem(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.status = UNCHECKED;
    }

    public enum Status {
        UNCHECKED
    }
}
