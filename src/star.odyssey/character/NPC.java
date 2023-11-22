package star.odyssey.character;

import star.odyssey.location.Location;

public class NPC extends Character {
    private boolean hostile;
    private String[] dialogueOptions;
    private String questDetails;

    public NPC() {
        super();
    }

    public NPC(String name, int health, int strength, int defense, Location location, boolean hostile) {
        super(name, health, strength, defense, location);
        this.hostile = hostile;
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

    public void talk() {
        // Interaction logic for NPC dialogues
    }

    public void giveQuest() {
        // Logic for assigning quests to the player
    }

    public void dropLoot() {
        // Define loot dropping behavior upon NPC defeat
    }

    // Additional methods if necessary...
}
