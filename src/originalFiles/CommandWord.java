package originalFiles;

import language.*;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    GO(WordList.GO), QUIT(WordList.QUIT), HELP(WordList.HELP), UNKNOWN(WordList.UNKNOWN), PICK(WordList.PICK), USE(WordList.USE), DROP(WordList.DROP), INVENTORY(WordList.INVENTORY);
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
