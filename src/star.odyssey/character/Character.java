package star.odyssey.character;

import star.odyssey.inventory.Inventory;
import star.odyssey.location.Location;

public abstract class Character {
    protected String name;
    protected int health;
    protected int strength;
    protected int defense;
    protected Location location;
    protected Inventory inventory;
    protected boolean isAlive;

    public Character() {
        this.inventory = new Inventory();
        this.isAlive = true;
    }

    public Character(String name, int health, int strength, int defense, Location location) {
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

    // Additional methods if necessary...
}
