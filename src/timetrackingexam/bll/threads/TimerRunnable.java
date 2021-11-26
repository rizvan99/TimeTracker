
package timetrackingexam.bll.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 * This is the class responsible for counting the time
 * @author math2
 */
public class TimerRunnable implements Runnable{

    private final int DELAY = 1;    
    private TextField tSec;
    private TextField tMin;
    private TextField tHours
;    
    private boolean active;
    private ExecutorService executor;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;

    public TimerRunnable(TextField lSec, TextField lMin, TextField lHour) {
        this.tSec = lSec;
        this.tMin = lMin;
        this.tHours = lHour;
    }
    
    /**
     * Counts the seconds, minutes, and hours gone by since the thread was started
     */
    @Override
    public void run() {
   
        while (active) {
            Platform.runLater(() -> {
                seconds++;
                
                tSec.setEditable(false);
                tMin.setEditable(false);
                tHours.setEditable(false);
                
                tSec.setText(seconds+"");
                tMin.setText(minutes+"");
                tHours.setText(hours+"");
                });
            
                try {
                    TimeUnit.SECONDS.sleep(DELAY);
                } 
                catch (InterruptedException ex) {
                }

                if (seconds >= 59) {
                    seconds = -1;
                    minutes++;
                }

                if (minutes >= 59) {
                    seconds = -1;
                    minutes = -1;
                    hours++;
                }
        }
    }
    
    public boolean isActive(){
        return active;
    }

    
    
    /**
     * submits the thread to be executed
     */
    public void start() {
        
        if(true){
            convertLabelsToInt();
        }
        
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this);
        active = true;
    }
    
    /**
     * shuts down the executor and resets the labels
     */
    public void stop(){
        executor.shutdownNow();
        active = false;
        
        tSec.setEditable(true);
        tMin.setEditable(true);
        tHours.setEditable(true);
        
        tSec.setText("" +seconds);
        tMin.setText("" +minutes);
        tHours.setText("" +hours);
        
    }
    
    private void convertLabelsToInt(){
            String sec = tSec.getText();
            sec = sec.replaceAll("[^0-9\\s+]", "");
            seconds = Integer.parseInt(sec.trim());
            
            String min = tMin.getText();
            min = min.replaceAll("[^0-9\\s+]", "");
            minutes = Integer.parseInt(min.trim());
            
            String hour = tHours.getText();
            hour = hour.replaceAll("[^0-9\\s+]", "");
            hours = Integer.parseInt(hour.trim());
    }

    
    
    
    
    
    
}
