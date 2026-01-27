package Barn.tasks;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int done) {
        super(description);
        if (done == 1) {
            this.markAsDone();
        }
    }

    public String getFormattedString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}