package sp2017g1;



import java.util.logging.Level;
import java.util.logging.Logger;


public class Timer1 extends Thread {

    private long startTime = System.currentTimeMillis();
    private long elapsedTime;


    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Timer1 te = new Timer1();
        te.start();
        for(int i = 600; i>=0;i--){
            System.out.println("Seconds passed: " + te.getElapsedTime()/1000);
            Thread.sleep(1001);
        }
        te.interrupt();
    }
    
        public long getElapsedTime() {
        return elapsedTime;
    }
        
        @Override
    public void run() {
        try {
            while (true) {
                elapsedTime = System.currentTimeMillis() - startTime;
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
