package cn.gaoyuexiang.todo.demo.todoItem.service;

import cn.gaoyuexiang.todo.demo.todoItem.TodoItemApplicationService;
import cn.gaoyuexiang.todo.demo.todoItem.command.CreateTodoItemCommand;
import cn.gaoyuexiang.todo.demo.todoItem.command.UpdateTodoItemDescriptionCommand;
import cn.gaoyuexiang.todo.demo.todoItem.model.CheckItem;
import cn.gaoyuexiang.todo.demo.todoItem.model.TodoItem;
import cn.gaoyuexiang.todo.demo.todoItem.repository.TodoItemRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TodoItemApplicationServiceTest {

    private TodoItemFactory todoItemFactory = new TodoItemFactory();
    private TodoItemRepository repository = Mockito.mock(TodoItemRepository.class);

    private TodoItemApplicationService service = new TodoItemApplicationService(todoItemFactory, repository);

    @Test
    public void should_create_todo_item() {
        //given
        String description = "This is a new todo item";
        CreateTodoItemCommand command = new CreateTodoItemCommand(description, emptyList());

        //when
        TodoItem todoItem = service.createTodoItem(command);

        //then
        assertNotNull(todoItem.getId());
        assertThat(todoItem.getDescription(), is(description));
        verify(repository).save(todoItem);
    }

    @Test
    public void should_create_todo_item_with_check_list() {
        //given
        String description = "This is a new todo item";
        List<String> checkList = asList("step1", "step2");
        CreateTodoItemCommand command = new CreateTodoItemCommand(description, checkList);

        //when
        TodoItem todoItem = service.createTodoItem(command);

        //then
        assertNotNull(todoItem.getId());
        assertThat(todoItem.getDescription(), is(description));
        assertThat(todoItem.getCheckList().get(0).getDescription(), is("step1"));
        assertThat(todoItem.getCheckList().get(0).getStatus(), is(CheckItem.Status.UNCHECKED));
        assertThat(todoItem.getCheckList().get(1).getDescription(), is("step2"));
        assertThat(todoItem.getCheckList().get(1).getStatus(), is(CheckItem.Status.UNCHECKED));
        verify(repository).save(todoItem);
    }

    @Test
    public void should_update_todo_item_description() {
        //given
        TodoItem todoItem = new TodoItem("old description", emptyList());
        String newDescription = "new description";
        UpdateTodoItemDescriptionCommand command = new UpdateTodoItemDescriptionCommand(newDescription);
        given(repository.findById(todoItem.getId())).willReturn(Optional.of(todoItem));

        //when
        TodoItem updatedTodoItem = service.updateDescription(todoItem.getId(), command);

        //then
        assertThat(updatedTodoItem.getDescription(), is(newDescription));
    }
}