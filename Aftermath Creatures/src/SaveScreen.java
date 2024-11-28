import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SaveScreen extends Screen {

    public SaveScreen() {
        // Create SaveSlot objects
        SaveSlot saveSlot1 = new SaveSlot(1);
        SaveSlot saveSlot2 = new SaveSlot(2);
        SaveSlot saveSlot3 = new SaveSlot(3);

        // Add functionality to each SaveSlot
        addSaveLoadFunctionality(saveSlot1, 1);
        addSaveLoadFunctionality(saveSlot2, 2);
        addSaveLoadFunctionality(saveSlot3, 3);

        // Place SaveSlot panels onto SaveScreen panel
        setVertical(this.panel);
        this.panel.add(saveSlot1.panel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(saveSlot2.panel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(saveSlot3.panel);

        // Add navigation buttons for Main Menu and Main Game
        JButton mainMenuButton = new JButton("Main Menu");
        JButton mainGameButton = new JButton("Main Game");

        mainMenuButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        mainGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManager.swapView("0"); // Swap to Main Menu
            }
        });



        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(mainMenuButton);
    }

    // Method to add save/load functionality to SaveSlot
    private void addSaveLoadFunctionality(SaveSlot saveSlot, int slot) {
        saveSlot.panel.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {
                // Prompt user with Save/Load options for this slot
                int choice = JOptionPane.showOptionDialog(null,
                        "What would you like to do with Slot " + slot + "?",
                        "Save/Load Game",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Save", "Load", "Cancel"},
                        "Save");

                switch (choice) {
                    case JOptionPane.YES_OPTION: // Save game
                        SaveManager.saveGame(slot, "Game data for slot " + slot);
                        JOptionPane.showMessageDialog(null, "Game saved in slot " + slot);
                        break;
                    case JOptionPane.NO_OPTION: // Load game
                        String loadedData = SaveManager.loadGame(slot);
                        if (loadedData != null) {
                            JOptionPane.showMessageDialog(null, "Game data loaded:\n" + loadedData);
                            ScreenManager.swapView("mainGame"); // Switch to Main Game
                        } else {
                            JOptionPane.showMessageDialog(null, "No saved data found in slot " + slot);
                        }
                        break;
                    default: // Cancel
                        break;
                }
            }
        });
    }
}
