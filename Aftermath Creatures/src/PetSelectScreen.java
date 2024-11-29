import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class PetSelectScreen extends Screen{
	public PetSelectScreen() {
		//create elements
		JLabel title = new JLabel("Select Your Pet");
		setH1(title);
		JPanel petsPanel = new JPanel();
		JPanel zombiePanel = new JPanel();
		JLabel zombieTitle = new JLabel("Zombie");
		setH2(zombieTitle);
		JLabel zombieDesc = new JLabel("<html>A resilient pet that survives on minimal care<br> but gets moody without attention.</html>"); //Health = 100, Fullness = 100, Sleep = 100, Happiness = 50
		JPanel humanPanel = new JPanel();
		JLabel humanTitle = new JLabel("Human");
		setH2(humanTitle);
		JLabel humanDesc = new JLabel("<html>A vulnerable survivor needing<br> diverse interactions to stay happy.</html>"); //Health = 100, Fullness = 80, Sleep = 80, Happiness = 70
		JPanel robotPanel = new JPanel();
		JLabel robotTitle = new JLabel("Robot");
		setH2(robotTitle);
		JLabel robotDesc = new JLabel("<html>A reliable pet, constructed from salvaged parts.<br> It is sturdy and reliable.</html>"); //Health = 100, Fullness = 70, Sleep = 0, Happiness = 100
		JButton backToMainMenu = new JButton("Back to Main Menu");
		
		zombiePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		humanPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		robotPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		//add elements to panels
		setVertical(zombiePanel);
		//zombie image goes here
		zombiePanel.add(zombieTitle);
		zombiePanel.add(zombieDesc);
		setVertical(humanPanel);
		//human image goes here
		humanPanel.add(humanTitle);
		humanPanel.add(humanDesc);
		setVertical(robotPanel);
		//robot image goes here
		robotPanel.add(robotTitle);
		robotPanel.add(robotDesc);
		
		/*//add eventlisteners to panels and buttons
		zombiePanel.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				ScreenManager.swapView("5");
				//create zombie pet
			}
		});
		backToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.swapView("0");
			}
		});*/
		
		zombiePanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        ScreenManager.getInstance().initializeMainGameScreen("Zombie", "zombie");
		    }
		});

		humanPanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        ScreenManager.getInstance().initializeMainGameScreen("Human Pet", "human");
		    }
		});

		robotPanel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        ScreenManager.getInstance().initializeMainGameScreen("Robot Pet", "robot");
		    }
		});

		backToMainMenu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ScreenManager.swapView("0");
		    }
		});

		
		//organize and add subpanels to main panel
		petsPanel.add(zombiePanel);
		petsPanel.add(humanPanel);
		petsPanel.add(robotPanel);
		setVertical(this.panel);
		this.panel.add(title);
		this.panel.add(petsPanel);
		title.setAlignmentX((float)0.5);
		petsPanel.setAlignmentX((float)0.5);
		this.panel.add(backToMainMenu);
		backToMainMenu.setAlignmentX((float)0.1);
		
		// Integrate keyboard shortcuts
		setupKeyboardShortcuts();
	}
		
	/**
     * Sets up keyboard shortcuts for the tutorial screen.
     */
    private void setupKeyboardShortcuts() {
        KeyboardShortcutManager.setupGlobalShortcuts(this.panel);
        this.panel.requestFocusInWindow();
    }
}

