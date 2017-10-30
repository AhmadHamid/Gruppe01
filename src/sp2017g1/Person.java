/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;
import java.util.*;
/**
 *
 * @author Student
 */
public class Person extends NPC {
    private HashMap<String, Item> items = new HashMap<>();
    private HashMap<String, Boolean> itemsLock = new HashMap<>();
    private HashMap<String, String> unlockItem = new HashMap<>();
    
    public Person(){
        this.species = Species.HUMAN;
    }
    
    public void addItem(String name, Item item){
        this.items.put(name, item);
        this.itemsLock.put(name, Boolean.FALSE);
    }
    
    public  void addItem(String name, Item item, Boolean lock, String lockItem){
        this.items.put(name, item);
        this.itemsLock.put(name, lock);
        this.unlockItem.put(name, lockItem);
    }
    
    private void unlock(String item){
        this.itemsLock.replace(item, Boolean.FALSE);
    }
    
    public Item getItem(String item){
        return this.items.get(item);
    }
    
}
