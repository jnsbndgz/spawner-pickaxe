package me.jnsbndgz.spawnerpickaxeaddon.Util;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A false enchantment used to make items glow
 *
 * @author NCBPFluffyBear
 * @author J3fftw1
 */
@SuppressWarnings("NullableProblems")
public class GlowEnchant extends Enchantment {

    private final Set<String> ids = new HashSet<>();

    public GlowEnchant(@Nonnull NamespacedKey key, @Nonnull String[] applicableItems) {
        super(key);
        ids.addAll(Arrays.asList(applicableItems));
    }

    @Nonnull
    @Override
    @Deprecated
    public String getName() {
        return "GlowEnchant";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    @Deprecated
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

}
