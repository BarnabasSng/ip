package barn.tasks;

/**
 * Abstract class representing a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /** Constructor for a Task object*/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    public abstract String getFormattedString();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task other)) {
            return false;
        }
        return this.isDone == other.isDone
                && this.description.equals(other.description)
                && this.getClass().equals(other.getClass());
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
