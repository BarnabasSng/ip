package Barn.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class representing a task with a specified deadline.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /** Returns formatted string for storage in txt file */
    public String getFormattedString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    /**
     * Get deadline in formatted date (Month Day Year) if given date can be
     * parsed. Otherwise, return the deadline as the given user input string
     * 
     * @return String of either formatted date or user input date
     */
    public String getDate() {
        try {
            LocalDate date = LocalDate.parse(this.by);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return formattedDate;
        } catch (DateTimeParseException e) {
            return this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
