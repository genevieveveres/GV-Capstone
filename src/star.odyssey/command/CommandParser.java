package star.odyssey.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandParser {
    private final Set<String> unnecessaryWords; // Set of words to ignore during parsing.

    public CommandParser() {
        unnecessaryWords = new HashSet<>();
        loadUnnecessaryWords(); // Load words that are not essential for command parsing.
    }

    private void loadUnnecessaryWords() {
        try {
            Gson gson = new Gson();
            // Reading unnecessary words from a JSON file.
            List<String> words = gson.fromJson(new FileReader("./data/unnecessaryWords.json"), new TypeToken<List<String>>() {}.getType());
            unnecessaryWords.addAll(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ParsedCommand parseCommand(String input) {
        // Splits input into words, excluding unnecessary ones, and creates a ParsedCommand.
        String[] words = input.trim().toLowerCase().split("\\s+");
        String verb = null;
        String noun = null;

        for (String word : words) {
            if (!unnecessaryWords.contains(word)) {
                if (verb == null) {
                    verb = word; // First significant word is the verb.
                } else {
                    noun = word; // Second significant word is the noun.
                    break;
                }
            }
        }

        return new ParsedCommand(verb, noun);
    }
}
