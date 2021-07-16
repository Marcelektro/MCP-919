package net.minecraft.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionHealth extends Potion {
   public PotionHealth(int p_i45898_1_, ResourceLocation p_i45898_2_, boolean p_i45898_3_, int p_i45898_4_) {
      super(p_i45898_1_, p_i45898_2_, p_i45898_3_, p_i45898_4_);
   }

   public boolean func_76403_b() {
      return true;
   }

   public boolean func_76397_a(int p_76397_1_, int p_76397_2_) {
      return p_76397_1_ >= 1;
   }
}
