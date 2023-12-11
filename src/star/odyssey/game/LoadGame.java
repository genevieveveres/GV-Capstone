package star.odyssey.game;

import com.google.gson.Gson;
import java.io.FileReader;

public class LoadGame {

    // INSTANCE VARIABLES
    private final GameState gameState;

    // CONSTRUCTORS
    public LoadGame(GameState gameState) {
        this.gameState = gameState;
    }

    // METHODS
    // Load the saved game
    public void load() {
        try (FileReader reader = new FileReader("./data/gameSave.json")) {
            Gson gson = new Gson();
            String savedGameStateJson = gson.fromJson(reader, String.class);
            gameState.deserialize(savedGameStateJson, gameState.getItemManager(), gameState.getLocationManager(), gameState.getEntityManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
