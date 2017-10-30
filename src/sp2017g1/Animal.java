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
public class Animal extends NPC{
    private boolean flying;
    private boolean swimming;
    private boolean predator;
    
    public Animal(Species species){
        this.species = species;
        this.swimming = true;
        this.flying = false;
        this.predator = false;
    }
    
    public String speciesToString() {
        return species.toString();
    }
    /*
    public Animal(String name, String species, boolean predator) {
        this.name = name;
        this.species = species;
        this.predator = predator;
    }
    
    public Animal(String name, String species, boolean predator, boolean canFly) {
        this.name = name;
        this.species = species;
        this.predator = predator;
        this.flying = canFly;
    }
    
    public Animal(String name, String species, boolean predator, boolean canSwim, int i) {
        this.name = name;
        this.species = species;
        this.predator = predator;
        this.swimming = canSwim;
    }
    */

//    public String getSpecies() {
//        return species;
//    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isSwimming() {
        return swimming;
    }

    public boolean isPredator() {
        return predator;
    }

    @Override
    public void interact() {
        super.interact(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
