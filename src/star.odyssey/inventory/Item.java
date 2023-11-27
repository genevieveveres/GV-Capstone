package star.odyssey.inventory;

import star.odyssey.command.Describable;

public class Item implements Describable {
    private String name;
    private String description;
    private String detailedDescription;
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

    // Getters and setters

    @Override
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public String getName() {
        return name;
    }

    // Additional methods if necessary...
}
