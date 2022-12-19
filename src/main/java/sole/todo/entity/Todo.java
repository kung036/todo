package sole.todo.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sole.todo.audit.Auditable;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class Todo extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private long todo_order;

    @Column(nullable = false)
    private boolean completed = false;

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Object b) {
        completed = (boolean) b;
    }
}
