package Barn.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDate_validDateFormat_success() {
        String date = "2026-02-10";
        String formattedDate = "Feb 10 2026";
        assertEquals(formattedDate, new Deadline("test", date).getDate());
    }

    @Test
    public void getDate_invalidDateFormat_success() {
        String date = "tomorrow";
        assertEquals(date, new Deadline("test", date).getDate());
    }
}
