package star.odyssey.command;

import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;

import java.util.Map;

import static star.odyssey.game.GameUtil.IntToJson;
import static star.odyssey.game.GameUtil.jsonToInt;

public class SoundCommand implements Command {
    String gameTxtFilePath = "./data/gameText.json";
    private final Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "sound_cmd");
    private final Map<String, String> optionsMap = GameUtil.jsonToStringMap(gameTxtFilePath, "sound_options");

    public SoundCommand() {
    }

    @Override
    public String execute(String option) {
        String gameSettings = "data/gameSettings.json";
        String currentVolume = "current_volume";
        String previousVolume = "previous_volume";
        String optionChoices = GameUtil.optionsValues(optionsMap);
        if (option == null || option.trim().isEmpty()) {
            return txtMap.get("sound_null") + optionChoices;
        }

        String optionKey = GameUtil.getValueKey(optionsMap, option).toString();
        switch (optionKey) {
            case "[1]":
                // Turn sound off and store current volume in previous volume
                int curVol = jsonToInt(gameSettings, currentVolume);
                IntToJson(gameSettings, previousVolume, curVol);
                IntToJson(gameSettings, currentVolume, 0);
                return txtMap.get("sound_off");
            case "[2]":
                // Turn on the sound using the previous volume
                int prevVol = jsonToInt(gameSettings, previousVolume);
                IntToJson(gameSettings, currentVolume, prevVol);
                return txtMap.get("sound_on");
            case "[3]":
                IntToJson(gameSettings, currentVolume, 75);
                return txtMap.get("sound_low");
            case "[4]":
                IntToJson(gameSettings, currentVolume, 85);
                return txtMap.get("sound_medium");
            case "[5]":
                IntToJson(gameSettings, currentVolume, 100);
                return txtMap.get("sound_high");
            default:
                return option + txtMap.get("sound_unknown") + optionChoices;
        }
    }
}
