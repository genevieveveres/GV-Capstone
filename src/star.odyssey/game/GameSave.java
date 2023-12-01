package star.odyssey.game;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class GameSave {

    private static final String SAVE_FILE_PATH = "./data/gameSave.json";

    public static void saveGame(GameState gameState) throws IOException {
        Gson gson = new Gson();



//        try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
//            writer.write(json);
//        }
    }
}