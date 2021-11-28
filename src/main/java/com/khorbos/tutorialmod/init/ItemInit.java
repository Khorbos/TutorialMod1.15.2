package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.TutorialMod.TutorialItemGroup;
import com.khorbos.tutorialmod.objects.items.SpecialItem;
import com.khorbos.tutorialmod.util.enums.ModItemTier;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, TutorialMod.ID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EDIBLE_ITEM = ITEMS.register("edible_item", () -> new Item(new Item.Properties().food(new Food.Builder().hunger(8).saturation(1.2F).build()).group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EDIBLE_MEAT_ITEM = ITEMS.register("edible_meat_item", () -> new Item(new Item.Properties().food(new Food.Builder().hunger(1).saturation(0.2F).meat().build()).group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EDIBLE_EFFECT_ITEM = ITEMS.register("edible_effect_item", () -> new Item(new Item.Properties().food(new Food.Builder().hunger(1).saturation(0.2F).effect(new EffectInstance(Effects.SPEED, 6000, 1), 1).build()).group(TutorialItemGroup.instance)));

    public static final RegistryObject<Item> EXAMPLE_SWORD = ITEMS.register("example_sword", () -> new SwordItem(ModItemTier.EXAMPLE, 7, 5.0F, new Item.Properties().group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EXAMPLE_PICKAXE = ITEMS.register("example_pickaxe", () -> new PickaxeItem(ModItemTier.EXAMPLE, 4, 5.0F, new Item.Properties().group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EXAMPLE_AXE = ITEMS.register("example_axe", () -> new AxeItem(ModItemTier.EXAMPLE, 11, 3.0F, new Item.Properties().group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EXAMPLE_SHOVEL = ITEMS.register("example_shovel", () -> new ShovelItem(ModItemTier.EXAMPLE, 2, 5.0F, new Item.Properties().group(TutorialItemGroup.instance)));
    public static final RegistryObject<Item> EXAMPLE_HOE = ITEMS.register("example_hoe", () -> new HoeItem(ModItemTier.EXAMPLE, 5.0F, new Item.Properties().group(TutorialItemGroup.instance)));

    public static final RegistryObject<SpecialItem> SPECIAL_ITEM = ITEMS.register("special_item", SpecialItem::new);

}
