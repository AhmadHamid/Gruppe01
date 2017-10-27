/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

/**
 *
 * @author Lasse
 */
public class Timer {
    static Thread countdown = new Thread();
    public static void countdownTimer() throws InterruptedException {
        for (int i = 60;i>=0;i--) {
            countdown.sleep(1000);
            System.out.println(i);
        
    }
}
}
