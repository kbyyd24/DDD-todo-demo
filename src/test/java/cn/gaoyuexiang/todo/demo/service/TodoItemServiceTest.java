package cn.gaoyuexiang.todo.demo.service;

import cn.gaoyuexiang.todo.demo.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.model.TodoItem;
import cn.gaoyuexiang.todo.demo.repository.TodoItemRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class TodoItemServiceTest {

    private TodoItemRepository repository = Mockito.mock(TodoItemRepository.class);

    private TodoItemService service = new TodoItemService(repository);

    @Test
    public void should_create_todo_item() {
        //given
        String description = "This is a new todo item";
        CreateTodoItemCommand command = new CreateTodoItemCommand(description);

        //when
        TodoItem todoItem = service.createTodoItem(command);

        //then
        assertNotNull(todoItem.getId());
        assertThat(todoItem.getDescription(), is(description));
        verify(repository).save(todoItem);
    }
}