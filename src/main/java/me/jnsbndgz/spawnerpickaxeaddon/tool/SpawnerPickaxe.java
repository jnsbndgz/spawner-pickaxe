package me.jnsbndgz.spawnerpickaxeaddon.tool;

import com.bgsoftware.wildstacker.api.events.SpawnerUnstackEvent;
import com.bgsoftware.wildstacker.api.objects.StackedSpawner;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.jnsbndgz.spawnerpickaxeaddon.SpawnerPickaxeAddon;
import me.jnsbndgz.spawnerpickaxeaddon.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Spawner Pickaxe, that can pick up spawners.
 *
 * @author jnsbndgz
 */
public class SpawnerPickaxe extends SlimefunItem implements Listener {

    public SpawnerPickaxe(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        Bukkit.getPluginManager().registerEvents(this, SpawnerPickaxeAddon.getInstance());
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent e) {

        Player player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!(block.getType() == Material.SPAWNER) && this.isItem(item)) {
            e.setCancelled(true);
            Util.sendMessage(player, "&7[&5&lSpawnerPickaxe&7] This pickaxe only breaks spawners!");
        }
    }

    @EventHandler
    private void onSpawnerBreak(SpawnerUnstackEvent e) {

        if (!(e.getUnstackSource() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getUnstackSource();
        StackedSpawner spawner = e.getSpawner();
        ItemStack pickaxe = player.getInventory().getItemInMainHand();

        if (!this.isItem(pickaxe)) {
            return;
        }

        consumeSpawnerPickaxe(player, pickaxe);
        spawner.getWorld().dropItemNaturally(spawner.getLocation(), spawner.getDropItem(1));
    }

    private void consumeSpawnerPickaxe(Player player, ItemStack itemStack) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
        player.getInventory().removeItem(itemStack);
    }
}
