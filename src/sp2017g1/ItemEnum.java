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
    key("key"), hammer("hammer"), nails("nails"), axe("axe"), shovel("shovel"), lumber("lumber"), block("block"), ladder("ladder"), test("test"), unknown("unknown"), wood("wood");
    
    private String itemString;

    private ItemEnum(String itemString) {
        this.itemString = itemString;
    }
    
    public String toString() {
        return itemString;
    }
    
}
