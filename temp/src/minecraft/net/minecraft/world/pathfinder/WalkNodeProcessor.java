package net.minecraft.world.pathfinder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.pathfinder.NodeProcessor;

public class WalkNodeProcessor extends NodeProcessor {
   private boolean field_176180_f;
   private boolean field_176181_g;
   private boolean field_176183_h;
   private boolean field_176184_i;
   private boolean field_176182_j;

   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
      super.func_176162_a(p_176162_1_, p_176162_2_);
      this.field_176182_j = this.field_176183_h;
   }

   public void func_176163_a() {
      super.func_176163_a();
      this.field_176183_h = this.field_176182_j;
   }

   public PathPoint func_176161_a(Entity p_176161_1_) {
      int i;
      if(this.field_176184_i && p_176161_1_.func_70090_H()) {
         i = (int)p_176161_1_.func_174813_aQ().field_72338_b;
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.func_76128_c(p_176161_1_.field_70165_t), i, MathHelper.func_76128_c(p_176161_1_.field_70161_v));

         for(Block block = this.field_176169_a.func_180495_p(blockpos$mutableblockpos).func_177230_c(); block == Blocks.field_150358_i || block == Blocks.field_150355_j; block = this.field_176169_a.func_180495_p(blockpos$mutableblockpos).func_177230_c()) {
            ++i;
            blockpos$mutableblockpos.func_181079_c(MathHelper.func_76128_c(p_176161_1_.field_70165_t), i, MathHelper.func_76128_c(p_176161_1_.field_70161_v));
         }

         this.field_176183_h = false;
      } else {
         i = MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72338_b + 0.5D);
      }

      return this.func_176159_a(MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72340_a), i, MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72339_c));
   }

   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_) {
      return this.func_176159_a(MathHelper.func_76128_c(p_176160_2_ - (double)(p_176160_1_.field_70130_N / 2.0F)), MathHelper.func_76128_c(p_176160_4_), MathHelper.func_76128_c(p_176160_6_ - (double)(p_176160_1_.field_70130_N / 2.0F)));
   }

   public int func_176164_a(PathPoint[] p_176164_1_, Entity p_176164_2_, PathPoint p_176164_3_, PathPoint p_176164_4_, float p_176164_5_) {
      int i = 0;
      int j = 0;
      if(this.func_176177_a(p_176164_2_, p_176164_3_.field_75839_a, p_176164_3_.field_75837_b + 1, p_176164_3_.field_75838_c) == 1) {
         j = 1;
      }

      PathPoint pathpoint = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c + 1, j);
      PathPoint pathpoint1 = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a - 1, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c, j);
      PathPoint pathpoint2 = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a + 1, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c, j);
      PathPoint pathpoint3 = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c - 1, j);
      if(pathpoint != null && !pathpoint.field_75842_i && pathpoint.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[i++] = pathpoint;
      }

      if(pathpoint1 != null && !pathpoint1.field_75842_i && pathpoint1.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[i++] = pathpoint1;
      }

      if(pathpoint2 != null && !pathpoint2.field_75842_i && pathpoint2.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[i++] = pathpoint2;
      }

      if(pathpoint3 != null && !pathpoint3.field_75842_i && pathpoint3.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[i++] = pathpoint3;
      }

      return i;
   }

   private PathPoint func_176171_a(Entity p_176171_1_, int p_176171_2_, int p_176171_3_, int p_176171_4_, int p_176171_5_) {
      PathPoint pathpoint = null;
      int i = this.func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_, p_176171_4_);
      if(i == 2) {
         return this.func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_);
      } else {
         if(i == 1) {
            pathpoint = this.func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_);
         }

         if(pathpoint == null && p_176171_5_ > 0 && i != -3 && i != -4 && this.func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_ + p_176171_5_, p_176171_4_) == 1) {
            pathpoint = this.func_176159_a(p_176171_2_, p_176171_3_ + p_176171_5_, p_176171_4_);
            p_176171_3_ += p_176171_5_;
         }

         if(pathpoint != null) {
            int j = 0;

            int k;
            for(k = 0; p_176171_3_ > 0; pathpoint = this.func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_)) {
               k = this.func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_ - 1, p_176171_4_);
               if(this.field_176183_h && k == -1) {
                  return null;
               }

               if(k != 1) {
                  break;
               }

               if(j++ >= p_176171_1_.func_82143_as()) {
                  return null;
               }

               --p_176171_3_;
               if(p_176171_3_ <= 0) {
                  return null;
               }
            }

            if(k == -2) {
               return null;
            }
         }

         return pathpoint;
      }
   }

   private int func_176177_a(Entity p_176177_1_, int p_176177_2_, int p_176177_3_, int p_176177_4_) {
      return func_176170_a(this.field_176169_a, p_176177_1_, p_176177_2_, p_176177_3_, p_176177_4_, this.field_176168_c, this.field_176165_d, this.field_176166_e, this.field_176183_h, this.field_176181_g, this.field_176180_f);
   }

   public static int func_176170_a(IBlockAccess p_176170_0_, Entity p_176170_1_, int p_176170_2_, int p_176170_3_, int p_176170_4_, int p_176170_5_, int p_176170_6_, int p_176170_7_, boolean p_176170_8_, boolean p_176170_9_, boolean p_176170_10_) {
      boolean flag = false;
      BlockPos blockpos = new BlockPos(p_176170_1_);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int i = p_176170_2_; i < p_176170_2_ + p_176170_5_; ++i) {
         for(int j = p_176170_3_; j < p_176170_3_ + p_176170_6_; ++j) {
            for(int k = p_176170_4_; k < p_176170_4_ + p_176170_7_; ++k) {
               blockpos$mutableblockpos.func_181079_c(i, j, k);
               Block block = p_176170_0_.func_180495_p(blockpos$mutableblockpos).func_177230_c();
               if(block.func_149688_o() != Material.field_151579_a) {
                  if(block != Blocks.field_150415_aT && block != Blocks.field_180400_cw) {
                     if(block != Blocks.field_150358_i && block != Blocks.field_150355_j) {
                        if(!p_176170_10_ && block instanceof BlockDoor && block.func_149688_o() == Material.field_151575_d) {
                           return 0;
                        }
                     } else {
                        if(p_176170_8_) {
                           return -1;
                        }

                        flag = true;
                     }
                  } else {
                     flag = true;
                  }

                  if(p_176170_1_.field_70170_p.func_180495_p(blockpos$mutableblockpos).func_177230_c() instanceof BlockRailBase) {
                     if(!(p_176170_1_.field_70170_p.func_180495_p(blockpos).func_177230_c() instanceof BlockRailBase) && !(p_176170_1_.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c() instanceof BlockRailBase)) {
                        return -3;
                     }
                  } else if(!block.func_176205_b(p_176170_0_, blockpos$mutableblockpos) && (!p_176170_9_ || !(block instanceof BlockDoor) || block.func_149688_o() != Material.field_151575_d)) {
                     if(block instanceof BlockFence || block instanceof BlockFenceGate || block instanceof BlockWall) {
                        return -3;
                     }

                     if(block == Blocks.field_150415_aT || block == Blocks.field_180400_cw) {
                        return -4;
                     }

                     Material material = block.func_149688_o();
                     if(material != Material.field_151587_i) {
                        return 0;
                     }

                     if(!p_176170_1_.func_180799_ab()) {
                        return -2;
                     }
                  }
               }
            }
         }
      }

      return flag?2:1;
   }

   public void func_176175_a(boolean p_176175_1_) {
      this.field_176180_f = p_176175_1_;
   }

   public void func_176172_b(boolean p_176172_1_) {
      this.field_176181_g = p_176172_1_;
   }

   public void func_176176_c(boolean p_176176_1_) {
      this.field_176183_h = p_176176_1_;
   }

   public void func_176178_d(boolean p_176178_1_) {
      this.field_176184_i = p_176178_1_;
   }

   public boolean func_176179_b() {
      return this.field_176180_f;
   }

   public boolean func_176174_d() {
      return this.field_176184_i;
   }

   public boolean func_176173_e() {
      return this.field_176183_h;
   }
}
