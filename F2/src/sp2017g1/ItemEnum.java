/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

/**
 *
 * @author Student
 */
public enum ItemEnum {
    KEY("key"), HAMMER("hammer"), NAILS("nails"), AXE("axe"), SHOVEL("shovel"), LUMBER("lumber"), LADDER("ladder"), UNKNOWN("unknown"), WOOD("wood"), STOLENITEM("stolenItem");
    
    private String itemString;

    /**
     * 
     * @param itemString name of an item
     */
    private ItemEnum(String itemString) {
        this.itemString = itemString;
    }
    
    /**
     * 
     * @return item name as string
     */
    public String toString() {
        return itemString;
    }
    
}
