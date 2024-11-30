import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import com.fasterxml.jackson.databind.*;

public class MainMenuScreen extends Screen {
    static ObjectMapper objectMapper = new ObjectMapper();
    static File parentalControlsDataFile = new File("parentalControls.json");
    static ParentalControls parentalControls;

    public MainMenuScreen() {
        // Read data from parentalControls.json
        try {
            parentalControls = objectMapper.readValue(parentalControlsDataFile, ParentalControls.class);
        } catch (Exception e) {
            // Handle error
            e.printStackTrace();
        }

        // Set up panel
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.panel.setBorder(BorderFactory.createLineBorder(Color.blue));

        // Ensure the coin timer starts when the game launches
        Coins.startCoinTimer();

        // Create title and buttons
        JLabel title = new JLabel("Aftermath Creatures");
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton tutorialButton = new JButton("Tutorial");
        JButton settingsButton = new JButton("Settings");
        JButton exitGameButton = new JButton("Exit Game");
        JLabel credits = new JLabel("Developed by: Ivory Huo, Numan Salahuddin, Terry Shui, Harshi Kamboj (Group 43) Fall 2024, CS2212, Western University");

        // Add button events
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If blocked by parental controls screentime, show a popup
                if (!screentimeValid()) {
                    JLabel message = new JLabel("You cannot play the game at this time.");
                    JOptionPane.showMessageDialog(newGameButton, message,
                            "Screentime Restrictions", JOptionPane.WARNING_MESSAGE);
                } else {
                    SoundManager.play("button_sound.wav");
                    ScreenManager.swapView("1");
                }
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If blocked by parental controls screentime, show a popup
                if (!screentimeValid()) {
                    JLabel message = new JLabel("You cannot play the game at this time.");
                    JOptionPane.showMessageDialog(loadGameButton, message,
                            "Screentime Restrictions", JOptionPane.WARNING_MESSAGE);
                } else {
                    SoundManager.play("button_sound.wav");
                    ScreenManager.swapView("2");
                }
            }
        });

        tutorialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("4");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("3");
            }
        });

        exitGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                System.exit(0);
            }
        });

        // Adjust position and size of elements
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 50));
        newGameButton.setPreferredSize(new Dimension(50, 250));
        newGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loadGameButton.setPreferredSize(new Dimension(50, 250));
        loadGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        tutorialButton.setPreferredSize(new Dimension(50, 250));
        tutorialButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        settingsButton.setPreferredSize(new Dimension(50, 250));
        settingsButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exitGameButton.setPreferredSize(new Dimension(50, 250));
        exitGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        credits.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Add components to the panel
        this.panel.add(title);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(newGameButton);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(loadGameButton);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(tutorialButton);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(settingsButton);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(exitGameButton);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(credits);
    }

    private boolean screentimeValid() {
        // Read data from parentalControls.json (check for updates)
        ParentalControls p = parentalControls;
        try {
            p = objectMapper.readValue(parentalControlsDataFile, ParentalControls.class);
        } catch (Exception e) {
            // Handle error
            e.printStackTrace();
        }

        // Get current time
        Calendar now = Calendar.getInstance();

        // Edge case: when the start time is after the end time, the screentime will be discontinuous
        // Example: Start 21:00, End 3:00, Time1 17:00 is invalid, Time2 1:30 is valid
        boolean isRev = false;
        if (isBefore(p.getEndHr(), p.getEndMin(), p.getStartHr(), p.getStartMin()))
            isRev = true;

        // Check if current time is before the start time
        if (isBefore(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), p.getStartHr(), p.getStartMin())) {
            // If current time is also before the end time, it falls in the range
            if (isBefore(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), p.getEndHr(), p.getEndMin()) && isRev) {
                return true;
            }
            // Otherwise return false
            return false;
        }
        // Check if current time is after the end time
        if (isBefore(p.getEndHr(), p.getEndMin(), now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE))) {
            // If current time is also after the start time, it falls in the range
            if (isBefore(p.getStartHr(), p.getStartMin(), now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE)) && isRev) {
                return true;
            }
            // Otherwise return false
            return false;
        }
        // Otherwise return true
        return true;
    }

    /*
     * Helper method for comparing times
     * 
     * Takes 2 time values, each broken up into 2 ints representing hour and minute
     * 
     * @param h1 Hour value of first time
     * @param m1 Minute value of first time
     * @param h2 Hour value of second time
     * @param m2 Minute value of second time
     * @return true if first time is before second time, false if not
     */
    public boolean isBefore(int h1, int m1, int h2, int m2) {
        if (h1 < h2 || (h1 == h2 && m1 < m2))
            return true;
        else
            return false;
    }
}
