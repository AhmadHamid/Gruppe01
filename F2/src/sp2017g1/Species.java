/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import sp2017g1.*;

/**
 *
 * @author Kasper
 */
public enum Species {
    HUMAN("human"), DOG("dog"), INANIMATE("inanimate");
    
    private String speciesString;
    
    /**
     * 
     * @param speciesString NPC type
     */
    private Species(String speciesString) {
        this.speciesString = speciesString;
    }

    /**
     * 
     * @return NPC type as string 
     */
    public String toString() {
        return speciesString;
    }
}