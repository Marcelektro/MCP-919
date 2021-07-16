package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionAbsorption extends Potion {
   protected PotionAbsorption(int p_i45901_1_, ResourceLocation p_i45901_2_, boolean p_i45901_3_, int p_i45901_4_) {
      super(p_i45901_1_, p_i45901_2_, p_i45901_3_, p_i45901_4_);
   }

   public void func_111187_a(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_) {
      p_111187_1_.func_110149_m(p_111187_1_.func_110139_bj() - (float)(4 * (p_111187_3_ + 1)));
      super.func_111187_a(p_111187_1_, p_111187_2_, p_111187_3_);
   }

   public void func_111185_a(EntityLivingBase p_111185_1_, BaseAttributeMap p_111185_2_, int p_111185_3_) {
      p_111185_1_.func_110149_m(p_111185_1_.func_110139_bj() + (float)(4 * (p_111185_3_ + 1)));
      super.func_111185_a(p_111185_1_, p_111185_2_, p_111185_3_);
   }
}
