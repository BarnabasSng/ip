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
import Barn.tasks.Deadline;
import Barn.tasks.Event;
import Barn.tasks.Todo;

/**
 * The Parser class processes the command given by the user
 * and returns a Command class object
 */
public class Parser {

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
     * Processes user command and returns a Command class object.
     * 
     * @param fullCommand Full string given by the user
     * @return Command class object representing the user command
     * @throws InvalidCommandException If userInput is of an invalid format
     */
    public static Command parse(String userInput) throws InvalidCommandException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
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

    private static Command parseTodo(String arguments) throws InvalidCommandException {
        Matcher matcher = TODO_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        String description = matcher.group("description");
        return new AddCommand(new Todo(description));
    }

    private static Command parseDeadline(String arguments) throws InvalidCommandException {
        Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        String description = matcher.group("description");
        String by = matcher.group("by");
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEvent(String arguments) throws InvalidCommandException {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        return new AddCommand(new Event(description, from, to));
    }

    private static Command parseMark(String arguments) throws InvalidCommandException {
        Matcher matcher = MARK_UNMARK_DELETE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        int index;
        try {
            index = Integer.parseInt(matcher.group("index")) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
        return new MarkCommand(index);
    }

    private static Command parseUnmark(String arguments) throws InvalidCommandException {
        Matcher matcher = MARK_UNMARK_DELETE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        int index;
        try {
            index = Integer.parseInt(matcher.group("index")) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
        return new UnmarkCommand(index);
    }

    private static Command parseDelete(String arguments) throws InvalidCommandException {
        Matcher matcher = MARK_UNMARK_DELETE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        int index;
        try {
            index = Integer.parseInt(matcher.group("index")) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
        return new DeleteCommand(index);
    }

    private static Command parseFind(String arguments) throws InvalidCommandException {
        Matcher matcher = FIND_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandException();
        }
        String keyword = matcher.group("keyword");
        return new FindCommand(keyword);
    }
}
