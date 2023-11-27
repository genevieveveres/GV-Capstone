package star.odyssey.inventory;

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

    // Additional methods if necessary...
}
