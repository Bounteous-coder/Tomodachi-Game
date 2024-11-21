import java.awt.CardLayout;

import javax.swing.JPanel;

public class ScreenManager {
	//static reference
	private static ScreenManager single_instance = null;
	
	static CardLayout c = new CardLayout();
	static JPanel currentScreen = new JPanel(c);
	public static String currentScreenNum="0";
	//private constructor (for singleton)
	private ScreenManager(){		
		//create screens
		Screen mainMenu = new MainMenuScreen();
		Screen tutorialScreen = new TutorialScreen();
		Screen settingsScreen = new SettingsScreen();
		Screen saveScreen = new SaveScreen();
		Screen mainGameScreen = new MainGameScreen();
		Screen inventoryScreen = new InventoryScreen(); 
		Screen petSelectScreen = new PetSelectScreen();
		//add screens to cardlayout
		currentScreen.add(mainMenu.panel,"0");
		currentScreen.add(petSelectScreen.panel,"1");
		currentScreen.add(saveScreen.panel,"2");
		currentScreen.add(settingsScreen.panel,"3");
		currentScreen.add(tutorialScreen.panel,"4");
		currentScreen.add(mainGameScreen.panel,"5");
		currentScreen.add(inventoryScreen.panel,"6");
		
		//default is mainMenu
		c.show(currentScreen, "0");

	}
	
	//swap view
	public static void swapView(String key) {
		c.show(currentScreen,key);
	}
	
	//create singleton
	public static synchronized ScreenManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ScreenManager();
 
        return single_instance;
    }
	
	//set current screen number = param s
	public static void setCurrentScreenNum(String s) {
		currentScreenNum=s;
	}
}