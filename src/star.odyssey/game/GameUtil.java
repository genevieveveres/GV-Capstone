package star.odyssey.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class GameUtil {
    public static Map<String, String> jsonToStringMap(String filePath, String desiredItem) {
        Map<String, String> map = null;
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonObject menuObject = jsonObject.getAsJsonObject(desiredItem);

            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            map = gson.fromJson(menuObject, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String jsonToString(String filePath, String desiredItem) {
        String s = null;
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();

            // reads the main_menu portion of gameText.json
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            s = jsonObject.get(desiredItem).getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}