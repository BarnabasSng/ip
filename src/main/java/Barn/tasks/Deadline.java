package Barn.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, int done, String by) {
        super(description);
        this.by = by;
        if (done == 1) {
            this.markAsDone();
        }
    }

    public String getFormattedString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    private String getDate() {
        try {
            LocalDate date = LocalDate.parse(this.by);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return formattedDate;
        } catch (Exception e) {
            return this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}