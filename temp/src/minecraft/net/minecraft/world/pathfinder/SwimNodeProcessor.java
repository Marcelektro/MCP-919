package net.minecraft.world.pathfinder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.pathfinder.NodeProcessor;

public class SwimNodeProcessor extends NodeProcessor {
   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
      super.func_176162_a(p_176162_1_, p_176162_2_);
   }

   public void func_176163_a() {
      super.func_176163_a();
   }

   public PathPoint func_176161_a(Entity p_176161_1_) {
      return this.func_176159_a(MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72340_a), MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72338_b + 0.5D), MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72339_c));
   }

   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_) {
      return this.func_176159_a(MathHelper.func_76128_c(p_176160_2_ - (double)(p_176160_1_.field_70130_N / 2.0F)), MathHelper.func_76128_c(p_176160_4_ + 0.5D), MathHelper.func_76128_c(p_176160_6_ - (double)(p_176160_1_.field_70130_N / 2.0F)));
   }

   public int func_176164_a(PathPoint[] p_176164_1_, Entity p_176164_2_, PathPoint p_176164_3_, PathPoint p_176164_4_, float p_176164_5_) {
      int i = 0;

      for(EnumFacing enumfacing : EnumFacing.values()) {
         PathPoint pathpoint = this.func_176185_a(p_176164_2_, p_176164_3_.field_75839_a + enumfacing.func_82601_c(), p_176164_3_.field_75837_b + enumfacing.func_96559_d(), p_176164_3_.field_75838_c + enumfacing.func_82599_e());
         if(pathpoint != null && !pathpoint.field_75842_i && pathpoint.func_75829_a(p_176164_4_) < p_176164_5_) {
            p_176164_1_[i++] = pathpoint;
         }
      }

      return i;
   }

   private PathPoint func_176185_a(Entity p_176185_1_, int p_176185_2_, int p_176185_3_, int p_176185_4_) {
      int i = this.func_176186_b(p_176185_1_, p_176185_2_, p_176185_3_, p_176185_4_);
      return i == -1?this.func_176159_a(p_176185_2_, p_176185_3_, p_176185_4_):null;
   }

   private int func_176186_b(Entity p_176186_1_, int p_176186_2_, int p_176186_3_, int p_176186_4_) {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int i = p_176186_2_; i < p_176186_2_ + this.field_176168_c; ++i) {
         for(int j = p_176186_3_; j < p_176186_3_ + this.field_176165_d; ++j) {
            for(int k = p_176186_4_; k < p_176186_4_ + this.field_176166_e; ++k) {
               Block block = this.field_176169_a.func_180495_p(blockpos$mutableblockpos.func_181079_c(i, j, k)).func_177230_c();
               if(block.func_149688_o() != Material.field_151586_h) {
                  return 0;
               }
            }
         }
      }

      return -1;
   }
}
