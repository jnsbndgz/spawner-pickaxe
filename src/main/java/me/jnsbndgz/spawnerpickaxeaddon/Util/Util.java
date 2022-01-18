package me.jnsbndgz.spawnerpickaxeaddon.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class Util {

    public static void sendMessage(Player player, String msg) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
