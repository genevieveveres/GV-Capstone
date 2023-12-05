package star.odyssey.character;

import star.odyssey.command.Describable;
import star.odyssey.game.SerializableRPGObject;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;

import java.util.List;

public abstract class Entity implements Describable, SerializableRPGObject {
    protected String index;
    protected String name;
    protected int health;
    protected int strength;
    protected int defense;
    protected String detailedDescription;
    protected Location location;
    protected List<Item> inventory;
    protected boolean isAlive;
    protected Weapon weapon;

    public Entity() {
    }

    public Entity(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive) {
        this.index = index;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.detailedDescription = detailedDescription;
        this.location = location;
        this.inventory = inventory;
        this.isAlive = isAlive;
    }

    public Entity(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive, Weapon weapon) {
        this.index = index;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.detailedDescription = detailedDescription;
        this.location = location;
        this.inventory = inventory;
        this.isAlive = isAlive;
        this.weapon = weapon;
    }

    public String attack(Entity target) {
        int damage = calculateDamage(target.getDefense());
        String result = target.takeDamage(damage);
        return this.getName() + " attacked " + target.getName() + " for " + damage + " damage. " + result;
    }

    private int calculateDamage(int targetDefense) {
        int damage = this.strength - targetDefense;
        return Math.max(damage, 0);
    }

    public String takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            isAlive = false;
            return this.getName() + " has been defeated.";
        }
        return "";
    }

    public String equip(Weapon weapon) {
        if (weapon == null || !this.inventory.contains(weapon)) {
            return "Weapon is not in inventory.";
        }

        // Your logic to equip the weapon
        this.weapon = weapon;
        return this.name + " has equipped " + weapon.getName() + ".";
    }

    public void heal() {
        this.health += 10;
    }

    // Getters and setters
    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    // Additional methods if necessary...
}
