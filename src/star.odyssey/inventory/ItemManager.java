package star.odyssey.inventory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private final Map<String, Item> items;

    public ItemManager(String jsonFilePath) {
        items = new HashMap<>();
        loadItemsFromJson(jsonFilePath);
    }

    private void loadItemsFromJson(String jsonFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray itemsArray = jsonObject.getAsJsonArray("items");

            if (itemsArray != null) {
                for (JsonElement itemElement : itemsArray) {
                    Item item = createItem(itemElement.getAsJsonObject());
                    items.put(item.getIndex(), item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Item createItem(JsonObject itemObject) {
        String index = itemObject.get("index").getAsString();
        String name = itemObject.get("name").getAsString();
        String description = itemObject.get("description").getAsString();
        String detailedDescription = itemObject.get("detailed_description").getAsString();
        boolean usable = itemObject.get("usable").getAsBoolean();
        boolean active = itemObject.get("active").getAsBoolean();
        boolean hidden = itemObject.get("hidden").getAsBoolean();
        boolean movable = itemObject.get("movable").getAsBoolean();

        return new Item(index, name, description, detailedDescription, usable, active, hidden, movable);
    }

    public Item getItem(String index) {
        return items.get(index);
    }

    public Map<String, Item> getAllItems() {
        return new HashMap<>(items);
    }

    // Additional methods as needed...
}