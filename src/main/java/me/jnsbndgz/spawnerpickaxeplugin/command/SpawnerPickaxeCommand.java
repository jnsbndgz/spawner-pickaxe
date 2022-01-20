package me.jnsbndgz.spawnerpickaxeplugin.command;

import me.jnsbndgz.spawnerpickaxeplugin.tool.SpawnerPickaxe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnerPickaxeCommand implements CommandExecutor {

    private final SpawnerPickaxe spawnerPickaxe;

    public SpawnerPickaxeCommand(SpawnerPickaxe spawnerPickaxe) {
        this.spawnerPickaxe = spawnerPickaxe;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        ((Player) sender).getInventory().addItem(spawnerPickaxe);

        return true;
    }
}
