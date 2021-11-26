
package timetrackingexam.bll.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class handles all the threads used by the program
 * It will probably have more threads to manage in the future
 * @author math2
 */
public class Scheduler implements Runnable {

    private final BlockingQueue<TimerRunnable> QUEUE = new LinkedBlockingQueue<>();

    private TimerRunnable currentTimer = null;
    private ExecutorService executor = null;

    
    @Override
    public void run() {
    }

    /**
     * Starts the current timer
     * @param timer the timer to be startet
     */
    public synchronized void startTimer(TimerRunnable timer) {
        if (executor == null || executor.isShutdown()) {
            executor = Executors.newSingleThreadExecutor();
            executor.submit(this);
        }

        if (currentTimer == null && QUEUE.isEmpty()) {
            currentTimer = timer;
            currentTimer.start();
        } else {
            try {
                QUEUE.put(currentTimer);
            } catch (InterruptedException iEx) {
                if(!timer.isActive()){
                    executor.shutdownNow();
                }
            }
        }
    }

    /**
     * pauses the Thread
     */
    public synchronized void pause() {
        currentTimer.stop();
    }

    /**
     * Clears the queue and stops the thread
     */
    public synchronized void stop() {
        QUEUE.forEach((runnable) -> {
            QUEUE.remove(runnable);
        });
            executor.shutdownNow();
            currentTimer.stop();
        
    }
}
