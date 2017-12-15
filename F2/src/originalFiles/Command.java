package originalFiles;

/** 
 * @author  Gruppe 1 
 */
public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     * 
     * @param commandWord command type
     * @param secondWord controls what the command is used on
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     *
     * @return command type
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     *
     * @return what the command is used on
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     *
     * @param secondWord 
     */
    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }
    
    /**
     *
     * @return true if command is unknown, otherwise false
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     *
     * @return true if command has second word, otherwise false
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

