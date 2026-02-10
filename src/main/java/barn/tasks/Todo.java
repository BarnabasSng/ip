package barn.tasks;

import java.util.ArrayList;

/**
 * Todo class representing a task with no other qualifiers.
 */
public class Todo extends Task {

    public Todo(String description, ArrayList<String> tags) {
        super(description, tags);
    }

    /** Returns formatted string for storage in txt file */
    public String getFormattedString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description + this.getFormattedTags();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + getTagsAsString();
    }
}
