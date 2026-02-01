package Barn.storage;

import org.junit.jupiter.api.Test;

import Barn.tasks.Deadline;
import Barn.tasks.Event;
import Barn.tasks.Task;
import Barn.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class StorageTest {
    @Test
    public void load_validTxt_success() throws Exception {
        ArrayList<Task> correctTasks = new ArrayList<>();
        correctTasks.add(new Todo("read book"));
        correctTasks.add(new Deadline("return book", "2026-02-10"));
        correctTasks.add(new Event("meeting", "2pm", "4pm"));
        assertEquals(correctTasks, new Storage("data/valid.txt").load());
    }

    @Test
    public void load_invalidTxt_success() {
        try {
            ArrayList<Task> correctTasks = new ArrayList<>();
            correctTasks.add(new Todo("read book"));
            assertEquals(correctTasks, new Storage("data/invalid.txt").load());
        } catch (Exception e) {
            assertEquals("Error when loading Barn.txt file", e.getMessage());
        }
    }
}
