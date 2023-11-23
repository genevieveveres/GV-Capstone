package star.odyssey.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandConfig {
    private Map<String, List<String>> commands;

    public CommandConfig(String configFile) {
        try {
            Gson gson = new Gson();
            // Read commands and their synonyms from JSON configuration file
            commands = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, List<String>>>(){}.getType());
            validateSynonyms();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateSynonyms() {
        Set<String> uniqueSynonyms = new HashSet<>();
        Set<String> repeatedSynonyms = new HashSet<>();

        for (List<String> synonyms : commands.values()) {
            for (String synonym : synonyms) {
                if (!uniqueSynonyms.add(synonym)) { // add() returns false if the item already exists
                    repeatedSynonyms.add(synonym);
                }
            }
        }

        if (!repeatedSynonyms.isEmpty()) {
            System.err.println("Warning: Repeated synonyms found: " + repeatedSynonyms);
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
