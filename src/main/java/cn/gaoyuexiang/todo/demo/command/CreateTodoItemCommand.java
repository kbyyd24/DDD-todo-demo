package cn.gaoyuexiang.todo.demo.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateTodoItemCommand {
    private String description;
    private List<String> checkList;

    @JsonCreator
    public CreateTodoItemCommand(@JsonProperty("description") String description,
                                 @JsonProperty("checkList") List<String> checkList) {
        this.description = description;
        this.checkList = checkList;
    }
}

