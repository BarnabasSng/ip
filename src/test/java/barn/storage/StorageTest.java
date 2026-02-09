package barn.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import barn.tasks.Deadline;
import barn.tasks.Event;
import barn.tasks.Task;
import barn.tasks.Todo;

public class StorageTest {
    @Test
    public void load_validTxt_success() throws Exception {
        ArrayList<Task> correctTasks = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        correctTasks.add(new Todo("read book", tags));
        correctTasks.add(new Deadline("return book", "2026-02-10", tags));
        correctTasks.add(new Event("meeting", "2pm", "4pm", tags));
        assertEquals(correctTasks, new Storage("data/valid.txt").load());
    }

    @Test
    public void load_invalidTxt_success() {
        try {
            ArrayList<Task> correctTasks = new ArrayList<>();
            ArrayList<String> tags = new ArrayList<>();
            correctTasks.add(new Todo("read book", tags));
            assertEquals(correctTasks, new Storage("data/invalid.txt").load());
        } catch (Exception e) {
            assertEquals("Error when loading Barn.txt file", e.getMessage());
        }
    }
}
