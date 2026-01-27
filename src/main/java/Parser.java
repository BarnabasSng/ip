public class Parser {
    protected String userString;

    public Parser(String userString){
        this.userString = userString;
    }

    public String getCommand() {
        String firstWord = this.userString.split(" ")[0];
        return firstWord;
    }

    public int getIndex() {
        String[] splitString = this.userString.split(" ");
        int index = Integer.parseInt(splitString[1]) - 1;
        return index;
    }

    public String getTodoInfo() throws EmptyDescriptionException{
        if (this.userString.split(" ").length <= 1) {
            throw new EmptyDescriptionException();
        }
        String description = this.userString.split(" ", 2)[1];
        return description;
    }

    public String[] getDeadlineInfo() {
        String descriptionAndBy = this.userString.split(" ", 2)[1];
        String description = descriptionAndBy.split(" /by ")[0];
        String by = descriptionAndBy.split(" /by ")[1];
        String[] info = {description, by};
        return info;
    }

    public String[] getEventInfo() {
        String descriptionAndFromTo = this.userString.split(" ", 2)[1];
        String description = descriptionAndFromTo.split(" /from | /to ")[0];
        String from = descriptionAndFromTo.split(" /from | /to ")[1];
        String to = descriptionAndFromTo.split(" /from | /to ")[2];
        String[] info = {description, from, to};
        return info;
    }

}
