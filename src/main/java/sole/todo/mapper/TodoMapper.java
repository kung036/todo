package sole.todo.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sole.todo.dto.TodoDto;
import sole.todo.entity.Todo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoDtoToTodo(TodoDto todoDto);
    TodoDto todoToTodoDto(Todo todo);
    List<TodoDto> todoToTodoDtos (List<Todo> todo);
}
