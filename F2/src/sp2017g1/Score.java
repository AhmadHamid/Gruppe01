/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import originalFiles.*;
import language.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikol
 */
public class Score {
    
    private Game game;
    
    public Score(Game game) {
        this.game = game;
    }
    
    public Score(int totalScore) {
        // Logic for calculating the end score based on the total points gained from the three types (item, time, and efficiency) of points.
        /*
        totalPoints = totalItemPoints + totalTimePoints + totalEfficiencyPoints;
        */
    }

    public void Save() {
        
        String scoreString = game.scoreString;
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"));
            
            writer.write(scoreString);
            
            writer.close();
        } catch (NullPointerException e) {
            System.out.println("What interaction?");
        } catch (IOException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public String Load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"));
            String loadData = reader.readLine();
            
            reader.close();
            return loadData;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
