package me.jnsbndgz.spawnerpickaxeplugin.util;

import me.jnsbndgz.spawnerpickaxeplugin.SpawnerPickaxePlugin;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class Util {

    public static void sendMessage(Player player, String msg) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> color(List<String> texts) {
        List<String> list = new ArrayList<>();

        for (String text : texts) {
            list.add(color(text));
        }

        return list;
    }

    public static Enchantment getGlowEnchant() {
        return new GlowEnchant(new NamespacedKey(SpawnerPickaxePlugin.getInstance(), "glow_enchant"), new String[]{});
    }
}
