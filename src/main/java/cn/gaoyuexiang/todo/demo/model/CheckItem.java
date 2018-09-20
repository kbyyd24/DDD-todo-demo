package cn.gaoyuexiang.todo.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class CheckItem {

    private String description;
    private Status status;

    public enum Status {
        UNCHECKED
    }
}
