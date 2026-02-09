package barn.tasks;

import java.util.ArrayList;

/**
 * Abstract class representing a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private ArrayList<String> tags;

    /** Constructor for a Task object*/
    public Task(String description, ArrayList<String> tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
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

    protected String getTagsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append(tag).append(" ");
        }
        return sb.toString().trim();
    }

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

    protected String getFormattedTags() {
        if (tags.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(" | ");
            for (String tag : tags) {
                sb.append(tag).append(" ");
            }
            return sb.toString();
        }
    }
}
