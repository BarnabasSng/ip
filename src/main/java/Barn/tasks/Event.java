package Barn.tasks;

/**
 * Event class representing an event with a specified time period.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFormattedString() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.from +
                " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
