package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenSwamp extends WorldGenAbstractTree {
   private static final IBlockState field_181648_a = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.OAK);
   private static final IBlockState field_181649_b = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.OAK).func_177226_a(BlockOldLeaf.field_176236_b, Boolean.valueOf(false));

   public WorldGenSwamp() {
      super(false);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int i;
      for(i = p_180709_2_.nextInt(4) + 5; p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c().func_149688_o() == Material.field_151586_h; p_180709_3_ = p_180709_3_.func_177977_b()) {
         ;
      }

      boolean flag = true;
      if(p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + i + 1 <= 256) {
         for(int j = p_180709_3_.func_177956_o(); j <= p_180709_3_.func_177956_o() + 1 + i; ++j) {
            int k = 1;
            if(j == p_180709_3_.func_177956_o()) {
               k = 0;
            }

            if(j >= p_180709_3_.func_177956_o() + 1 + i - 2) {
               k = 3;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = p_180709_3_.func_177958_n() - k; l <= p_180709_3_.func_177958_n() + k && flag; ++l) {
               for(int i1 = p_180709_3_.func_177952_p() - k; i1 <= p_180709_3_.func_177952_p() + k && flag; ++i1) {
                  if(j >= 0 && j < 256) {
                     Block block = p_180709_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(l, j, i1)).func_177230_c();
                     if(block.func_149688_o() != Material.field_151579_a && block.func_149688_o() != Material.field_151584_j) {
                        if(block != Blocks.field_150355_j && block != Blocks.field_150358_i) {
                           flag = false;
                        } else if(j > p_180709_3_.func_177956_o()) {
                           flag = false;
                        }
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if(!flag) {
            return false;
         } else {
            Block block1 = p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c();
            if((block1 == Blocks.field_150349_c || block1 == Blocks.field_150346_d) && p_180709_3_.func_177956_o() < 256 - i - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());

               for(int l1 = p_180709_3_.func_177956_o() - 3 + i; l1 <= p_180709_3_.func_177956_o() + i; ++l1) {
                  int k2 = l1 - (p_180709_3_.func_177956_o() + i);
                  int i3 = 2 - k2 / 2;

                  for(int k3 = p_180709_3_.func_177958_n() - i3; k3 <= p_180709_3_.func_177958_n() + i3; ++k3) {
                     int l3 = k3 - p_180709_3_.func_177958_n();

                     for(int j1 = p_180709_3_.func_177952_p() - i3; j1 <= p_180709_3_.func_177952_p() + i3; ++j1) {
                        int k1 = j1 - p_180709_3_.func_177952_p();
                        if(Math.abs(l3) != i3 || Math.abs(k1) != i3 || p_180709_2_.nextInt(2) != 0 && k2 != 0) {
                           BlockPos blockpos = new BlockPos(k3, l1, j1);
                           if(!p_180709_1_.func_180495_p(blockpos).func_177230_c().func_149730_j()) {
                              this.func_175903_a(p_180709_1_, blockpos, field_181649_b);
                           }
                        }
                     }
                  }
               }

               for(int i2 = 0; i2 < i; ++i2) {
                  Block block2 = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(i2)).func_177230_c();
                  if(block2.func_149688_o() == Material.field_151579_a || block2.func_149688_o() == Material.field_151584_j || block2 == Blocks.field_150358_i || block2 == Blocks.field_150355_j) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(i2), field_181648_a);
                  }
               }

               for(int j2 = p_180709_3_.func_177956_o() - 3 + i; j2 <= p_180709_3_.func_177956_o() + i; ++j2) {
                  int l2 = j2 - (p_180709_3_.func_177956_o() + i);
                  int j3 = 2 - l2 / 2;
                  BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                  for(int i4 = p_180709_3_.func_177958_n() - j3; i4 <= p_180709_3_.func_177958_n() + j3; ++i4) {
                     for(int j4 = p_180709_3_.func_177952_p() - j3; j4 <= p_180709_3_.func_177952_p() + j3; ++j4) {
                        blockpos$mutableblockpos1.func_181079_c(i4, j2, j4);
                        if(p_180709_1_.func_180495_p(blockpos$mutableblockpos1).func_177230_c().func_149688_o() == Material.field_151584_j) {
                           BlockPos blockpos3 = blockpos$mutableblockpos1.func_177976_e();
                           BlockPos blockpos4 = blockpos$mutableblockpos1.func_177974_f();
                           BlockPos blockpos1 = blockpos$mutableblockpos1.func_177978_c();
                           BlockPos blockpos2 = blockpos$mutableblockpos1.func_177968_d();
                           if(p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(blockpos3).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, blockpos3, BlockVine.field_176278_M);
                           }

                           if(p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(blockpos4).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, blockpos4, BlockVine.field_176280_O);
                           }

                           if(p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(blockpos1).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, blockpos1, BlockVine.field_176279_N);
                           }

                           if(p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(blockpos2).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, blockpos2, BlockVine.field_176273_b);
                           }
                        }
                     }
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void func_181647_a(World p_181647_1_, BlockPos p_181647_2_, PropertyBool p_181647_3_) {
      IBlockState iblockstate = Blocks.field_150395_bd.func_176223_P().func_177226_a(p_181647_3_, Boolean.valueOf(true));
      this.func_175903_a(p_181647_1_, p_181647_2_, iblockstate);
      int i = 4;

      for(p_181647_2_ = p_181647_2_.func_177977_b(); p_181647_1_.func_180495_p(p_181647_2_).func_177230_c().func_149688_o() == Material.field_151579_a && i > 0; --i) {
         this.func_175903_a(p_181647_1_, p_181647_2_, iblockstate);
         p_181647_2_ = p_181647_2_.func_177977_b();
      }

   }
}
