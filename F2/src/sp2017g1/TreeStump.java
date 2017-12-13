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

    public TreeStump(Room room) {
        this.currentRoom = room;
        this.species = Species.INANIMATE;
    }
    
    @Override
    public void interact(Command command) {
        System.out.println("Treestump interact.");
    }
    
    public void getItemLadder(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.ladder, item);
    }
    
    public void getItemLumber(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.lumber, item);
    }
    
    /*    public void getItemLumber(Item item, HashMap<ItemEnum, Item> inv) {
    inv.put(ItemEnum.lumber, item);
    }*/
    
    public String interactExtendedStump(Command command, Item iNails, Item iHammer, Item iWood, Item iLumber, Item iLadder, HashMap<ItemEnum, Item> inv) {
//        Scanner input = new Scanner(System.in);
//        
//        System.out.println("You see a tree stump where you can create a ladder or refine wood into lumber." + "\n" + "Type ladder to make a ladder or type lumber to make lumber.");
//        
//        String option = input.next();
        
        // Dialogue tree for creating lumber.
        if(interactCount == 0) {
            if(!Game.getInventory().containsKey(ItemEnum.axe) || !Game.getInventory().containsKey(ItemEnum.wood)) {
                System.out.println("To refine wood into lumber, you need the following items: " + "\n" + Game.axe.getItemName() + "\t" + Game.wood.getItemName());
                return "To refine wood into lumber, you need the following items: " + "\n" + Game.axe.getItemName() + "\t" + Game.wood.getItemName();
            }
            
            else if (Game.getInventory().containsKey(ItemEnum.axe) && Game.getInventory().containsKey(ItemEnum.wood)) {
                getItemLumber(iLumber, inv);
                Game.getInventory().remove(ItemEnum.wood);
                Game.getInventory().remove(ItemEnum.axe);
                System.out.println("You have refined wood with the axe and created lumber." + "\n" + "Lumber has been added to your inventory.");
                interactCount = 1;
                return "You have refined wood with the axe and created lumber." + "\n" + "Lumber has been added to your inventory.";
            }
            return null;
        }
        
        // Dialogue tree for creating ladder.
        else if (interactCount == 1) {
            if(!Game.getInventory().containsKey(ItemEnum.lumber) || !Game.getInventory().containsKey(ItemEnum.nails) || !Game.getInventory().containsKey(ItemEnum.hammer)) {
                System.out.println("To assemble a ladder, you need the following items: " + "\n" + Game.hammer.getItemName() + "\t" + Game.nails.getItemName() + "\t" + Game.lumber.getItemName());
                return "To assemble a ladder, you need the following items: " + "\n" + Game.hammer.getItemName() + "\t" + Game.nails.getItemName() + "\t" + Game.lumber.getItemName();
            }
            
            else if(Game.getInventory().containsKey(ItemEnum.lumber) && Game.getInventory().containsKey(ItemEnum.nails) && Game.getInventory().containsKey(ItemEnum.hammer)) {
                getItemLadder(iLadder, inv);
                Game.getInventory().remove(ItemEnum.lumber);
                Game.getInventory().remove(ItemEnum.nails);
                Game.getInventory().remove(ItemEnum.hammer);
                System.out.println("You have used the hammer on the lumber and nails to assemble a ladder." + "\n" + "Ladder has been added to your inventory.");
                return "You have used the hammer on the lumber and nails to assemble a ladder." + "\n" + "Ladder has been added to your inventory.";
            }
            return null;
        }
        
        
        /*        else if (!Game.getInventory().containsKey(ItemEnum.ladder) && Game.getInventory().containsKey(ItemEnum.wood)) {
        System.out.println("Here, you can refine wood for the ladder.");
        } else if (Game.getInventory().containsKey(ItemEnum.ladder)) {
        System.out.println("You have no further use of the tree stump.");
        }*/
        
        // The following code is functional. It creates the ladder item.
        /*        if(Game.getInventory().containsKey(ItemEnum.nails) && Game.getInventory().containsKey(ItemEnum.lumber) && Game.getInventory().containsKey(ItemEnum.hammer)) {
        getItemLadder(iLadder, inv);
        System.out.println(Game.nails.getItemName() + " and x " + "has been combined via TreeStump.java to " + Game.ladder.getItemName());
        Game.getInventory().remove(ItemEnum.nails);
        Game.getInventory().remove(ItemEnum.lumber);
        }*/
        return null;
    }
}
