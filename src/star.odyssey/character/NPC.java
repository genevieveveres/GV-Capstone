package star.odyssey.character;

import star.odyssey.inventory.Item;
import star.odyssey.location.Location;

import java.util.List;

public class NPC extends Entity {
    private boolean hostile;
    private List<String> dialogueOptions;
    private String questDetails;
    private boolean hidden;

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
            return getName() + " looks at you, then at a distant point in space, and opts for silence.";
        }
        return dialogueOptions.get(0);
    }

    public void giveQuest() {
        // Logic for assigning quests to the player
    }

    public void dropLoot() {
        // Define loot dropping behavior upon NPC defeat
    }

    // Additional methods if necessary...
}
