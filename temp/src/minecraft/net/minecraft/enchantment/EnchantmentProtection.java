package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class EnchantmentProtection extends Enchantment {
   private static final String[] field_77354_A = new String[]{"all", "fire", "fall", "explosion", "projectile"};
   private static final int[] field_77355_B = new int[]{1, 10, 5, 5, 3};
   private static final int[] field_77357_C = new int[]{11, 8, 6, 8, 6};
   private static final int[] field_77353_D = new int[]{20, 12, 10, 12, 15};
   public final int field_77356_a;

   public EnchantmentProtection(int p_i45765_1_, ResourceLocation p_i45765_2_, int p_i45765_3_, int p_i45765_4_) {
      super(p_i45765_1_, p_i45765_2_, p_i45765_3_, EnumEnchantmentType.ARMOR);
      this.field_77356_a = p_i45765_4_;
      if(p_i45765_4_ == 2) {
         this.field_77351_y = EnumEnchantmentType.ARMOR_FEET;
      }

   }

   public int func_77321_a(int p_77321_1_) {
      return field_77355_B[this.field_77356_a] + (p_77321_1_ - 1) * field_77357_C[this.field_77356_a];
   }

   public int func_77317_b(int p_77317_1_) {
      return this.func_77321_a(p_77317_1_) + field_77353_D[this.field_77356_a];
   }

   public int func_77325_b() {
      return 4;
   }

   public int func_77318_a(int p_77318_1_, DamageSource p_77318_2_) {
      if(p_77318_2_.func_76357_e()) {
         return 0;
      } else {
         float f = (float)(6 + p_77318_1_ * p_77318_1_) / 3.0F;
         return this.field_77356_a == 0?MathHelper.func_76141_d(f * 0.75F):(this.field_77356_a == 1 && p_77318_2_.func_76347_k()?MathHelper.func_76141_d(f * 1.25F):(this.field_77356_a == 2 && p_77318_2_ == DamageSource.field_76379_h?MathHelper.func_76141_d(f * 2.5F):(this.field_77356_a == 3 && p_77318_2_.func_94541_c()?MathHelper.func_76141_d(f * 1.5F):(this.field_77356_a == 4 && p_77318_2_.func_76352_a()?MathHelper.func_76141_d(f * 1.5F):0))));
      }
   }

   public String func_77320_a() {
      return "enchantment.protect." + field_77354_A[this.field_77356_a];
   }

   public boolean func_77326_a(Enchantment p_77326_1_) {
      if(p_77326_1_ instanceof EnchantmentProtection) {
         EnchantmentProtection enchantmentprotection = (EnchantmentProtection)p_77326_1_;
         return enchantmentprotection.field_77356_a == this.field_77356_a?false:this.field_77356_a == 2 || enchantmentprotection.field_77356_a == 2;
      } else {
         return super.func_77326_a(p_77326_1_);
      }
   }

   public static int func_92093_a(Entity p_92093_0_, int p_92093_1_) {
      int i = EnchantmentHelper.func_77511_a(Enchantment.field_77329_d.field_77352_x, p_92093_0_.func_70035_c());
      if(i > 0) {
         p_92093_1_ -= MathHelper.func_76141_d((float)p_92093_1_ * (float)i * 0.15F);
      }

      return p_92093_1_;
   }

   public static double func_92092_a(Entity p_92092_0_, double p_92092_1_) {
      int i = EnchantmentHelper.func_77511_a(Enchantment.field_77327_f.field_77352_x, p_92092_0_.func_70035_c());
      if(i > 0) {
         p_92092_1_ -= (double)MathHelper.func_76128_c(p_92092_1_ * (double)((float)i * 0.15F));
      }

      return p_92092_1_;
   }
}
