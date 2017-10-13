/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import originalFiles.*;

/**
 *
 * @author Student
 */
public class WordList {
    Game game = new Game();
    
//    class CommandWord
    public static final String GO = "go";
    public static final String QUIT = "quit";
    public static final String HELP = "help";
    public static final String UNKNOWN = "?";
    public static final String PICK = "pick";
    public static final String USE = "use";
    public static final String DROP = "drop";
    public static final String INVENTORY = "inventory";
    public static final String UNLOCK = "unlock";
    
//    class Game.
    public static final String WELCOME = "Welcome to the World of Zuul!";
    public static final String DESCRIPTION = "World of Zuul is a new, incredibly boring adventure game.";
    public static final String GET_HELP = "Type '" + CommandWord.HELP + "' if you need help.";
    public static final String ITEMS_IN_ROOM = "These items is in the room: ";
    public static final String DONT_KNOW_WHAT_YOU_MEAN = "I don't know what you mean...";
    public static final String PRINT_HELP = "You are lost. You are alone. You wander\n"
                                            + "around at the university. \n"
                                            + "\n"
                                             + "Your command words are:";
    public static final String GO_WHERE = "Go where?";
}
