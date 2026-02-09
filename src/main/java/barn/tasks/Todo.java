package barn.tasks;

/**
 * Todo class representing a task with no other qualifiers.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /** Returns formatted string for storage in txt file */
    public String getFormattedString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
