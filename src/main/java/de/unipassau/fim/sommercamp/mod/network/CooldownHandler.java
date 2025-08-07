package de.unipassau.fim.sommercamp.mod.network;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownHandler {
    private static final Map<UUID, Map<Item, Integer>> playerCooldowns = new HashMap<>();

    // Get cooldown in ticks
    public static int getCooldown(UUID playerId, Item item) {
        return playerCooldowns.getOrDefault(playerId, Map.of()).getOrDefault(item, 0);
    }

    // Set cooldown in ticks
    public static void setCooldown(UUID playerId, Item item, int ticks) {
        playerCooldowns.computeIfAbsent(playerId, k -> new HashMap<>()).put(item, ticks);
    }

    // Decrement all cooldowns per tick
    public static void tick() {
        for (Map<Item, Integer> map : playerCooldowns.values()) {
            map.replaceAll((item, ticks) -> Math.max(0, ticks - 1));
        }
    }
}