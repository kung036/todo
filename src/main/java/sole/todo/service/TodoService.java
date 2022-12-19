package sole.todo.service;

import org.springframework.stereotype.Service;
import sole.todo.controller.TodoController;
import sole.todo.entity.Todo;
import sole.todo.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(long todoId, Todo todo) {
        Todo findTodo = findVerifiedTodo(todoId);
        findTodo.setTodoId(todoId);

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(order -> findTodo.setTodo_order(order));

        return todoRepository.save(todo);
    }

    public void deleteTodo(long todoId) {
        Todo todo = findVerifiedTodo(todoId);
        todoRepository.delete(todo);
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    public Todo findVerifiedTodo(long todoId) {
        return todoRepository.findByTodoId(todoId)
                .orElseThrow(() -> new NullPointerException("Todo Not Find"));
    }
}
