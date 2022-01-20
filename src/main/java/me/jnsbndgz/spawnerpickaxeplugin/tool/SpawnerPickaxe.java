package me.jnsbndgz.spawnerpickaxeplugin.tool;

import com.bgsoftware.wildstacker.api.events.SpawnerUnstackEvent;
import com.bgsoftware.wildstacker.api.objects.StackedSpawner;
import me.jnsbndgz.spawnerpickaxeplugin.SpawnerPickaxePlugin;
import me.jnsbndgz.spawnerpickaxeplugin.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

/**
 * Spawner Pickaxe, that can pick up spawners.
 *
 * @author jnsbndgz
 */
public class SpawnerPickaxe extends ItemStack implements Listener {

    private final NamespacedKey PICKAXE_KEY = new NamespacedKey(SpawnerPickaxePlugin.getInstance(), "SPAWNER_PICKAXE");

    public SpawnerPickaxe() {
        super(Material.NETHERITE_PICKAXE);

        ItemMeta meta = this.getItemMeta();

        meta.setDisplayName(Util.color("&b&lSpawner Pickaxe"));
        meta.setLore(Util.color(Arrays.asList("&7(One time use)", "", "&bBreak the spawner to get it.")));
        meta.addEnchant(Util.getGlowEnchant(), 1, true);
        meta.getPersistentDataContainer().set(PICKAXE_KEY, PersistentDataType.BYTE, (byte) 1);

        this.setItemMeta(meta);

        Bukkit.getPluginManager().registerEvents(this, SpawnerPickaxePlugin.getInstance());
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!(block.getType() == Material.SPAWNER) && isSpawnerPickaxe(item)) {
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
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!isSpawnerPickaxe(item)) {
            return;
        }

        StackedSpawner spawner = e.getSpawner();

        consumeSpawnerPickaxe(player, item);
        spawner.getWorld().dropItemNaturally(spawner.getLocation(), spawner.getDropItem(1));
    }

    private void consumeSpawnerPickaxe(Player player, ItemStack itemStack) {
        Util.sendMessage(player, "&7[&5&lSpawnerPickaxe&7] You have successfully broke a spawner!");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
        player.getInventory().removeItem(itemStack);
    }

    private boolean isSpawnerPickaxe(ItemStack item) {
        if(item.getItemMeta() == null) {
            return false;
        }

        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        byte key = pdc.getOrDefault(PICKAXE_KEY, PersistentDataType.BYTE, (byte) 0);

        return key == 1;
    }
}
