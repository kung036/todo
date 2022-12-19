package sole.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoDto {
    private long todoId;

    private String title;

    private long todo_order;

    private boolean completed;

    public boolean getCompleted() {
        return completed;
    }
}
