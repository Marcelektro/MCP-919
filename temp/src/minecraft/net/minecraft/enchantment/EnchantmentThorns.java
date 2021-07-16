package net.minecraft.enchantment;

import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class EnchantmentThorns extends Enchantment {
   public EnchantmentThorns(int p_i45764_1_, ResourceLocation p_i45764_2_, int p_i45764_3_) {
      super(p_i45764_1_, p_i45764_2_, p_i45764_3_, EnumEnchantmentType.ARMOR_TORSO);
      this.func_77322_b("thorns");
   }

   public int func_77321_a(int p_77321_1_) {
      return 10 + 20 * (p_77321_1_ - 1);
   }

   public int func_77317_b(int p_77317_1_) {
      return super.func_77321_a(p_77317_1_) + 50;
   }

   public int func_77325_b() {
      return 3;
   }

   public boolean func_92089_a(ItemStack p_92089_1_) {
      return p_92089_1_.func_77973_b() instanceof ItemArmor?true:super.func_92089_a(p_92089_1_);
   }

   public void func_151367_b(EntityLivingBase p_151367_1_, Entity p_151367_2_, int p_151367_3_) {
      Random random = p_151367_1_.func_70681_au();
      ItemStack itemstack = EnchantmentHelper.func_92099_a(Enchantment.field_92091_k, p_151367_1_);
      if(func_92094_a(p_151367_3_, random)) {
         if(p_151367_2_ != null) {
            p_151367_2_.func_70097_a(DamageSource.func_92087_a(p_151367_1_), (float)func_92095_b(p_151367_3_, random));
            p_151367_2_.func_85030_a("damage.thorns", 0.5F, 1.0F);
         }

         if(itemstack != null) {
            itemstack.func_77972_a(3, p_151367_1_);
         }
      } else if(itemstack != null) {
         itemstack.func_77972_a(1, p_151367_1_);
      }

   }

   public static boolean func_92094_a(int p_92094_0_, Random p_92094_1_) {
      return p_92094_0_ <= 0?false:p_92094_1_.nextFloat() < 0.15F * (float)p_92094_0_;
   }

   public static int func_92095_b(int p_92095_0_, Random p_92095_1_) {
      return p_92095_0_ > 10?p_92095_0_ - 10:1 + p_92095_1_.nextInt(4);
   }
}
