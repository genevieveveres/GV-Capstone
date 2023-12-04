package star.odyssey.inventory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import star.odyssey.character.EntityManager;
import star.odyssey.location.LocationManager;

public class Weapon extends Item {
    private int damage;
    private int range;
    private int durability;

    public Weapon(String index, String name, String description, String detailedDescription, boolean usable, boolean active, boolean hidden, boolean movable, int damage, int range, int durability) {
        super(index, name, description, detailedDescription, usable, active, hidden, movable);
        this.damage = damage;
        this.range = range;
        this.durability = durability;
    }

    public void attack(Character target) {
        // Implement weapon attack logic against a target character
    }

    public void upgrade() {
        // Logic for upgrading weapon stats
    }

    public void decreaseDurability() {
        // Decrease weapon durability with use, deactivate if necessary
    }

    // Serialize and Deserialize
    @Override
    public String serialize() {
        String itemSerialized = super.serialize();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(itemSerialized, JsonObject.class);
        jsonObject.addProperty("damage", damage);
        jsonObject.addProperty("range", range);
        jsonObject.addProperty("durability", durability);
        return gson.toJson(jsonObject);
    }

    @Override
    public void deserialize(String serializedData, ItemManager itemManager, LocationManager locationManager, EntityManager entityManager) {
        super.deserialize(serializedData, itemManager, locationManager, entityManager); // Deserialize base Item properties

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(serializedData, JsonObject.class);

        // Update specific fields for Weapon
        this.damage = jsonObject.get("damage").getAsInt();
        this.range = jsonObject.get("range").getAsInt();
        this.durability = jsonObject.get("durability").getAsInt();
    }


    // Additional methods if necessary...
}
