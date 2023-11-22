package star.odyssey.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandParser {
    private final Set<String> unnecessaryWords;

    public CommandParser() {
        unnecessaryWords = new HashSet<>();
        loadUnnecessaryWords();
    }

    private void loadUnnecessaryWords() {
        try {
            Gson gson = new Gson();
            List<String> words = gson.fromJson(new FileReader("./data/unnecessaryWords.json"), new TypeToken<List<String>>() {
            }.getType());
            unnecessaryWords.addAll(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ParsedCommand parseCommand(String input) {
        // Split input into words and filter out unnecessary words
        String[] words = input.trim().toLowerCase().split("\\s+");
        String verb = null;
        String noun = null;

        for (String word : words) {
            if (!unnecessaryWords.contains(word)) {
                // The first significant word is considered the verb
                if (verb == null) {
                    verb = word;
                } else {
                    // The second significant word is considered the noun
                    noun = word;
                    break;
                }
            }
        }

        return new ParsedCommand(verb, noun);
    }
}
