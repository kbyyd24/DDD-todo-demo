package cn.gaoyuexiang.todo.demo.todoItem.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateTodoItemDescriptionCommand {
    private String description;

    @JsonCreator
    public UpdateTodoItemDescriptionCommand(@JsonProperty("description") String description) {
        this.description = description;
    }
}
