package net.minecraft.potion;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionAttackDamage extends Potion {
   protected PotionAttackDamage(int p_i45900_1_, ResourceLocation p_i45900_2_, boolean p_i45900_3_, int p_i45900_4_) {
      super(p_i45900_1_, p_i45900_2_, p_i45900_3_, p_i45900_4_);
   }

   public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
      return this.field_76415_H == Potion.field_76437_t.field_76415_H?(double)(-0.5F * (float)(p_111183_1_ + 1)):1.3D * (double)(p_111183_1_ + 1);
   }
}
