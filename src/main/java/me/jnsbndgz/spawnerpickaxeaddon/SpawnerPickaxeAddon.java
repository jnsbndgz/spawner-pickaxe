package me.jnsbndgz.spawnerpickaxeaddon;

import me.jnsbndgz.spawnerpickaxeaddon.command.SpawnerPickaxeCommand;
import me.jnsbndgz.spawnerpickaxeaddon.tool.SpawnerPickaxe;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerPickaxeAddon extends JavaPlugin {

    private static SpawnerPickaxeAddon instance;

    public static SpawnerPickaxeAddon getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        SpawnerPickaxe spawnerPickaxe = new SpawnerPickaxe();

        this.getCommand("spawnerpickaxe").setExecutor(new SpawnerPickaxeCommand(spawnerPickaxe));
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }
}
