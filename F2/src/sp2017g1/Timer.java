package sp2017g1;



import java.util.logging.Level;
import java.util.logging.Logger;


public class Timer extends Thread {

   
    private int i;
    
    public void addTime() {
        i += 25;
    }
    
    public int getTime() {
        return i;
    }
    
    public void setTime(int i) {
        this.i = i;
    }
       
        @Override
    public void run() {
        try {
            for(i = 600; i>=0;i--){
                
                
                Thread.sleep(1000);
            }
            //call quit
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
