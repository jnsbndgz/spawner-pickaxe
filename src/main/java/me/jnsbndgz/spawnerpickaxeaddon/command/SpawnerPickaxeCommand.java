package me.jnsbndgz.spawnerpickaxeaddon.command;

import me.jnsbndgz.spawnerpickaxeaddon.tool.SpawnerPickaxe;
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

        Player player = (Player) sender;

        player.getInventory().addItem(spawnerPickaxe.getPickaxe());
        return true;
    }
}
