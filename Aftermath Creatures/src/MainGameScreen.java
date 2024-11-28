import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainGameScreen extends Screen {

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

        // Center layout (Pet-related functionality can go here)
        JLabel petLabel = new JLabel("Your Pet");
        petLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(petLabel, BorderLayout.CENTER);

        // Create elements
        JLabel curTime = new JLabel("17:25");
        JLabel petName = new JLabel("Pet Name");
        JLabel money = new JLabel("$0");
        JLabel score = new JLabel("Score: 0");
        setH2(curTime);
        setH2(petName);
        setH2(money);
        setH2(score);

        JLabel health = new JLabel("Health: 100/100");
        JLabel sleep = new JLabel("Sleep: 100/100");
        JLabel fullness = new JLabel("Fullness: 100/100");
        JLabel happiness = new JLabel("Happiness: 100/100");

        // Footer buttons
        JButton rest = new JButton("Rest");
        JButton inventory = new JButton("Inventory");
        JButton doctor = new JButton("Doctor");
        JButton market = new JButton("Market");
        JButton minigames = new JButton("Minigames");
        JButton settingsMenu = new JButton("Settings Menu");
        JButton saveGameButton = new JButton("Save Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton mainMenuButton = new JButton("Main Menu");

        // Add functionality to buttons
        rest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Placeholder action for rest
            }
        });
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.swapView("6"); // Inventory screen
            }
        });
        doctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Placeholder action for visiting doctor
            }
        });
        market.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Placeholder action for market
            }
        });
        minigames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Placeholder action for minigames
            }
        });
        settingsMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.swapView("3"); // Settings screen
            }
        });
        // Save game with slot selection
        saveGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] slots = {"1", "2", "3"};
                String slot = (String) JOptionPane.showInputDialog(null,
                        "Select a slot to save:",
                        "Save Game",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        slots,
                        slots[0]);

                if (slot != null) {
                    int slotNumber = Integer.parseInt(slot);
                    SaveManager.saveGame(slotNumber, "Game state for slot " + slotNumber);
                    JOptionPane.showMessageDialog(null, "Game saved in slot " + slotNumber);
                }
            }
        });

        // Load game with slot selection
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] slots = {"1", "2", "3"};
                String slot = (String) JOptionPane.showInputDialog(null,
                        "Select a slot to load:",
                        "Load Game",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        slots,
                        slots[0]);

                if (slot != null) {
                    int slotNumber = Integer.parseInt(slot);
                    String loadedData = SaveManager.loadGame(slotNumber);
                    if (loadedData != null) {
                        JOptionPane.showMessageDialog(null, "Game Loaded:\n" + loadedData);
                    } else {
                        JOptionPane.showMessageDialog(null, "No saved data found in slot " + slotNumber);
                    }
                }
            }
        });
        
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.swapView("0");  // Ensure "main" is the correct ID for Main Menu
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

        // Footer icons and navigation buttons
        footer.add(rest);
        footer.add(inventory);
        footer.add(doctor);
        footer.add(market);
        footer.add(minigames);
        footer.add(settingsMenu);
        footer.add(saveGameButton);
        footer.add(loadGameButton);
        footer.add(mainMenuButton);
    }
}
