package me.jnsbndgz.spawnerpickaxeaddon.tool;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.List;

import static org.bukkit.Sound.ENTITY_ITEM_BREAK;

public class SpawnerPickaxe extends SlimefunItem {

    public SpawnerPickaxe(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Boolean isWildStacker) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(new ToolUseHandler() {
            @Override
            public void onToolUse(BlockBreakEvent blockBreakEvent, ItemStack itemStack, int i, List<ItemStack> list) {

                blockBreakEvent.setCancelled(true);

                if (!blockBreakEvent.getBlock().getType().name().contains("SPAWNER")) {
                    blockBreakEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&7[&5&lSpawner Pickaxe&7] &7This pickaxe only has effect on spawners!"));
                    return;
                }

                Block block = blockBreakEvent.getBlock();
                Player player = blockBreakEvent.getPlayer();

                if(!isWildStacker) {
                    EntityType entityType = ((CreatureSpawner) block.getState()).getSpawnedType();

                    ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
                    BlockStateMeta blockStateMeta = (BlockStateMeta) spawner.getItemMeta();
                    CreatureSpawner creatureSpawner = (CreatureSpawner) blockStateMeta.getBlockState();

                    creatureSpawner.setSpawnedType(entityType);
                    blockStateMeta.setBlockState(creatureSpawner);
                    blockStateMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&d" + generateSpawnerName(entityType) + " Spawner"));
                    spawner.setItemMeta(blockStateMeta);

                    consumeSpawnerPickaxe(player, itemStack);
                    block.breakNaturally();
                    block.getWorld().dropItemNaturally(block.getLocation(), spawner);
                }
            }
        });
    }

    private void consumeSpawnerPickaxe(Player player, ItemStack itemStack) {
        player.getWorld().playSound(player.getLocation(), ENTITY_ITEM_BREAK, 1F, 1F);
        player.getInventory().removeItem(itemStack);
    }

    private String generateSpawnerName(EntityType entityType) {
        String origin = entityType.getKey().toString().split(":")[1].replace("_", " ");
        StringBuilder sb = new StringBuilder("");
        String[] parts = origin.split(" ");

        for(String s : parts) {
            sb.append(s.substring(0, 1).toUpperCase());
            sb.append(s.substring(1));
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }
}
