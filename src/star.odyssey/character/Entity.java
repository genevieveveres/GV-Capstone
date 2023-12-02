package star.odyssey.character;

import star.odyssey.command.Describable;
import star.odyssey.game.SerializableRPGObject;
import star.odyssey.inventory.Item;
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

    public abstract void move();

    public abstract void attack();

    public abstract void defend();

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
