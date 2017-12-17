package business;

import business.Room;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * 
 */
public class Door 
        extends Room
{
    private String description;
    
    private boolean lock;
    private final boolean door;
    private ItemEnum lockItem;
    private String name;
    
    /**
     * Constructor for Door
     * @param description doors description
     * @param lockItem item needed to unlock the door
     */
    public  Door(String description, ItemEnum lockItem) 
    {
        super(description, "");
        this.lockItem = lockItem;
        lock = true;
        door = true;
        this.name = null;
    }
    
    /**
     * Constructor for Door
     * @param description doors description
     * @param lockItem item needed to unlock the door
     * @param name name of the door
     */
    public  Door(String description, ItemEnum lockItem, String name) 
    {
        super(description, "");
        this.lockItem = lockItem;
        lock = true;
        door = true;
        this.name = name;
    }

    /**
     *
     * @param lock true to lock the door, false to unlock the door
     */
    public void setLock(boolean lock) {
        this.lock = lock;
    }
    
    /**
     * 
     * @return true if door is locked, otherwise false
     */
    public boolean getLock(){
        return lock;
    }
    
    /**
     * 
     * @return true if object is a door, otherwise false
     */
    public boolean getDoor(){
        return door;
    }
    
    /**
     * 
     * @return name of the door
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return item needed to unlock the door 
     */
    public String getKey(){
        return lockItem.toString();
    }
   
    /**
     * 
     * @return desciption
     */
    @Override
    public String getLongDescription()
    {
        return "You walk through " + description + ".\n";
    }

    
    
}

