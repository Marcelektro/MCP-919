package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentLootBonus extends Enchantment {
   protected EnchantmentLootBonus(int p_i45767_1_, ResourceLocation p_i45767_2_, int p_i45767_3_, EnumEnchantmentType p_i45767_4_) {
      super(p_i45767_1_, p_i45767_2_, p_i45767_3_, p_i45767_4_);
      if(p_i45767_4_ == EnumEnchantmentType.DIGGER) {
         this.func_77322_b("lootBonusDigger");
      } else if(p_i45767_4_ == EnumEnchantmentType.FISHING_ROD) {
         this.func_77322_b("lootBonusFishing");
      } else {
         this.func_77322_b("lootBonus");
      }

   }

   public int func_77321_a(int p_77321_1_) {
      return 15 + (p_77321_1_ - 1) * 9;
   }

   public int func_77317_b(int p_77317_1_) {
      return super.func_77321_a(p_77317_1_) + 50;
   }

   public int func_77325_b() {
      return 3;
   }

   public boolean func_77326_a(Enchantment p_77326_1_) {
      return super.func_77326_a(p_77326_1_) && p_77326_1_.field_77352_x != field_77348_q.field_77352_x;
   }
}
