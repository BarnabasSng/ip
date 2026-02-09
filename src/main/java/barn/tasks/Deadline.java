package barn.tasks;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Deadline class representing a task with a specified deadline.
 */
public class Deadline extends Task {
    protected String by;

    /** Creates a Deadline task */
    public Deadline(String description, String by, ArrayList<String> tags) {
        super(description, tags);
        this.by = by;
    }

    /** Returns formatted string for storage in txt file */
    public String getFormattedString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by
                + this.getFormattedTags();
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
        return "[D]" + super.toString() + " (by: " + this.getDate() + ") " + getTagsAsString();
    }
}
