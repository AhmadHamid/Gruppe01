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
    key("key"), hammer("hammer"), nails("nails"), axe("axe"), shovel("shovel"), lumber("lumber"), ladder("ladder"), unknown("unknown"), wood("wood"), STOLENITEM("stolenItem");
    
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
