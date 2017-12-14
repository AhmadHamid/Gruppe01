/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import java.util.HashMap;
import java.util.Scanner;
import originalFiles.*;

/**
 *
 * @author Student
 */
public class TreeStump extends NPC {

    /**
     * Constructor for TreeStump
     * @param room location of the treestump
     */
    public TreeStump(Room room) {
        this.currentRoom = room;
        this.species = Species.INANIMATE;
    }
    
    /**
     * 
     * @param command 
     */
    @Override
    public void interact(Command command) {
        System.out.println("Treestump interact.");
    }
    
    /**
     * 
     * @param item
     * @param inv 
     */
    public void getItemLadder(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.ladder, item);
    }
    
    /**
     * 
     * @param item
     * @param inv 
     */
    public void getItemLumber(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.lumber, item);
    }
    
    /**
     * 
     * @param command
     * @param iNails
     * @param iHammer
     * @param iWood
     * @param iLumber
     * @param iLadder
     * @param inv
     * @return 
     */
    public String interactExtendedStump(Command command, Item iNails, Item iHammer, Item iWood, Item iLumber, Item iLadder, HashMap<ItemEnum, Item> inv) {
        
        // Dialogue tree for creating lumber.
        if(interactCount == 0) {
            if(!Game.getInventory().containsKey(ItemEnum.axe) || !Game.getInventory().containsKey(ItemEnum.wood)) {
                return "To refine wood into lumber, you need the following items: " + "\n" + Game.axe.getItemName() + "\t" + Game.wood.getItemName();
            }
            
            else if (Game.getInventory().containsKey(ItemEnum.axe) && Game.getInventory().containsKey(ItemEnum.wood)) {
                getItemLumber(iLumber, inv);
                Game.getInventory().remove(ItemEnum.wood);
                Game.getInventory().remove(ItemEnum.axe);
                interactCount = 1;
                return "You have refined wood with the axe and created lumber." + "\n" + "Lumber has been added to your inventory.";
            }
            return null;
        }
        
        // Dialogue tree for creating ladder.
        else if (interactCount == 1) {
            if(!Game.getInventory().containsKey(ItemEnum.lumber) || !Game.getInventory().containsKey(ItemEnum.nails) || !Game.getInventory().containsKey(ItemEnum.hammer)) {
                return "To assemble a ladder, you need the following items: " + "\n" + Game.hammer.getItemName() + "\t" + Game.nails.getItemName() + "\t" + Game.lumber.getItemName();
            }
            
            else if(Game.getInventory().containsKey(ItemEnum.lumber) && Game.getInventory().containsKey(ItemEnum.nails) && Game.getInventory().containsKey(ItemEnum.hammer)) {
                getItemLadder(iLadder, inv);
                Game.getInventory().remove(ItemEnum.lumber);
                Game.getInventory().remove(ItemEnum.nails);
                Game.getInventory().remove(ItemEnum.hammer);
                return "You have used the hammer on the lumber and nails to assemble a ladder." + "\n" + "Ladder has been added to your inventory.";
            }
            return null;
        }
        return null;
    }
}
