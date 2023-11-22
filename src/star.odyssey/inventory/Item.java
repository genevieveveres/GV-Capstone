package star.odyssey.inventory;

public class Item {
    private String name;
    private String description;
    private boolean usable;
    private boolean active;
    private boolean hidden;
    private boolean movable;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.usable = false;  // Items are usable by default
        this.active = true;   // Items are active by default
        this.movable = true;  // Items are movable by default

    }

    public void use() {
        // Define how the item is used (e.g., consume potion, read scroll)
    }

    public void examine() {
        // Logic for examining the item (e.g., display description)
    }

    // Additional methods if necessary...
}
