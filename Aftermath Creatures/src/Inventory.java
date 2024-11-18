import javax.swing.*;
import java.awt.*;

public class Inventory {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Aftermath Creatures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        // Top panel for header information
        JPanel topPanel = new JPanel(new GridLayout(1, 5));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a dark border to the header
        topPanel.setPreferredSize(new Dimension(600, 80)); // Make the header larger
        JLabel timeLabel = new JLabel("5:45 PM", SwingConstants.CENTER);
        JLabel petLabel = new JLabel("Pet's Name 😊", SwingConstants.CENTER);
        JLabel moneyLabel = new JLabel("$0", SwingConstants.CENTER);
        JLabel pointsLabel = new JLabel("Points 0", SwingConstants.CENTER);
        topPanel.add(timeLabel);
        topPanel.add(petLabel);
        topPanel.add(moneyLabel);
        topPanel.add(pointsLabel);

        // Main panel for inventory
        JPanel inventoryContainer = new JPanel(new GridBagLayout()); // Center the grid panel
        JPanel inventoryPanel = new JPanel(new GridLayout(3, 3));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Dark border for the grid
        inventoryPanel.setPreferredSize(new Dimension(750, 750)); // Set a fixed size for the grid panel

        // Add bordered panels for each cell to create a grid-like appearance
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
                    // Leave other cells empty for now
                    break;
            }

            inventoryPanel.add(cellPanel);
        }

        inventoryContainer.add(inventoryPanel); // Center the inventory panel in the container

        // Bottom panel for footer (empty for now)
        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Dark border for the footer
        footerPanel.setPreferredSize(new Dimension(600, 50)); // Set a height for the footer

        // Add components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(inventoryContainer, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }
}
