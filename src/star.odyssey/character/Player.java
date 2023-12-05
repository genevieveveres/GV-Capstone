package star.odyssey.character;

import star.odyssey.game.Game;
import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;
import star.odyssey.sound.SoundEffect;
import star.odyssey.ui.MainMenu;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class Player extends Entity {

    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "player_cmd_txt");

    public Player(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive, Weapon equippedWeapon) {
        super(index, name, health, strength, defense, detailedDescription, location, inventory, isAlive, equippedWeapon);
    }

    public String getItem(Item item) {
        if (!item.isMovable()) {
            return item.getName() + txtMap.get("item_unmovable");
        }
        if (item.isHidden()) {
            return item.getName() + txtMap.get("item_hidden");
        }
        if (!item.isActive()) {
            return item.getName() + txtMap.get("item_inactive");
        }

        this.inventory.add(item);
        this.location.removeItem(item);
        return item.getName() + txtMap.get("item_get");
    }

    public String dropItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                location.addInventory(item);
                return itemName + txtMap.get("item_drop");
            }
        }
        return txtMap.get("item_drop_fail") + itemName;
    }


    public String useItem(Item item) {
        if (!item.isUsable()) {
            return item.getName() + txtMap.get("use_not_usable");
        }
        if (item.isMovable() && !this.getInventory().contains(item)) {
            return item.getName() + txtMap.get("use_moveable_needs_pickedup");
        }
        if (item.isHidden()) {
            return item.getName() + txtMap.get("item_hidden");
        }
        if (!item.isActive()) {
            return item.getName() + txtMap.get("item_inactive");
        }
        if (!Objects.equals(item.getUseLocation(), "") && !item.getUseLocation().equals(this.getLocation().getIndex())) {
            return item.getName() + txtMap.get("use_not_location");
        }
        if (item.getIndex().equals("starstone")) {
            clearScreen();
            System.out.println(wrapText(GameUtil.jsonToString(gameTxtFilePath, "win_repair_engine")));
            pauseDisplay();
            Game.stop();
            clearScreen();
            MainMenu.execute();
            return null;
        }
        if (item.hasSound()) {
            String capitalizedName = item.getIndex().toUpperCase();
            try {
                SoundEffect effect = SoundEffect.valueOf(capitalizedName);
                effect.play();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }

        return wrapText(item.getUseText());
    }

    public void equipWeapon(Weapon weapon) {
        // Equip or change weapons for the player
    }
}
