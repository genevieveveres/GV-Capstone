package star.odyssey.ui;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;

public class DisplayGameInfo {

    public static void displayGameInfo() {
        String file = "./data/gameText.json";
        clearScreen();
        try {
            FileReader reader = new FileReader(file);

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(reader).getAsJsonObject();

            String backstory = jsonObject.get("gameinfo").getAsString();

            System.out.println(backstory);

            reader.close();
            pauseDisplay();
            clearScreen();
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }
}