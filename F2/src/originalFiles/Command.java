package originalFiles;

/** 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     *
     * @param commandWord
     * @param secondWord
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     *
     * @return
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     *
     * @return
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
     * @return
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     *
     * @return
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

