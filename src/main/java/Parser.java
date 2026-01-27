public class Parser {

    private static String getFirstWord(String fullCommand) {
        String firstWord = fullCommand.split(" ")[0];
        return firstWord;
    }

    private static int getIndex(String fullCommand) {
        String[] splitString = fullCommand.split(" ");
        int index = Integer.parseInt(splitString[1]) - 1;
        return index;
    }

    private static String getTodoInfo(String fullCommand) throws EmptyDescriptionException{
        if (fullCommand.split(" ").length <= 1) {
            throw new EmptyDescriptionException();
        }
        String description = fullCommand.split(" ", 2)[1];
        return description;
    }

    private static String[] getDeadlineInfo(String fullCommand) {
        String descriptionAndBy = fullCommand.split(" ", 2)[1];
        String description = descriptionAndBy.split(" /by ")[0];
        String by = descriptionAndBy.split(" /by ")[1];
        String[] info = {description, by};
        return info;
    }

    private static String[] getEventInfo(String fullCommand) {
        String descriptionAndFromTo = fullCommand.split(" ", 2)[1];
        String description = descriptionAndFromTo.split(" /from | /to ")[0];
        String from = descriptionAndFromTo.split(" /from | /to ")[1];
        String to = descriptionAndFromTo.split(" /from | /to ")[2];
        String[] info = {description, from, to};
        return info;
    }

    public static Command parse(String fullCommand) throws InvalidCommandException {
        try {
            String firstWord = getFirstWord(fullCommand);
            switch(firstWord) {
                case "todo": {
                    String description = getTodoInfo(fullCommand);
                    Todo task = new Todo(description);
                    return new AddCommand(task);
                }
    
                case "deadline": {
                    String[] deadlineInfo = getDeadlineInfo(fullCommand);
                    String description = deadlineInfo[0];
                    String by = deadlineInfo[1];
                    Deadline task = new Deadline(description, by);
                    return new AddCommand(task);
                }
    
                case "event": {
                    String[] eventInfo = getEventInfo(fullCommand);
                    String description = eventInfo[0];
                    String from = eventInfo[1];
                    String to = eventInfo[2];
                    Event task = new Event(description, from, to);
                    return new AddCommand(task);
                }
    
                case "mark": {
                    int index = getIndex(fullCommand);
                    return new MarkCommand(index);
                }
    
                case "unmark": {
                    int index = getIndex(fullCommand);
                    return new UnmarkCommand(index);
                }
    
                case "delete": {
                    int index = getIndex(fullCommand);
                    return new DeleteCommand(index);
                }
    
                case "list": {
                    return new ShowTasksCommand();
                }
                    
                case "bye": {
                    return new ExitCommand();
                }
    
                default:
                    throw new InvalidCommandException();
            }
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

}
