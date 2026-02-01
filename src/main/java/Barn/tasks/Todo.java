package Barn.tasks;

/**
 * Todo class representing a task with no other qualifiers.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getFormattedString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
