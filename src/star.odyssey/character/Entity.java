package star.odyssey.character;

import star.odyssey.command.Describable;
import star.odyssey.inventory.Inventory;
import star.odyssey.location.Location;

public abstract class Entity implements Describable {
    protected String name;
    protected int health;
    protected int strength;
    protected int defense;
    protected String detailedDescription;
    protected Location location;
    protected Inventory inventory;
    protected boolean isAlive;

    public Entity() {
        this.inventory = new Inventory();
        this.isAlive = true;
    }

    public Entity(String name, int health, int strength, int defense, Location location) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.location = location;
        this.inventory = new Inventory();
        this.isAlive = true;
    }

    public abstract void move();

    public abstract void attack();

    public abstract void defend();

    // Getters and setters

    public String getName() {
        return name;
    }

    @Override
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public Location getLocation() {
        return location;
    }

    // Additional methods if necessary...
}
