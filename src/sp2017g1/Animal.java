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
public class Animal {
    private String name;
    private String species;
    private boolean rovdyr;
    
    public Animal(String name, String species, boolean rovdyr) {
        this.name = name;
        this.species = species;
        this.rovdyr = rovdyr;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
    
}
