package sp2017g1;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import originalFiles.*;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Door 
        extends Room
{
    private String description;
    
    private boolean lock;
    private final boolean door;
    private String lockItem;
    
    public  Door(String description, String lockItem) 
    {
        super(description);
        this.lockItem = lockItem;
        lock = true;
        door = true;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
    
    public boolean getLock(){
        return lock;
    }
    
    public boolean getDoor(){
        return door;
    }
    
    public String getKey(){
        return lockItem;
    }
    
   
@Override
    public String getLongDescription()
    {
        return "You walk through " + description + ".\n";
    }

    
    
}

