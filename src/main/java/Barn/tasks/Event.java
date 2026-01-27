package tasks;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, int done, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        if (done == 1) {
            this.markAsDone();
        }
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