import java.io.*;

public class SaveManager {
    private static final String SAVE_DIRECTORY = "saves/";

    static {
        // Ensure the save directory exists
        File directory = new File(SAVE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // Method to save game data to a file
    public static void saveGame(int slot, String gameData) {
        try (FileWriter writer = new FileWriter(SAVE_DIRECTORY + "save" + slot + ".txt")) {
            writer.write(gameData);
            System.out.println("Game saved in slot " + slot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load game data from a file
    public static String loadGame(int slot) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_DIRECTORY + "save" + slot + ".txt"))) {
            StringBuilder gameData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                gameData.append(line).append("\n");
            }
            System.out.println("Game loaded from slot " + slot);
            return gameData.toString();
        } catch (IOException e) {
            System.out.println("No save file found in slot " + slot);
            return null;
        }
    }
}
