package star.odyssey.character;

import star.odyssey.inventory.Item;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;

import java.util.List;

public class Player extends Entity {

    public Player() {
        super();
    }

    public Player(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive) {
        super(index, name, health, strength, defense, detailedDescription, location, inventory, isAlive);
    }

    public void move() {
        // Implement player-specific movement (e.g., based on player input)
    }

    public void attack() {
        // Player-specific attack implementation (e.g., combat mechanics)
    }

    public void defend() {
        // Player-specific defense implementation (e.g., block, dodge)
    }

    public void pickUpItem(Item item) {
        // Logic for the player to pick up items
    }

    public void useItem(Item item) {
        // Implement usage of items from the inventory
    }

    public void interactWithNPC(NPC npc) {
        // Define player interaction with NPCs (e.g., start dialogue)
    }

    public void equipWeapon(Weapon weapon) {
        // Equip or change weapons for the player
    }

    // Additional methods if necessary...
}