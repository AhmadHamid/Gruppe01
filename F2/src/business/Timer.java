package business;



import java.util.logging.Level;
import java.util.logging.Logger;
import business.Game;


public class Timer extends Thread {
   
    private int i;
    private boolean gameOver = false;
    
    /**
     * Method used to add time to the timer
     */
    public void addTime() {
        i += 25;
    }
    
    /**
     * 
     * @return time left 
     */
    public int getTime() {
        return i;
    }
    
    /**
     * Used to change how much time that is left
     * @param i time left
     */
    public void setTime(int i) {
        this.i = i;
    }
       
    /**
     * Timer that is running for 10 minutes
     */
    @Override
    public void run() {
        try {
            /*for(i = 600; i>=0;i--){
                Thread.sleep(1000);
            }*/
            while (i>0) {
                Thread.sleep(1000);
                i--;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean gameOver() {
        if (getTime() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
