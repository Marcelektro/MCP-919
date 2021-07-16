package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentOxygen extends Enchantment {
   public EnchantmentOxygen(int p_i45766_1_, ResourceLocation p_i45766_2_, int p_i45766_3_) {
      super(p_i45766_1_, p_i45766_2_, p_i45766_3_, EnumEnchantmentType.ARMOR_HEAD);
      this.func_77322_b("oxygen");
   }

   public int func_77321_a(int p_77321_1_) {
      return 10 * p_77321_1_;
   }

   public int func_77317_b(int p_77317_1_) {
      return this.func_77321_a(p_77317_1_) + 30;
   }

   public int func_77325_b() {
      return 3;
   }
}
