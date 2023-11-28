package star.odyssey.ui;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class DisplaySplash {

    // Display splash screen
    public static void displaySplash() throws IOException {
        clearScreen();
        String file = "./data/gameText.json";
        try {
            FileReader fileReader = new FileReader(file);

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(fileReader).getAsJsonObject();

            String splashscreen = jsonObject.get("splashscreen").getAsString();

            fileReader.close();
            for (char c : splashscreen.toCharArray()) {
                if (c == '█') {
                    System.out.print(makeRed(String.valueOf(c)));
                } else if (c == '░') {
                    System.out.print(makeBlue(String.valueOf(c)));
                } else {
                    System.out.print(c);
                }
            }
            fileReader.close();
            pauseDisplay();
            clearScreen();
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        displaySplash();
    }
}
