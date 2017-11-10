/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import java.io.*;
import java.util.*;
import originalFiles.*;

/**
 *
 * @author Student
 */
public class SP2017G1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Which language? (DA/EN)");
        langSelect();

        Game game = new Game();
        game.play();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".//saves/file.txt"));

            writer.write("HEJ MED DIG");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String langSelect() {
        String lang;
        while (true) {
            Scanner input = new Scanner(System.in);
            lang = input.next();
            if (lang.equalsIgnoreCase("en")) {
                System.out.println("LOADING ENGLISH");
//            Load english words.
                break;
            } else if (lang.equalsIgnoreCase("da")) {
                System.out.println("INDLÆSER DANSK");
//            Load danish words.
                break;
            } else {
                System.out.println("Not accepted lang. Try again.");
            }
        }
        return lang;
    }

}
