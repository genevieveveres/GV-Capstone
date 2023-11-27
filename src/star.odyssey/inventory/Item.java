package star.odyssey.inventory;

import star.odyssey.command.Describable;

public class Item implements Describable {
    private String index;
    private String name;
    private String description;
    private String detailedDescription;
    private boolean usable;
    private boolean active;
    private boolean hidden;
    private boolean movable;

    public Item(String index, String name, String description, String detailedDescription, boolean usable, boolean active, boolean hidden, boolean movable) {
        this.index = index;
        this.name = name;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.usable = usable;
        this.active = active;
        this.hidden = hidden;
        this.movable = movable;
    }

    public void use() {
        // Define how the item is used (e.g., consume potion, read scroll)
    }

    // Getters and setters
    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDetailedDescription() {
        return detailedDescription;
    }

    // Additional methods if necessary...
}
