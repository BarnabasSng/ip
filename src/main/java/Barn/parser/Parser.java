package Barn.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Barn.commands.AddCommand;
import Barn.commands.Command;
import Barn.commands.DeleteCommand;
import Barn.commands.ExitCommand;
import Barn.commands.FindCommand;
import Barn.commands.ListCommand;
import Barn.commands.MarkCommand;
import Barn.commands.UnmarkCommand;
import Barn.exceptions.InvalidCommandException;
import Barn.messages.ExceptionMessages;
import Barn.tasks.Deadline;
import Barn.tasks.Event;
import Barn.tasks.Todo;

/**
 * The Parser class processes the command given by the user
 * and returns a Command class object
 */
public class Parser {

    private static final int INDEX_OFFSET = 1; // offset for todo list which has 1-based index

    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        LIST,
        BYE
    }

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<description>.+)");

    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)"
            + " /by (?<by>.+)");

    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)"
            + " /from (?<from>[^/]+)"
            + " /to (?<to>.+)");

    public static final Pattern MARK_UNMARK_DELETE_ARGS_FORMAT = Pattern.compile("\\s*(?<index>\\d+)\\s*");

    public static final Pattern FIND_ARGS_FORMAT = Pattern.compile("(?<keyword>.+)");

    /**
     * Processes user command and returns a Command type object.
     * 
     * @param fullCommand Full string given by the user
     * @return Command type object representing the user command
     * @throws InvalidCommandException If userInput is of an invalid format
     */
    public static Command parse(String userInput) throws InvalidCommandException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_EMPTY_COMMAND);
        }

        String commandWord = matcher.group("commandWord").toUpperCase();
        String arguments = matcher.group("arguments");

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        switch (commandType) {
            case TODO:
                return parseTodo(arguments);

            case EVENT:
                return parseEvent(arguments);

            case DEADLINE:
                return parseDeadline(arguments);

            case MARK:
                return parseMark(arguments);

            case UNMARK:
                return parseUnmark(arguments);

            case DELETE:
                return parseDelete(arguments);

            case FIND:
                return parseFind(arguments);

            case LIST:
                return new ListCommand();

            case BYE:
                return new ExitCommand();

            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Parses the user input and creates an AddCommand with a Todo task
     * 
     * @param arguments String containing task description
     * @return AddCommand with a Todo task
     * @throws InvalidCommandException If description is empty
     */
    private static Command parseTodo(String arguments) throws InvalidCommandException {
        Matcher matcher = TODO_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_TODO);
        }
        String description = matcher.group("description");
        return new AddCommand(new Todo(description));
    }

    /**
     * Parses the user input and creates an AddCommand with a Deadline task
     * 
     * @param arguments String containing description and deadline date (indicated
     *                  by "/by" string)
     * @return AddCommand with a Deadline task
     * @throws InvalidCommandException If description is empty or arguments does not
     *                                 contain "/by" string
     */
    private static Command parseDeadline(String arguments) throws InvalidCommandException {
        Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_DEADLINE);
        }
        String description = matcher.group("description");
        String by = matcher.group("by");
        return new AddCommand(new Deadline(description, by));
    }

    /**
     * Parses the user input and creates an AddCommand with an Event task
     * 
     * @param arguments String containing description, and time of event (indicated
     *                  by "/from" and "/to" strings)
     * @return AddCommand with an Event task
     * @throws InvalidCommandException If description is empty or arguments does not
     *                                 contain "/from" or "/to" strings
     */
    private static Command parseEvent(String arguments) throws InvalidCommandException {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_EVENT);
        }
        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        return new AddCommand(new Event(description, from, to));
    }

    /**
     * Parses the user input and creates a MarkCommand
     * 
     * @param arguments String containing index of task to be marked as done
     * @return MarkCommand with the given index
     * @throws InvalidCommandException If index is not given or not an Integer
     */
    private static Command parseMark(String arguments) throws InvalidCommandException {
        Matcher matcher = MARK_UNMARK_DELETE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_MARK);
        }
        int index;
        try {
            index = Integer.parseInt(matcher.group("index")) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_INDEX);
        }
        return new MarkCommand(index);
    }

    /**
     * Parses the user input and creates an UnmarkCommand
     * 
     * @param arguments String containing index of task to be marked as not done
     * @return UnmarkCommand with the given index
     * @throws InvalidCommandException If index is not given or not an Integer
     */
    private static Command parseUnmark(String arguments) throws InvalidCommandException {
        Matcher matcher = MARK_UNMARK_DELETE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_UNMARK);
        }
        int index;
        try {
            index = Integer.parseInt(matcher.group("index")) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_INDEX);
        }
        return new UnmarkCommand(index);
    }

    /**
     * Parses the user input and creates a DeleteCommand
     * 
     * @param arguments String containing index of task to be deleted
     * @return DeleteCommand with the given index
     * @throws InvalidCommandException If index is not given or not an Integer
     */
    private static Command parseDelete(String arguments) throws InvalidCommandException {
        Matcher matcher = MARK_UNMARK_DELETE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_DELETE);
        }
        int index;
        try {
            index = Integer.parseInt(matcher.group("index")) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_INDEX);
        }
        return new DeleteCommand(index);
    }

    /**
     * Parses the user input and creates a FindCommand
     * 
     * @param arguments String containing keyword to be searched
     * @return FindCommand with the given keyword
     * @throws InvalidCommandException If keyword is empty
     */
    private static Command parseFind(String arguments) throws InvalidCommandException {
        Matcher matcher = FIND_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException(ExceptionMessages.EXCEPTION_MESSSAGE_FIND);
        }
        String keyword = matcher.group("keyword");
        return new FindCommand(keyword);
    }
}
