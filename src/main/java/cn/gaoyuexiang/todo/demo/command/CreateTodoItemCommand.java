package cn.gaoyuexiang.todo.demo.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateTodoItemCommand {
    private String description;

    @JsonCreator
    public CreateTodoItemCommand(@JsonProperty("description") String description) {
        this.description = description;
    }
}

