import java.awt.CardLayout;

import javax.swing.JPanel;

public class ScreenManager {
	//static reference
	private static ScreenManager single_instance = null;
	
	static CardLayout c = new CardLayout();
	static JPanel currentScreen = new JPanel(c);
	public static String currentScreenNum="0";
	
	// Reference to the shared Pet instance
    private Pet pet = null; // Initially null, will be set after pet selection
    
	//private constructor (for singleton)
	private ScreenManager(){	
		//create screens
		Screen mainMenu = new MainMenuScreen();
		Screen tutorialScreen = new TutorialScreen();
		Screen settingsScreen = new SettingsScreen();
		Screen saveScreen = new SaveScreen();
		Screen inventoryScreen = new InventoryScreen(); 
		Screen petSelectScreen = new PetSelectScreen();
		//add screens to cardlayout
		currentScreen.add(mainMenu.panel,"0");
		currentScreen.add(petSelectScreen.panel,"1");
		currentScreen.add(saveScreen.panel,"2");
		currentScreen.add(settingsScreen.panel,"3");
		currentScreen.add(tutorialScreen.panel,"4");
		currentScreen.add(inventoryScreen.panel,"6");
		
		//default is mainMenu
		c.show(currentScreen, "0");
		SoundManager.play("game_bgm.wav",true);

	}
	
	//create singleton
		public static synchronized ScreenManager getInstance()
	    {
			if (single_instance == null) {
	            single_instance = new ScreenManager();
	        }
	        return single_instance;
	    }
		
		public void initializeMainGameScreen(String petName, String petType) {
		    // Create the Pet instance
		    this.pet = new Pet(petName);

		    // Customize the Pet based on the type
		    switch (petType.toLowerCase()) {
		        case "zombie":
		            pet.setHealth(100);
		            pet.setFullness(100);
		            pet.setSleep(100);
		            pet.setHappiness(50);
		            break;
		        case "human":
		            pet.setHealth(100);
		            pet.setFullness(80);
		            pet.setSleep(80);
		            pet.setHappiness(70);
		            break;
		        case "robot":
		            pet.setHealth(100);
		            pet.setFullness(70);
		            pet.setSleep(70);
		            pet.setHappiness(100);
		            break;
		        default:
		            pet.setHealth(100);
		            pet.setFullness(100);
		            pet.setSleep(100);
		            pet.setHappiness(100);
		            break;
		    }

		    // Create MainGameScreen and add to CardLayout
		    Screen mainGameScreen = new MainGameScreen(this.pet);
		    currentScreen.add(mainGameScreen.panel, "5");

		    // Navigate to MainGameScreen
		    swapView("5");
		}

	
	//swap view
	public static void swapView(String key) {
		c.show(currentScreen,key);
		currentScreen.requestFocusInWindow(); // Ensure focus for keyboard shortcuts
	}
	
	
	//set current screen number = param s
	public static void setCurrentScreenNum(String s) {
		currentScreenNum=s;
	}
}
