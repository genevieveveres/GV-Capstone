package star.odyssey.inventory;

public class Weapon extends Item {
    private int damage;
    private int range;
    private int durability;

    public Weapon(String name, String description, int damage, int range, int durability) {
        super(name, description);
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
