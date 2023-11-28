package star.odyssey.ui;

import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;

public class DisplayBackstory {
    public static void displayBackstory() {
        String file = "./data/gameText.json";
        clearScreen();
        try {
            FileReader reader = new FileReader(file);

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(reader).getAsJsonObject();

            String backstory = jsonObject.get("backstory").getAsString();

            System.out.println(backstory);

            reader.close();
            pauseDisplay();
            clearScreen();
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        displayBackstory();
    }
}