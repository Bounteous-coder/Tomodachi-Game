import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Aftermath Creatures");
		f.setSize(960, 720);
		CardLayout c = new CardLayout();
		JPanel currentScreen = new JPanel(c);
		
		Screen mainMenu = new MainMenuScreen();
		Screen tutorialScreen = new TutorialScreen();
		Screen settingsScreen = new SettingsScreen();
		Screen saveScreen = new SaveScreen();
		Screen mainGameScreen = new MainGameScreen();
		f.add(currentScreen);
		
		//default screen is main menu
		currentScreen.add(mainMenu.panel);
		
		//if button is pressed change menus... somehow
		
		//testing
		currentScreen.remove(mainMenu.panel);
		currentScreen.add(mainGameScreen.panel);
		
		//note: this has to be at the end for some reason
		f.setVisible(true);
	}
}
