package star.odyssey.character;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import star.odyssey.inventory.Item;

import java.io.FileReader;
import java.util.*;

public class NPCManager {
    private final Map<String, NPC> npcs;
    private final Map<String, String> npcLocationMap; // Map of NPC index to Location index
    private final Map<String, List<String>> npcItemsMap; // Map of NPC index to list of Item indexes

    public NPCManager(String jsonFilePath) {
        npcs = new HashMap<>();
        npcLocationMap = new HashMap<>();
        npcItemsMap = new HashMap<>();
        loadNPCsFromJson(jsonFilePath);
    }

    private void loadNPCsFromJson(String jsonFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray npcsArray = jsonObject.getAsJsonArray("npcs");

            if (npcsArray != null) {
                for (JsonElement npcElement : npcsArray) {
                    NPC npc = createNPC(npcElement.getAsJsonObject());
                    npcs.put(npc.getIndex(), npc);

                    // Storing location and item information for later association
                    JsonObject npcObj = npcElement.getAsJsonObject();
                    String locationIndex = npcObj.has("location") ? npcObj.get("location").getAsString() : null;
                    npcLocationMap.put(npc.getIndex(), locationIndex);
                    List<String> itemIndexes = npcObj.has("items") ? parseItemIndexes(npcObj.getAsJsonArray("items")) : Collections.emptyList();
                    npcItemsMap.put(npc.getIndex(), itemIndexes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NPC createNPC(JsonObject npcObject) {
        String index = npcObject.get("index").getAsString();
        String name = npcObject.get("name").getAsString();
        int health = npcObject.get("health").getAsInt();
        int strength = npcObject.get("strength").getAsInt();
        int defense = npcObject.get("defense").getAsInt();
        String detailedDescription = npcObject.get("detailed_description").getAsString();

        boolean isAlive = npcObject.get("isAlive").getAsBoolean();
        boolean hostile = npcObject.get("hostile").getAsBoolean();
        List<String> dialogueOptions = parseDialogueOptions(npcObject.getAsJsonArray("dialogueOptions"));
        String questDetails = npcObject.get("questDetails").getAsString();
        boolean hidden = npcObject.get("hidden").getAsBoolean();

        // Initialize NPC without location and inventory
        return new NPC(index, name, health, strength, defense, detailedDescription, null, new ArrayList<>(), isAlive, hostile, dialogueOptions, questDetails, hidden);
    }

    private List<String> parseDialogueOptions(JsonArray dialogueOptionsArray) {
        List<String> dialogueOptions = new ArrayList<>();
        for (JsonElement element : dialogueOptionsArray) {
            dialogueOptions.add(element.getAsString());
        }
        return dialogueOptions;
    }

    private List<String> parseItemIndexes(JsonArray itemIndexesArray) {
        List<String> itemIndexes = new ArrayList<>();
        for (JsonElement element : itemIndexesArray) {
            itemIndexes.add(element.getAsString());
        }
        return itemIndexes;
    }

    public void addItemToNPC(String npcIndex, Item item) {
        NPC npc = npcs.get(npcIndex);
        if (npc != null) {
            npc.getInventory().add(item);
        }
    }

    public NPC getNPC(String index) {
        return npcs.get(index);
    }

    public Map<String, NPC> getAllNPCs() {
        return new HashMap<>(npcs);
    }

    public String getNPCsLocationIndex(String npcIndex) {
        return npcLocationMap.get(npcIndex);
    }

    public List<String> getNPCsItemIndexes(String npcIndex) {
        return npcItemsMap.getOrDefault(npcIndex, Collections.emptyList());
    }

    // Additional methods as needed...
}
