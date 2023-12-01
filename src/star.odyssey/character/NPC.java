package star.odyssey.character;

import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class NPC extends Entity {
    private boolean hostile;
    private List<String> dialogueOptions;
    private String questDetails;
    private boolean hidden;

    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "npc_cmd_txt");


    public NPC() {
        super();
    }

    public NPC(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive, boolean hostile, List<String> dialogueOptions, String questDetails, boolean hidden) {
        super(index, name, health, strength, defense, detailedDescription, location, inventory, isAlive);
        this.hostile = hostile;
        this.dialogueOptions = dialogueOptions;
        this.questDetails = questDetails;
        this.hidden = hidden;
    }

    public void move() {
        // NPC-specific movement behavior (e.g., patrol, follow player)
    }

    public void attack() {
        // Logic for NPC to attack, if hostile
    }

    public void defend() {
        // Defensive behavior for NPC during combat
    }

    public String talk() {
        if (dialogueOptions.isEmpty()) {
            return getName() + txtMap.get("ignored");
        }
        Random rand = new Random();
        return dialogueOptions.get(rand.nextInt(dialogueOptions.size()));
    }

    public void giveQuest() {
        // Logic for assigning quests to the player
    }

    public void dropLoot() {
        // Define loot dropping behavior upon NPC defeat
    }

    // Serialize and Deserialize
    @Override
    public String serialize() {
        return null;
    }

    @Override
    public void deserialize(String serializedData, ItemManager itemManager, LocationManager locationManager, EntityManager entityManager) {

    }

    // Additional methods if necessary...
}
