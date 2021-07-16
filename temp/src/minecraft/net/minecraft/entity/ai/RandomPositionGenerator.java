package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGenerator {
   private static Vec3 field_75465_a = new Vec3(0.0D, 0.0D, 0.0D);

   public static Vec3 func_75463_a(EntityCreature p_75463_0_, int p_75463_1_, int p_75463_2_) {
      return func_75462_c(p_75463_0_, p_75463_1_, p_75463_2_, (Vec3)null);
   }

   public static Vec3 func_75464_a(EntityCreature p_75464_0_, int p_75464_1_, int p_75464_2_, Vec3 p_75464_3_) {
      field_75465_a = p_75464_3_.func_178786_a(p_75464_0_.field_70165_t, p_75464_0_.field_70163_u, p_75464_0_.field_70161_v);
      return func_75462_c(p_75464_0_, p_75464_1_, p_75464_2_, field_75465_a);
   }

   public static Vec3 func_75461_b(EntityCreature p_75461_0_, int p_75461_1_, int p_75461_2_, Vec3 p_75461_3_) {
      field_75465_a = (new Vec3(p_75461_0_.field_70165_t, p_75461_0_.field_70163_u, p_75461_0_.field_70161_v)).func_178788_d(p_75461_3_);
      return func_75462_c(p_75461_0_, p_75461_1_, p_75461_2_, field_75465_a);
   }

   private static Vec3 func_75462_c(EntityCreature p_75462_0_, int p_75462_1_, int p_75462_2_, Vec3 p_75462_3_) {
      Random random = p_75462_0_.func_70681_au();
      boolean flag = false;
      int i = 0;
      int j = 0;
      int k = 0;
      float f = -99999.0F;
      boolean flag1;
      if(p_75462_0_.func_110175_bO()) {
         double d0 = p_75462_0_.func_180486_cf().func_177954_c((double)MathHelper.func_76128_c(p_75462_0_.field_70165_t), (double)MathHelper.func_76128_c(p_75462_0_.field_70163_u), (double)MathHelper.func_76128_c(p_75462_0_.field_70161_v)) + 4.0D;
         double d1 = (double)(p_75462_0_.func_110174_bM() + (float)p_75462_1_);
         flag1 = d0 < d1 * d1;
      } else {
         flag1 = false;
      }

      for(int j1 = 0; j1 < 10; ++j1) {
         int l = random.nextInt(2 * p_75462_1_ + 1) - p_75462_1_;
         int k1 = random.nextInt(2 * p_75462_2_ + 1) - p_75462_2_;
         int i1 = random.nextInt(2 * p_75462_1_ + 1) - p_75462_1_;
         if(p_75462_3_ == null || (double)l * p_75462_3_.field_72450_a + (double)i1 * p_75462_3_.field_72449_c >= 0.0D) {
            if(p_75462_0_.func_110175_bO() && p_75462_1_ > 1) {
               BlockPos blockpos = p_75462_0_.func_180486_cf();
               if(p_75462_0_.field_70165_t > (double)blockpos.func_177958_n()) {
                  l -= random.nextInt(p_75462_1_ / 2);
               } else {
                  l += random.nextInt(p_75462_1_ / 2);
               }

               if(p_75462_0_.field_70161_v > (double)blockpos.func_177952_p()) {
                  i1 -= random.nextInt(p_75462_1_ / 2);
               } else {
                  i1 += random.nextInt(p_75462_1_ / 2);
               }
            }

            l = l + MathHelper.func_76128_c(p_75462_0_.field_70165_t);
            k1 = k1 + MathHelper.func_76128_c(p_75462_0_.field_70163_u);
            i1 = i1 + MathHelper.func_76128_c(p_75462_0_.field_70161_v);
            BlockPos blockpos1 = new BlockPos(l, k1, i1);
            if(!flag1 || p_75462_0_.func_180485_d(blockpos1)) {
               float f1 = p_75462_0_.func_180484_a(blockpos1);
               if(f1 > f) {
                  f = f1;
                  i = l;
                  j = k1;
                  k = i1;
                  flag = true;
               }
            }
         }
      }

      if(flag) {
         return new Vec3((double)i, (double)j, (double)k);
      } else {
         return null;
      }
   }
}
