import javax.swing.*;
import java.awt.*;

public class Inventory {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Aftermath Creatures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Top panel for header information
        JPanel topPanel = new JPanel(new GridLayout(2, 5));
        topPanel.add(new JLabel("Time Elapsed", SwingConstants.CENTER));
        topPanel.add(new JLabel("Pet's Name 😊", SwingConstants.CENTER));
        topPanel.add(new JLabel("$0", SwingConstants.CENTER));
        topPanel.add(new JLabel("", SwingConstants.CENTER));
        topPanel.add(new JLabel("0", SwingConstants.CENTER));
        topPanel.add(new JLabel("", SwingConstants.CENTER));

        // Main panel for inventory
        JPanel inventoryPanel = new JPanel(new GridLayout(3, 3));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Dark border for the grid
        
        for (int i = 0; i < 9; i++) {
            JPanel cellPanel = new JPanel();
            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for each cell

            // Add labels for specific cells
            switch (i) {
                case 0:
                    cellPanel.add(new JLabel("Food", SwingConstants.CENTER));
                    break;
                case 3:
                    cellPanel.add(new JLabel("Gift", SwingConstants.CENTER));
                    break;
                case 6:
                    cellPanel.add(new JLabel("Meds", SwingConstants.CENTER));
                    break;
                default:
                    if (i < 6) {
                        cellPanel.add(new JLabel("Item " + (i + 1), SwingConstants.CENTER));
                    }
                    break;
            }

            inventoryPanel.add(cellPanel);
        }
        // Add labels for each inventory slot
        // inventoryPanel.add(createInventoryLabel("Food", "path/to/food_icon.png")); // Replace with actual paths
        //inventoryPanel.add(new JLabel("Item 1")); // Empty slot
        //inventoryPanel.add(new JLabel("Item 2")); // Empty slot
        //inventoryPanel.add(createInventoryLabel("Gift", "path/to/gift_icon.png")); // Replace with actual paths
        //inventoryPanel.add(new JLabel("Item 3")); // Empty slot
        //inventoryPanel.add(new JLabel("Item 4")); // Empty slot
        //inventoryPanel.add(createInventoryLabel("Meds", "path/to/med_icon.png")); // Replace with actual paths
        //inventoryPanel.add(new JLabel("Item 5")); // Empty slot
        //inventoryPanel.add(new JLabel("Item 6")); // Empty slot 

        
        // Bottom panel for footer (empty for now)
        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Dark border for the footer

        // Add components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(inventoryPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    // Helper method to create a JLabel with text and icon
    private static JLabel createInventoryLabel(String text, String iconPath) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setIcon(new ImageIcon(iconPath));
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        return label;
    }
}
