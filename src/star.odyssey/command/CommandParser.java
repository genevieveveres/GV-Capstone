package star.odyssey.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandParser {

    private final Set<String> unnecessaryWords = new HashSet<>(Arrays.asList("the", "of", "a", "an"));

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
