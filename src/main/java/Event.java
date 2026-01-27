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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}