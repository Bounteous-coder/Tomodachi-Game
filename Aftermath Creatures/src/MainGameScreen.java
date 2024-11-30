import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainGameScreen extends Screen {
    private JLabel curTime;
    private JLabel petName;
    private JLabel money;
    private JLabel score;
    private JLabel health;
    private JLabel sleep;
    private JLabel fullness;
    private JLabel happiness;
    private Timer updateTimer; // Timer for updating the coin display

    public MainGameScreen() {
        // Set layout, setup subpanels
        this.panel.setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(header, BorderLayout.NORTH);
        JPanel sidebar = new JPanel();
        sidebar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setVertical(sidebar);
        panel.add(sidebar, BorderLayout.WEST);
        JPanel footer = new JPanel();
        footer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(footer, BorderLayout.SOUTH);
        // Pet image goes in BorderLayout.CENTER

        // Create elements
        curTime = new JLabel("17:25");
        petName = new JLabel("Pet Name");
        money = new JLabel("Coins: " + Coins.getCoins()); // Display initial coin count
        score = new JLabel("Score: 0");
        setH2(curTime);
        setH2(petName);
        setH2(money);
        setH2(score);
        health = new JLabel("Health: 100/100");
        sleep = new JLabel("Sleep: 100/100");
        fullness = new JLabel("Fullness: 100/100");
        happiness = new JLabel("Happiness: 100/100");
        setH2(health);
        setH2(sleep);
        setH2(fullness);
        setH2(happiness);

        // Footer icons go here
        JButton rest = new JButton("Rest"); // Placeholders
        JButton inventory = new JButton("Inventory");
        JButton doctor = new JButton("Doctor");
        JButton market = new JButton("Market");
        JButton minigames = new JButton("Minigames");
        JButton menu = new JButton("Settings Menu");

        // Add functionality to buttons
        rest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                main.pet.goToBed();
            }
        });
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("6");
            }
        });
        doctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                main.pet.takeToVet();
            }
        });
        market.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("7");
            }
        });
        minigames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                // Add minigames screen swap here
            }
        });
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("3");
            }
        });

        // Add elements to subpanels
        header.add(curTime);
        header.add(petName);
        header.add(money);
        header.add(score);
        sidebar.add(health);
        sidebar.add(sleep);
        sidebar.add(fullness);
        sidebar.add(happiness);
        // Footer icons go here
        footer.add(rest);
        footer.add(inventory);
        footer.add(doctor);
        footer.add(market);
        footer.add(minigames);
        footer.add(menu);

        // Start updating the money label and other dynamic elements
        startUpdatingUI();
    }

    private void startUpdatingUI() {
        updateTimer = new Timer(true); // Daemon thread
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // Update coins
                    money.setText("Coins: " + Coins.getCoins());
                    // Update time
                    updateTime();
                    // Update other stats if necessary
                    updateHealth();
                    updateSleep();
                    updateFullness();
                    updateHappiness();
                });
            }
        }, 0, 1000); // Update every second
    }

    private void updateTime() {
        Calendar currentTime = Calendar.getInstance();
        String timeText = String.format("%02d:%02d",
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE));
        curTime.setText(timeText);
    }

    // Update pet stats
    private void updateHealth() {
        health.setText("Health: " + main.pet.getHealth() + "/100");
    }

    private void updateSleep() {
        sleep.setText("Sleep: " + main.pet.getSleep() + "/100");
    }

    private void updateFullness() {
        fullness.setText("Fullness: " + main.pet.getFullness() + "/100");
    }

    private void updateHappiness() {
        happiness.setText("Happiness: " + main.pet.getHappiness() + "/100");
    }
}
