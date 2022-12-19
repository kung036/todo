package sole.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sole.todo.dto.TodoDto;
import sole.todo.entity.Todo;
import sole.todo.mapper.TodoMapper;
import sole.todo.service.TodoService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto todoDto) {
        System.out.println("좀!!!" + LocalDateTime.now());

        Todo todo = todoService.createTodo(todoMapper.todoDtoToTodo(todoDto));

        return new ResponseEntity<>(todoMapper.todoToTodoDto(todo),
                HttpStatus.CREATED);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId) {
        Todo todo = todoService.findTodo(todoId);

        return new ResponseEntity<>(todoMapper.todoToTodoDto(todo),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todo = todoService.findTodos();

        return new ResponseEntity<>(todoMapper.todoToTodoDtos(todo),
                HttpStatus.OK);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(
            @PathVariable("todo-id") @Positive long todoId,
            @Valid @RequestBody TodoDto todoDto) {

//        boolean b = null;
//        if(todoDto.getCompleted() == null)
        Todo todo = todoService.updateTodo(todoId, todoMapper.todoDtoToTodo(todoDto));

        return new ResponseEntity<>(todoMapper.todoToTodoDto(todo),
                HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive long todoId) {
        todoService.deleteTodo(todoId);


        return new ResponseEntity<>("Delete " + todoId + " Completed",
                HttpStatus.NO_CONTENT);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();

        return new ResponseEntity<>("All Delete Completed",
                HttpStatus.NO_CONTENT);
    }
}