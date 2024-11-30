import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Robot extends Pet{
    
	public Robot(String name) {
		this.name = name;
        this.health = MAX_STAT; // Start with full health
        this.fullness = 30; // Lower starting fullness level
        this.sleep = 30; // Lower starting sleep level
        this.happiness = 30; // Lower starting happiness level
        this.state = "Normal"; // Initial state of the pet
        startStatDecline(); // Start automatic stat decline once pet initialized 
	}

	private void startStatDecline() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() { // Schedule tasks at fixed rates of time 
            @Override
            public void run() {
                if (!state.equals("Dead")) {
                    // Gradual decrease in stats if pet is NOT dead 
                    fullness = Math.max(MIN_STAT, fullness - 3); // Decrease fullness by 3
                    sleep = Math.max(MIN_STAT, sleep - 2);       // Decrease sleep by 2
                    happiness = Math.max(MIN_STAT, happiness - 3); // Decrease happiness by 3

                    // Delegate state management to updateState
                    updateState();

                    // Output updated stats
                    System.out.println("Stats updated: Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness);
                }
            }
        }, 0, 30000); // Update stats every 30 seconds
    }
}
