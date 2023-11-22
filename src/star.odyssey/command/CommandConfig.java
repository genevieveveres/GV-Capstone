package star.odyssey.command;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.Map;

public class CommandConfig {
    private Map<String, String[]> commands;

    public CommandConfig(String configFile) {
        try {
            Gson gson = new Gson();
            // Read commands and their synonyms from JSON configuration file
            commands = gson.fromJson(new FileReader(configFile), Map.class);
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }

    public String[] getSynonyms(String command) {
        // Return synonyms for a given command, or an empty array if none
        return commands.getOrDefault(command, new String[0]);
    }

    public Map<String, String[]> getCommands() {
        // Return the entire map of commands and their synonyms
        return commands;
    }
}
