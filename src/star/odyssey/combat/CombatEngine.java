package star.odyssey.combat;

import star.odyssey.character.Entity;
import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.inventory.Weapon;
import star.odyssey.ui.ConsoleDisplayUtils;

import java.util.Random;

import static star.odyssey.ui.ConsoleDisplayUtils.makeGreen;
import static star.odyssey.ui.ConsoleDisplayUtils.makeRed;

public class CombatEngine {

    // INSTANCE VARIABLES
    private final Player player;
    private final NPC npc;
    private static final Random random = new Random();
    private static final double CRITICAL_HIT_CHANCE = 0.15;
    private static final double DODGE_CHANCE = 0.1;
    private static final double DAMAGE_VARIATION_FACTOR = 0.2;
    private static final int CRITICAL_HIT_MULTIPLIER = 2;

    // CONSTRUCTORS
    public CombatEngine(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    // METHODS
    public String startCombat() {
        StringBuilder combatLog = new StringBuilder();
        combatLog.append(getNPCStats()).append("\n");
        boolean playerDefeated = false;

        while (player.isAlive() && npc.isAlive()) {
            combatLog.append(performAttack(player, npc)).append("\n");
            if (!npc.isAlive()) break;

            combatLog.append(performAttack(npc, player)).append("\n");
            if (!player.isAlive()) {
                playerDefeated = true;
                break;
            }
        }

        if (playerDefeated) {
            return combatLog.append("\nPlayer defeated. Returning to main menu...").toString();
        }

        return combatLog.toString();
    }

    private String getNPCStats() {
        StringBuilder stats = new StringBuilder();
        Weapon equippedWeapon = npc.getEquippedWeapon();

        stats.append(npc.getName()).append(" Combat Stats:\n");
        stats.append("Health: ").append(npc.getHealth());
        stats.append(", Strength: ").append(npc.getStrength());
        stats.append(", Defense: ").append(npc.getDefense());
        stats.append(", Equipped Weapon: ").append(equippedWeapon.getName() != null ? equippedWeapon.getName() : "None").append("\n");

        if (equippedWeapon.getName() != null) {
            stats.append("Weapon Damage: ").append(equippedWeapon.getDamage());
            stats.append(", Weapon Range: ").append(equippedWeapon.getRange());
            stats.append(", Weapon Durability: ").append(equippedWeapon.getDurability()).append("\n");
        }
        return stats.toString();
    }

    private String performAttack(Entity attacker, Entity defender) {
        if (dodgeAttack()) {
            return defender.getName() + " dodged the attack by " + attacker.getName() + ".";
        }

        int baseDamage = calculateDamage(attacker, defender);
        baseDamage = applyRandomVariation(baseDamage);

        if (isCriticalHit()) {
            baseDamage *= CRITICAL_HIT_MULTIPLIER;
            return attacker.getName() + " landed a critical hit on " + defender.getName() + " for " + baseDamage + " damage.\n" + takeDamage(defender, baseDamage);
        }

        return attacker.getName() + " attacked " + defender.getName() + " for " + baseDamage + " damage.\n" + takeDamage(defender, baseDamage);
    }

    private int calculateDamage(Entity attacker, Entity defender) {
        Weapon weapon = attacker.getEquippedWeapon();
        int weaponDamage = weapon != null ? weapon.getDamage() : 0;
        int totalAttackValue = attacker.getStrength() + weaponDamage;
        int damage = totalAttackValue - defender.getDefense();

        int minimumDamage = 1;
        return Math.max(damage, minimumDamage);
    }

    private String takeDamage(Entity entity, int damage) {
        entity.setHealth(entity.getHealth() - damage);

        if (entity.getHealth() <= 0) {
            entity.setHealth(0);
            entity.setAlive(false);
            if (entity instanceof NPC) {
                ((NPC) entity).dropItems();
                ((NPC) entity).removeFromLocation();
            }
            return entity.getName() + " has been defeated.";
        }
        return entity.getName() + " took " + damage + " damage.";
    }

    private int applyRandomVariation(int baseDamage) {
        double variation = DAMAGE_VARIATION_FACTOR * baseDamage * (random.nextDouble() - 0.5);
        return Math.max((int) (baseDamage + variation), 0);
    }

    private boolean isCriticalHit() {
        return random.nextDouble() < CRITICAL_HIT_CHANCE;
    }

    private boolean dodgeAttack() {
        return random.nextDouble() < DODGE_CHANCE;
    }
}
