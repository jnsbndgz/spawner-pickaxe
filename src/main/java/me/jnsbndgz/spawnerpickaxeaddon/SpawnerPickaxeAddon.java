package me.jnsbndgz.spawnerpickaxeaddon;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.jnsbndgz.spawnerpickaxeaddon.Util.GlowEnchant;
import me.jnsbndgz.spawnerpickaxeaddon.tool.SpawnerPickaxe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class SpawnerPickaxeAddon extends JavaPlugin implements SlimefunAddon {

    private static SpawnerPickaxeAddon instance;

    public static SpawnerPickaxeAddon getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Enchantment glowEnchantment = new GlowEnchant(new NamespacedKey(this, "glow_enchant"), new String[]{});

        ItemStack itemGroupItem = new CustomItemStack(Material.NETHERITE_PICKAXE, "&4Spawner Pickaxe Category",
                "", "&a> Click to open");
        ItemMeta itemGroupMeta = itemGroupItem.getItemMeta();
        itemGroupMeta.addEnchant(glowEnchantment, 1, false);
        itemGroupItem.setItemMeta(itemGroupMeta);

        NamespacedKey itemGroupId = new NamespacedKey(this, "spawner_pickaxe_category");
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);

        ItemStack spawnerPickaxeItem = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta itemMeta = spawnerPickaxeItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lSpawner Pickaxe"));
        itemMeta.setLore(Arrays.asList(
                ChatColor.translateAlternateColorCodes('&', "&7(One time use)"),
                "",
                ChatColor.translateAlternateColorCodes('&', "&bBreak the spawner to get it."))
        );
        itemMeta.addEnchant(glowEnchantment, 1, true);

        spawnerPickaxeItem.setItemMeta(itemMeta);

        SlimefunItemStack spawnerPickaxeSlimefunItem = new SlimefunItemStack("SPAWNER_PICKAXE", spawnerPickaxeItem);

        ItemStack[] recipe = {
                null, null, null,
                null, null, null,
                null, null, null
        };

        SpawnerPickaxe spawnerPickaxe = new SpawnerPickaxe(itemGroup, spawnerPickaxeSlimefunItem, RecipeType.NULL, recipe);

        spawnerPickaxe.register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/jnsbndgz/spawner-pickaxe/issues";
    }

    @Override
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }

}
