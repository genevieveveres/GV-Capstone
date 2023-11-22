package star.odyssey.command;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandConfig {
    private Map<String, List<String>> commands;

    public CommandConfig(String configFile) {
        try {
            Gson gson = new Gson();
            // Read commands and their synonyms from JSON configuration file
            commands = gson.fromJson(new FileReader(configFile), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getSynonyms(String command) {
        // Return synonyms for a given command, or an empty list if none
        return commands.getOrDefault(command, Collections.emptyList());
    }

    public Map<String, List<String>> getCommands() {
        // Return the entire map of commands and their synonyms
        return commands;
    }
}
