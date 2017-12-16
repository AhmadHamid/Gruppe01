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
     * @param item ladder item
     * @param inv player inventory
     */
    public void getItemLadder(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.LADDER, item);
    }
    
    /**
     * 
     * @param item lumber item
     * @param inv player inventory
     */
    public void getItemLumber(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.LUMBER, item);
    }
    
    /**
     * 
     * @param command 
     * @param iNails nails item
     * @param iHammer hammer item
     * @param iWood wood item
     * @param iLumber lumber item
     * @param iLadder ladder item
     * @param inv player inventory
     * @return treestump dialog
     */
    public String interactExtendedStump(Command command, Item iNails, Item iHammer, Item iWood, Item iLumber, Item iLadder, HashMap<ItemEnum, Item> inv) {
        
        // Dialogue tree for creating lumber.
        if(interactCount == 0) {
            if(!Game.getInventory().containsKey(ItemEnum.AXE) || !Game.getInventory().containsKey(ItemEnum.WOOD)) {
                return "To refine wood into lumber, you need the following items: " + "\n" + Game.axe.getItemName() + "\t" + Game.wood.getItemName();
            }
            
            else if (Game.getInventory().containsKey(ItemEnum.AXE) && Game.getInventory().containsKey(ItemEnum.WOOD)) {
                getItemLumber(iLumber, inv);
                Game.getInventory().remove(ItemEnum.WOOD);
                Game.getInventory().remove(ItemEnum.AXE);
                interactCount = 1;
                return "You have refined wood with the axe and created lumber." + "\n" + "Lumber has been added to your inventory.";
            }
            return null;
        }
        
        // Dialogue tree for creating ladder.
        else if (interactCount == 1) {
            if(!Game.getInventory().containsKey(ItemEnum.LUMBER) || !Game.getInventory().containsKey(ItemEnum.NAILS) || !Game.getInventory().containsKey(ItemEnum.HAMMER)) {
                return "To assemble a ladder, you need the following items: " + "\n" + Game.hammer.getItemName() + "\t" + Game.nails.getItemName() + "\t" + Game.lumber.getItemName();
            }
            
            else if(Game.getInventory().containsKey(ItemEnum.LUMBER) && Game.getInventory().containsKey(ItemEnum.NAILS) && Game.getInventory().containsKey(ItemEnum.HAMMER)) {
                getItemLadder(iLadder, inv);
                Game.getInventory().remove(ItemEnum.LUMBER);
                Game.getInventory().remove(ItemEnum.NAILS);
                Game.getInventory().remove(ItemEnum.HAMMER);
                return "You have used the hammer on the lumber and nails to assemble a ladder." + "\n" + "Ladder has been added to your inventory.";
            }
            return null;
        }
        return null;
    }
}
