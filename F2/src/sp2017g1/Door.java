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
    private ItemEnum lockItem;
    private String name;
    
    public  Door(String description, ItemEnum lockItem) 
    {
        super(description, "");
        this.lockItem = lockItem;
        lock = true;
        door = true;
        this.name = null;
    }
    
    public  Door(String description, ItemEnum lockItem, String name) 
    {
        super(description, "");
        this.lockItem = lockItem;
        lock = true;
        door = true;
        this.name = name;
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
    
    public String getName() {
        return name;
    }
    
    public String getKey(){
        return lockItem.toString();
    }
   
@Override
    public String getLongDescription()
    {
        return "You walk through " + description + ".\n";
    }

    
    
}

