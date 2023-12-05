package star.odyssey.character;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import star.odyssey.command.Describable;
import star.odyssey.game.SerializableRPGObject;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

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
    protected Weapon equippedWeapon;

    public Entity(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive, Weapon equippedWeapon) {
        this.index = index;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.detailedDescription = detailedDescription;
        this.location = location;
        this.inventory = inventory;
        this.isAlive = isAlive;
        this.equippedWeapon = equippedWeapon;
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

        this.equippedWeapon = weapon;
        return this.name + " has equipped " + weapon.getName() + ".";
    }

    public void heal() {
        this.health += 10;
    }


    // Serialize and Deserialize
    @Override
    public String serialize() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("index", this.index);
        jsonObject.addProperty("health", this.health);
        jsonObject.addProperty("strength", this.strength);
        jsonObject.addProperty("defense", this.defense);
        jsonObject.addProperty("isAlive", this.isAlive);
        jsonObject.addProperty("locationIndex", this.location.getIndex());
        // Serialize inventory as a list of item indices
        List<String> inventoryIndices = this.inventory.stream()
                .map(Item::getIndex)
                .collect(Collectors.toList());
        jsonObject.add("inventoryIndices", gson.toJsonTree(inventoryIndices));

        // Serialize weapon index
        jsonObject.addProperty("weaponIndex", this.equippedWeapon.getIndex());

        return jsonObject.toString();
    }

    @Override
    public void deserialize(String serializedData, ItemManager itemManager, LocationManager locationManager, EntityManager entityManager) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(serializedData, JsonObject.class);

        // Updating basic attributes
        this.setHealth(jsonObject.get("health").getAsInt());
        this.setStrength(jsonObject.get("strength").getAsInt());
        this.setDefense(jsonObject.get("defense").getAsInt());
        this.setAlive(jsonObject.get("isAlive").getAsBoolean());

        // Updating the entity's location
        String locationIndex = jsonObject.get("locationIndex").getAsString();
        Location location = locationManager.getLocation(locationIndex);
        this.setLocation(location);

        // Updating the entity's inventory
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> itemIndices = gson.fromJson(jsonObject.get("inventoryIndices"), type);
        List<Item> updatedInventory = itemIndices.stream()
                .map(itemManager::getItem)
                .collect(Collectors.toList());
        this.setInventory(updatedInventory);

        // Updaing the entity's weapon
        String weaponIndex = jsonObject.get("weaponIndex").getAsString();
        Weapon weapon = (Weapon) itemManager.getItem(weaponIndex);
        this.setEquippedWeapon(weapon);
    }

    // Getters and setters
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
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

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Item getInventoryItem(String itemName) {
        Item entityItem = null;
        List<Item> playerInventory = this.getInventory();
        for (Item item : playerInventory) {
            if (item.getName().equals(itemName)) {
                entityItem = item;
            }
        }
        return entityItem;
    }
    // Additional methods if necessary...
}
