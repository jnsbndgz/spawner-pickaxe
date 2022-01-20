package me.jnsbndgz.spawnerpickaxeplugin;

import me.jnsbndgz.spawnerpickaxeplugin.command.SpawnerPickaxeCommand;
import me.jnsbndgz.spawnerpickaxeplugin.tool.SpawnerPickaxe;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerPickaxePlugin extends JavaPlugin {

    private static SpawnerPickaxePlugin instance;

    public static SpawnerPickaxePlugin getInstance() {
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
