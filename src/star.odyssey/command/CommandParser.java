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
            List<String> words = gson.fromJson(new FileReader("./data/unnecessaryWords.json"), new TypeToken<List<String>>() {
            }.getType());
            unnecessaryWords.addAll(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ParsedCommand parseCommand(String input) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        StringBuilder nounBuilder = new StringBuilder();
        String verb = null;

        for (String word : words) {
            if (!unnecessaryWords.contains(word)) {
                if (verb == null) {
                    verb = word; // First significant word is the verb.
                } else {
                    // Append subsequent significant words to nounBuilder.
                    if (nounBuilder.length() > 0) {
                        nounBuilder.append(" ");
                    }
                    nounBuilder.append(word);
                }
            }
        }

        String noun = nounBuilder.toString();
        return new ParsedCommand(verb, noun);
    }
}