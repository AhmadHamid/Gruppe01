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
public class Animal {
    private String name;
    private String species;
    private boolean flying;
    private boolean swimming;
    private boolean rovdyr;
    
    public Animal(String name, String species, boolean rovdyr) {
        this.name = name;
        this.species = species;
        this.rovdyr = rovdyr;
    }
    
    public Animal(String name, String species, boolean rovdyr, boolean canFly) {
        this.name = name;
        this.species = species;
        this.rovdyr = rovdyr;
        this.flying = canFly;
    }
    
    public Animal(String name, String species, boolean rovdyr, boolean canSwim, int i) {
        this.name = name;
        this.species = species;
        this.rovdyr = rovdyr;
        this.swimming = canSwim;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isSwimming() {
        return swimming;
    }

    public boolean isRovdyr() {
        return rovdyr;
    }
    
    
}
