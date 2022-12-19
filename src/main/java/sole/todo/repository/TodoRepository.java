package sole.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sole.todo.entity.Todo;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTodoId(long id);
}
