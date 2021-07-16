package net.minecraft.entity.boss;

import net.minecraft.entity.boss.IBossDisplayData;

public final class BossStatus {
   public static float field_82828_a;
   public static int field_82826_b;
   public static String field_82827_c;
   public static boolean field_82825_d;

   public static void func_82824_a(IBossDisplayData p_82824_0_, boolean p_82824_1_) {
      field_82828_a = p_82824_0_.func_110143_aJ() / p_82824_0_.func_110138_aP();
      field_82826_b = 100;
      field_82827_c = p_82824_0_.func_145748_c_().func_150254_d();
      field_82825_d = p_82824_1_;
   }
}
