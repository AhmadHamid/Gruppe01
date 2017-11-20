package originalFiles;

import language.*;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    GO(WordList.GO), QUIT(WordList.QUIT), HELP(WordList.HELP), UNKNOWN(WordList.UNKNOWN), PICK(WordList.PICK), USE(WordList.USE), DROP(WordList.DROP), INVENTORY(WordList.INVENTORY), UNLOCK(WordList.UNLOCK), /*COMBINE(WordList.COMBINE),*/ INTERACT(WordList.INTERACT), SAVE(WordList.SAVE), LOAD(WordList.LOAD);
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    CommandWord(CommandWord commandWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString()
    {
        return commandString;
    }
}
