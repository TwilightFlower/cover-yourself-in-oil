package io.github.nuclearfarts.oil;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.ModInitializer;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.nuclearfarts.oil.mixin.BrewingRecipeRegistryAccessor;

public class OilMod implements ModInitializer {
	public static final StatusEffect OIL_EFFECT = new OilStatusEffect(StatusEffectType.BENEFICIAL, 0xFDD90B);
	public static final Potion OIL_POTION = new Potion(new StatusEffectInstance(OIL_EFFECT, 1800));
	public static final AbilitySource COVERED_IN_OIL = Pal.getAbilitySource(id("oil"));
	
	@Override
	public void onInitialize() {
		Registry.register(Registry.STATUS_EFFECT, id("oil"), OIL_EFFECT);
		Registry.register(Registry.POTION, id("oil"), OIL_POTION);
		BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(Potions.WATER, Items.BEETROOT_SEEDS, OIL_POTION);
	}
	
	public static Identifier id(String path) {
		return new Identifier("cover_yourself_in_oil", path);
	}
}
