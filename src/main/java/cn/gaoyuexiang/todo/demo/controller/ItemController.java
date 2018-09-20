package cn.gaoyuexiang.todo.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/todo-items")
public class ItemController {

    @PostMapping
    public Map<String, String> createItem(@RequestBody Map<String, String> requestBody) {
        HashMap<String, String> result = new HashMap<>();
        result.put("description", requestBody.get("description"));
        result.put("id", UUID.randomUUID().toString());
        return result;
    }

}
