package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionHealthBoost extends Potion {
   public PotionHealthBoost(int p_i45899_1_, ResourceLocation p_i45899_2_, boolean p_i45899_3_, int p_i45899_4_) {
      super(p_i45899_1_, p_i45899_2_, p_i45899_3_, p_i45899_4_);
   }

   public void func_111187_a(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_) {
      super.func_111187_a(p_111187_1_, p_111187_2_, p_111187_3_);
      if(p_111187_1_.func_110143_aJ() > p_111187_1_.func_110138_aP()) {
         p_111187_1_.func_70606_j(p_111187_1_.func_110138_aP());
      }

   }
}
