package star.odyssey.command;

import star.odyssey.game.GameUtil;
import star.odyssey.sound.SoundEffect;

import java.util.Map;

import static star.odyssey.game.GameUtil.IntToJson;
import static star.odyssey.game.GameUtil.jsonToInt;

public class SFXCommand implements Command {
    String gameTxtFilePath = "./data/gameText.json";
    private final Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "sfx_cmd");
    private final Map<String, String> optionsMap = GameUtil.jsonToStringMap(gameTxtFilePath, "sound_options");

    private final String gameSettings = "data/gameSettings.json";
    private final Map<String, String> soundLevelMap = GameUtil.jsonToStringMap(gameSettings, "sfx_levels");


    public SFXCommand() {
    }

    @Override
    public String execute(String option) {

        String currentVolume = "current_sfx_volume";
        String optionChoices = GameUtil.optionsValues(optionsMap);
        if (option == null || option.trim().isEmpty()) {
            return txtMap.get("sound_null") + optionChoices;
        }

        String optionKey = GameUtil.getValueKey(optionsMap, option).toString();
        String userSettings = "data/userSettings.json";
        switch (optionKey) {
            case "[1]":
                SoundEffect.setSoundEnabled(false);
                return txtMap.get("sound_off");
            case "[2]":
                SoundEffect.setSoundEnabled(true);
                return txtMap.get("sound_on");
            case "[3]":
                IntToJson(userSettings, currentVolume, Integer.parseInt(soundLevelMap.get("low_volume")));
                return txtMap.get("sound_low");
            case "[4]":
                IntToJson(userSettings, currentVolume, Integer.parseInt(soundLevelMap.get("med_volume")));
                return txtMap.get("sound_medium");
            case "[5]":
                IntToJson(userSettings, currentVolume, Integer.parseInt(soundLevelMap.get("high_volume")));
                return txtMap.get("sound_high");
            default:
                return option + txtMap.get("sound_unknown") + optionChoices;
        }
    }
}
