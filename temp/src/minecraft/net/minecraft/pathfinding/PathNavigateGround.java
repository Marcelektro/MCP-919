package net.minecraft.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class PathNavigateGround extends PathNavigate {
   protected WalkNodeProcessor field_179695_a;
   private boolean field_179694_f;

   public PathNavigateGround(EntityLiving p_i45875_1_, World p_i45875_2_) {
      super(p_i45875_1_, p_i45875_2_);
   }

   protected PathFinder func_179679_a() {
      this.field_179695_a = new WalkNodeProcessor();
      this.field_179695_a.func_176175_a(true);
      return new PathFinder(this.field_179695_a);
   }

   protected boolean func_75485_k() {
      return this.field_75515_a.field_70122_E || this.func_179684_h() && this.func_75506_l() || this.field_75515_a.func_70115_ae() && this.field_75515_a instanceof EntityZombie && this.field_75515_a.field_70154_o instanceof EntityChicken;
   }

   protected Vec3 func_75502_i() {
      return new Vec3(this.field_75515_a.field_70165_t, (double)this.func_179687_p(), this.field_75515_a.field_70161_v);
   }

   private int func_179687_p() {
      if(this.field_75515_a.func_70090_H() && this.func_179684_h()) {
         int i = (int)this.field_75515_a.func_174813_aQ().field_72338_b;
         Block block = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
         int j = 0;

         while(block == Blocks.field_150358_i || block == Blocks.field_150355_j) {
            ++i;
            block = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
            ++j;
            if(j > 16) {
               return (int)this.field_75515_a.func_174813_aQ().field_72338_b;
            }
         }

         return i;
      } else {
         return (int)(this.field_75515_a.func_174813_aQ().field_72338_b + 0.5D);
      }
   }

   protected void func_75487_m() {
      super.func_75487_m();
      if(this.field_179694_f) {
         if(this.field_75513_b.func_175678_i(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), (int)(this.field_75515_a.func_174813_aQ().field_72338_b + 0.5D), MathHelper.func_76128_c(this.field_75515_a.field_70161_v)))) {
            return;
         }

         for(int i = 0; i < this.field_75514_c.func_75874_d(); ++i) {
            PathPoint pathpoint = this.field_75514_c.func_75877_a(i);
            if(this.field_75513_b.func_175678_i(new BlockPos(pathpoint.field_75839_a, pathpoint.field_75837_b, pathpoint.field_75838_c))) {
               this.field_75514_c.func_75871_b(i - 1);
               return;
            }
         }
      }

   }

   protected boolean func_75493_a(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
      int i = MathHelper.func_76128_c(p_75493_1_.field_72450_a);
      int j = MathHelper.func_76128_c(p_75493_1_.field_72449_c);
      double d0 = p_75493_2_.field_72450_a - p_75493_1_.field_72450_a;
      double d1 = p_75493_2_.field_72449_c - p_75493_1_.field_72449_c;
      double d2 = d0 * d0 + d1 * d1;
      if(d2 < 1.0E-8D) {
         return false;
      } else {
         double d3 = 1.0D / Math.sqrt(d2);
         d0 = d0 * d3;
         d1 = d1 * d3;
         p_75493_3_ = p_75493_3_ + 2;
         p_75493_5_ = p_75493_5_ + 2;
         if(!this.func_179683_a(i, (int)p_75493_1_.field_72448_b, j, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, d0, d1)) {
            return false;
         } else {
            p_75493_3_ = p_75493_3_ - 2;
            p_75493_5_ = p_75493_5_ - 2;
            double d4 = 1.0D / Math.abs(d0);
            double d5 = 1.0D / Math.abs(d1);
            double d6 = (double)(i * 1) - p_75493_1_.field_72450_a;
            double d7 = (double)(j * 1) - p_75493_1_.field_72449_c;
            if(d0 >= 0.0D) {
               ++d6;
            }

            if(d1 >= 0.0D) {
               ++d7;
            }

            d6 = d6 / d0;
            d7 = d7 / d1;
            int k = d0 < 0.0D?-1:1;
            int l = d1 < 0.0D?-1:1;
            int i1 = MathHelper.func_76128_c(p_75493_2_.field_72450_a);
            int j1 = MathHelper.func_76128_c(p_75493_2_.field_72449_c);
            int k1 = i1 - i;
            int l1 = j1 - j;

            while(k1 * k > 0 || l1 * l > 0) {
               if(d6 < d7) {
                  d6 += d4;
                  i += k;
                  k1 = i1 - i;
               } else {
                  d7 += d5;
                  j += l;
                  l1 = j1 - j;
               }

               if(!this.func_179683_a(i, (int)p_75493_1_.field_72448_b, j, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, d0, d1)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private boolean func_179683_a(int p_179683_1_, int p_179683_2_, int p_179683_3_, int p_179683_4_, int p_179683_5_, int p_179683_6_, Vec3 p_179683_7_, double p_179683_8_, double p_179683_10_) {
      int i = p_179683_1_ - p_179683_4_ / 2;
      int j = p_179683_3_ - p_179683_6_ / 2;
      if(!this.func_179692_b(i, p_179683_2_, j, p_179683_4_, p_179683_5_, p_179683_6_, p_179683_7_, p_179683_8_, p_179683_10_)) {
         return false;
      } else {
         for(int k = i; k < i + p_179683_4_; ++k) {
            for(int l = j; l < j + p_179683_6_; ++l) {
               double d0 = (double)k + 0.5D - p_179683_7_.field_72450_a;
               double d1 = (double)l + 0.5D - p_179683_7_.field_72449_c;
               if(d0 * p_179683_8_ + d1 * p_179683_10_ >= 0.0D) {
                  Block block = this.field_75513_b.func_180495_p(new BlockPos(k, p_179683_2_ - 1, l)).func_177230_c();
                  Material material = block.func_149688_o();
                  if(material == Material.field_151579_a) {
                     return false;
                  }

                  if(material == Material.field_151586_h && !this.field_75515_a.func_70090_H()) {
                     return false;
                  }

                  if(material == Material.field_151587_i) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   private boolean func_179692_b(int p_179692_1_, int p_179692_2_, int p_179692_3_, int p_179692_4_, int p_179692_5_, int p_179692_6_, Vec3 p_179692_7_, double p_179692_8_, double p_179692_10_) {
      for(BlockPos blockpos : BlockPos.func_177980_a(new BlockPos(p_179692_1_, p_179692_2_, p_179692_3_), new BlockPos(p_179692_1_ + p_179692_4_ - 1, p_179692_2_ + p_179692_5_ - 1, p_179692_3_ + p_179692_6_ - 1))) {
         double d0 = (double)blockpos.func_177958_n() + 0.5D - p_179692_7_.field_72450_a;
         double d1 = (double)blockpos.func_177952_p() + 0.5D - p_179692_7_.field_72449_c;
         if(d0 * p_179692_8_ + d1 * p_179692_10_ >= 0.0D) {
            Block block = this.field_75513_b.func_180495_p(blockpos).func_177230_c();
            if(!block.func_176205_b(this.field_75513_b, blockpos)) {
               return false;
            }
         }
      }

      return true;
   }

   public void func_179690_a(boolean p_179690_1_) {
      this.field_179695_a.func_176176_c(p_179690_1_);
   }

   public boolean func_179689_e() {
      return this.field_179695_a.func_176173_e();
   }

   public void func_179688_b(boolean p_179688_1_) {
      this.field_179695_a.func_176172_b(p_179688_1_);
   }

   public void func_179691_c(boolean p_179691_1_) {
      this.field_179695_a.func_176175_a(p_179691_1_);
   }

   public boolean func_179686_g() {
      return this.field_179695_a.func_176179_b();
   }

   public void func_179693_d(boolean p_179693_1_) {
      this.field_179695_a.func_176178_d(p_179693_1_);
   }

   public boolean func_179684_h() {
      return this.field_179695_a.func_176174_d();
   }

   public void func_179685_e(boolean p_179685_1_) {
      this.field_179694_f = p_179685_1_;
   }
}
