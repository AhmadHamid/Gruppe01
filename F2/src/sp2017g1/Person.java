/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;
import java.util.*;
import language.WordList;
import originalFiles.*;
/**
 *
 * @author Student
 */
public class Person extends NPC {
    private int interactCount = 0;
    
    /**
     * Constructor for Person class
     * @param room location of the person
     */
    public Person(Room room){
        this.currentRoom = room;
        this.species = Species.HUMAN;
    }

    /**
     * 
     * @param command 
     */
    @Override
    public void interact(Command command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Neighbour interaction
     * @param command 
     * @param k key item
     * @param h hammer item
     * @param inv players inventory
     * @return neighbour dialog
     */
    public String interactExtended(Command command, Item k, Item h, HashMap<ItemEnum, Item> inv) {
        if (interactCount == 0) {
            interactCount++;
            Game.setProgress(3);
            return "WELCOME!\n" + WordList.NEIGHBOUR_QUEST;
            
        } else if (interactCount == 1) {
            if (getItemH(h, inv)) {
                interactCount++;
                return ItemEnum.hammer.toString() + " is added to the inventory"; 
            } else {
                return "Inventory full";
            }
        } else if (interactCount == 2) {
            if (!Game.getInventory().containsKey(ItemEnum.shovel)) {
                return "You have the hammer"  + "\n" + "Go find the shovel!";
            } else {
                getItemK(k, inv);
                Game.getInventory().remove(ItemEnum.shovel);
                return "Here is the key!" + "\n" + ItemEnum.key.toString() +  " is added to the inventory";
            }
        } 
        return null;
    }
    
    /**
     * 
     * @param item hammer item
     * @param inv players inventory
     * @return 
     */
    public boolean getItemH(Item item, HashMap<ItemEnum, Item> inv) {
        if (inv.size() < 3) {
            inv.put(ItemEnum.hammer, item);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @param item key item
     * @param inv player inventory
     */
    public void getItemK(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.key, item);
    }

    /**
     * 
     * @param string message
     * @return intro message string
     */
    @Override
    public String introMessage(String string) {
        return super.introMessage(string); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @return number of times interacted with the person
     */
    public int getInteractCount() {
        return interactCount;
    }
    
    /**
     * 
     * @param interactCount number of times interacted with the person
     */
    public void setInteractCount(int interactCount) {
        this.interactCount = interactCount;
    }
    
}
