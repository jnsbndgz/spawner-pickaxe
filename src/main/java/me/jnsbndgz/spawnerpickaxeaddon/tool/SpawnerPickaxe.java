package me.jnsbndgz.spawnerpickaxeaddon.tool;

import com.bgsoftware.wildstacker.api.events.SpawnerUnstackEvent;
import com.bgsoftware.wildstacker.api.objects.StackedSpawner;
import me.jnsbndgz.spawnerpickaxeaddon.SpawnerPickaxeAddon;
import me.jnsbndgz.spawnerpickaxeaddon.Util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Spawner Pickaxe, that can pick up spawners.
 *
 * @author jnsbndgz
 */
public class SpawnerPickaxe implements Listener {

    private ItemStack pickaxe;

    public SpawnerPickaxe() {

        pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);

        ItemMeta meta = pickaxe.getItemMeta();

        meta.setDisplayName(Util.color("&b&lSpawner Pickaxe"));
        meta.setLore(Util.color(Arrays.asList("&7(One time use)", "", "&bBreak the spawner to get it.")));
        meta.addEnchant(Util.getGlowEnchant(), 1, true);

        pickaxe.setItemMeta(meta);

        Bukkit.getPluginManager().registerEvents(this, SpawnerPickaxeAddon.getInstance());
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!(block.getType() == Material.SPAWNER) && item.isSimilar(pickaxe)) {
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
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!(item.isSimilar(pickaxe))) {
            return;
        }

        consumeSpawnerPickaxe(player, item);
        spawner.getWorld().dropItemNaturally(spawner.getLocation(), spawner.getDropItem(1));
    }

    private void consumeSpawnerPickaxe(Player player, ItemStack itemStack) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
        player.getInventory().removeItem(itemStack);
    }

    public ItemStack getPickaxe() {
        return pickaxe;
    }
}
