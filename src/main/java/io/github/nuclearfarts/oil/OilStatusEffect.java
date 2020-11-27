package io.github.nuclearfarts.oil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;

public class OilStatusEffect extends StatusEffect {

	public OilStatusEffect(StatusEffectType type, int color) {
		super(type, color);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if(!entity.world.isClient && entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if(player.world.hasRain(player.getBlockPos())) {
				Pal.grantAbility(player, VanillaAbilities.ALLOW_FLYING, OilMod.COVERED_IN_OIL);
			} else {
				Pal.revokeAbility(player, VanillaAbilities.ALLOW_FLYING, OilMod.COVERED_IN_OIL);
			}
		}
	}
	
	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		if(!entity.world.isClient && entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			Pal.revokeAbility(player, VanillaAbilities.ALLOW_FLYING, OilMod.COVERED_IN_OIL);
		}
	}
}
