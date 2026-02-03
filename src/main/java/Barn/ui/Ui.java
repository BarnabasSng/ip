package Barn.ui;

import java.util.Scanner;

import Barn.exceptions.OutOfBoundsException;
import Barn.tasks.*;
import Barn.tasks.tasklist.TaskList;

/**
 * Handles interaction with the user by printing text and reading input.
 */
public class Ui {
    public String showWelcome() {
        return "Hello! I'm Barn\nWhat can I do for you?";
    }

    public String showLine() {
        return ("____________________________________________________________");
    }

    public String showError(String error) {
        return (error);
    }

    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    public String showAddTask(Task task) {
        return "Got it, I've added this task:" + System.lineSeparator() + task;
    }

    public String showDeleteTask(Task task) {
        return "Noted. I've removed this task:" + System.lineSeparator() + task;
    }

    public String showTaskCount(TaskList tasks) {
        return "Now you have " + tasks.getTaskCount() + " tasks in the list.";
    }

    public String showMark(Task task) {
        return "Nice! I've marked this task as done:" + System.lineSeparator() + task;
    }

    public String showUnmark(Task task) {
        return ("OK, I've marked this task as not done yet:" + System.lineSeparator() + task);
    }

    public String showTasks(TaskList tasks) throws OutOfBoundsException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.getTaskCount(); i++) {
            sb.append(i + 1)
                    .append(".")
                    .append(tasks.getTask(i))
                    .append(System.lineSeparator());
        }
        String result = sb.toString();
        return "Here are the tasks in your list:" + System.lineSeparator() + result;

    }

    public String showFoundTasks(TaskList tasks) throws OutOfBoundsException {
        StringBuilder sb = new StringBuilder();

        if (tasks.getTaskCount() == 0) {
            sb.append("No tasks matching keyword was found");
        } else {
            sb.append("Here are the matching tasks in your list:")
                    .append(System.lineSeparator());

            for (int i = 0; i < tasks.getTaskCount(); i++) {
                sb.append(i + 1)
                        .append(".")
                        .append(tasks.getTask(i))
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }
}
