package barn.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getDate_validDateFormat_success() {
        String date = "2026-02-10";
        String formattedDate = "Feb 10 2026";
        ArrayList<String> tags = new ArrayList<>();
        assertEquals(formattedDate, new Deadline("test", date, tags).getDate());
    }

    @Test
    public void getDate_invalidDateFormat_success() {
        String date = "tomorrow";
        ArrayList<String> tags = new ArrayList<>();
        assertEquals(date, new Deadline("test", date, tags).getDate());
    }
}
