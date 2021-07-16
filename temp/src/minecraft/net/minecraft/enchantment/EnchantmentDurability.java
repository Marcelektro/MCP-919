package net.minecraft.enchantment;

import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDurability extends Enchantment {
   protected EnchantmentDurability(int p_i45773_1_, ResourceLocation p_i45773_2_, int p_i45773_3_) {
      super(p_i45773_1_, p_i45773_2_, p_i45773_3_, EnumEnchantmentType.BREAKABLE);
      this.func_77322_b("durability");
   }

   public int func_77321_a(int p_77321_1_) {
      return 5 + (p_77321_1_ - 1) * 8;
   }

   public int func_77317_b(int p_77317_1_) {
      return super.func_77321_a(p_77317_1_) + 50;
   }

   public int func_77325_b() {
      return 3;
   }

   public boolean func_92089_a(ItemStack p_92089_1_) {
      return p_92089_1_.func_77984_f()?true:super.func_92089_a(p_92089_1_);
   }

   public static boolean func_92097_a(ItemStack p_92097_0_, int p_92097_1_, Random p_92097_2_) {
      return p_92097_0_.func_77973_b() instanceof ItemArmor && p_92097_2_.nextFloat() < 0.6F?false:p_92097_2_.nextInt(p_92097_1_ + 1) > 0;
   }
}
