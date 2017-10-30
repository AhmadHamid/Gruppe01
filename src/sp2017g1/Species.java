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
    HUMAN("human"), DOG("dog"), CAT("cat");
    
    private String speciesString;
    
    private Species(String speciesString) {
        this.speciesString = speciesString;
    }

    public String toString() {
        return speciesString;
    }
}