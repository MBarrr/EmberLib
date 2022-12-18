package mbarrr.github.emberlib.Util;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Creates a repeating BukkitRunnable task
 * which will self cancel after a set number of repeats
 * or if repeat() returns false
 */
public class RepeatingTask {

    /**
     * Creates a new RepeatingTask
     * @param instance JavaPlugin instance
     * @param delay Initial delay
     * @param period Delay between repeats
     * @param numRepeats Number of times to repeat task
     */
    public RepeatingTask(JavaPlugin instance, long delay, long period, int numRepeats){

        int i = 0;

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(numRepeats == i){
                    cancel();
                    return;
                }

                if(!repeat()){
                    cancel();
                }
            }
        };

        runnable.runTaskTimer(instance, delay, period);
    }

    /**
     * Method to override to provide functionality
     * new code will be run every repeat
     * @return False to cancel task, otherwise true
     */
    public boolean repeat(){
        return true;
    }
}
