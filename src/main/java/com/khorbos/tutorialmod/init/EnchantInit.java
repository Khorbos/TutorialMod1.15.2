package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.enchantments.UpsetEnchantement;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<Enchantment>(ForgeRegistries.ENCHANTMENTS, TutorialMod.ID);

    public static final RegistryObject<Enchantment> UPSTEP = ENCHANTMENTS.register("upstep", () -> new UpsetEnchantement(Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] {EquipmentSlotType.FEET}));
}
