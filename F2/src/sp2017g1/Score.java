/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

/**
 *
 * @author nikol
 */
public class Score {
    public int totalScore = 0;
    public int totalItemScore = 0;
    public int totalTimeScore = 0;
    public int totalEfficiencyScore = 0;
    public final int ITEM_TIER1_POINT = 15;
    public final int ITEM_TIER2_POINT = 25;
    public final int ITEM_TIER3_POINT = 40;
    public boolean itemTier1;
    public boolean itemTier2;
    public boolean itemTier3;
    public boolean itemPicked = false;
    
    public Score(int totalScore) {
        // Logic for calculating the end score based on the total points gained from the three types (item, time, and efficiency) of points.
        /*
        totalPoints = totalItemPoints + totalTimePoints + totalEfficiencyPoints;
        */
    }
    
    public int ItemPoints() {
        // Logic for points given from obtained items.
        
        /* This solution, written in pseudo-code, requires all items to have a flag that can be set the first time it is in the player inventory.
           It may not count any one item more than once.
        
        if(itemPicked == itemTier1) {
            totalItemPoints = totalItemPoints + ITEM_TIER1_POINT;
        }
        
        else if(itemPicked == itemTier2) {
            totalItemPoints = totalItemPoints + ITEM_TIER2_POINT;
        }
        
        else if(itemPicked == itemTier3) {
            totalItemPoints = totalItemPoints + ITEM_TIER3_POINT;
        }
        */
        
        return totalItemScore;
    }
    
    public int TimePoints() {
        // Logic for points given from time spent finishing the game.
        // (1000 / time el. 1000 - time)
        
        return totalTimeScore;
    }
    
    public int EfficiencyPoints() {
        // Logic for points given from efficiency (shortest route from start to finish) in finishing the game.
        // (num / steps = p) el. 1000 - (20 * steps)
        
        return totalEfficiencyScore;
    }
}
