package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigMushroom extends WorldGenerator {
   private Block field_76523_a;

   public WorldGenBigMushroom(Block p_i46449_1_) {
      super(true);
      this.field_76523_a = p_i46449_1_;
   }

   public WorldGenBigMushroom() {
      super(false);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      if(this.field_76523_a == null) {
         this.field_76523_a = p_180709_2_.nextBoolean()?Blocks.field_150420_aW:Blocks.field_150419_aX;
      }

      int i = p_180709_2_.nextInt(3) + 4;
      boolean flag = true;
      if(p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + i + 1 < 256) {
         for(int j = p_180709_3_.func_177956_o(); j <= p_180709_3_.func_177956_o() + 1 + i; ++j) {
            int k = 3;
            if(j <= p_180709_3_.func_177956_o() + 3) {
               k = 0;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = p_180709_3_.func_177958_n() - k; l <= p_180709_3_.func_177958_n() + k && flag; ++l) {
               for(int i1 = p_180709_3_.func_177952_p() - k; i1 <= p_180709_3_.func_177952_p() + k && flag; ++i1) {
                  if(j >= 0 && j < 256) {
                     Block block = p_180709_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(l, j, i1)).func_177230_c();
                     if(block.func_149688_o() != Material.field_151579_a && block.func_149688_o() != Material.field_151584_j) {
                        flag = false;
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
            if(block1 != Blocks.field_150346_d && block1 != Blocks.field_150349_c && block1 != Blocks.field_150391_bh) {
               return false;
            } else {
               int k2 = p_180709_3_.func_177956_o() + i;
               if(this.field_76523_a == Blocks.field_150419_aX) {
                  k2 = p_180709_3_.func_177956_o() + i - 3;
               }

               for(int l2 = k2; l2 <= p_180709_3_.func_177956_o() + i; ++l2) {
                  int j3 = 1;
                  if(l2 < p_180709_3_.func_177956_o() + i) {
                     ++j3;
                  }

                  if(this.field_76523_a == Blocks.field_150420_aW) {
                     j3 = 3;
                  }

                  int k3 = p_180709_3_.func_177958_n() - j3;
                  int l3 = p_180709_3_.func_177958_n() + j3;
                  int j1 = p_180709_3_.func_177952_p() - j3;
                  int k1 = p_180709_3_.func_177952_p() + j3;

                  for(int l1 = k3; l1 <= l3; ++l1) {
                     for(int i2 = j1; i2 <= k1; ++i2) {
                        int j2 = 5;
                        if(l1 == k3) {
                           --j2;
                        } else if(l1 == l3) {
                           ++j2;
                        }

                        if(i2 == j1) {
                           j2 -= 3;
                        } else if(i2 == k1) {
                           j2 += 3;
                        }

                        BlockHugeMushroom.EnumType blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.func_176895_a(j2);
                        if(this.field_76523_a == Blocks.field_150420_aW || l2 < p_180709_3_.func_177956_o() + i) {
                           if((l1 == k3 || l1 == l3) && (i2 == j1 || i2 == k1)) {
                              continue;
                           }

                           if(l1 == p_180709_3_.func_177958_n() - (j3 - 1) && i2 == j1) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if(l1 == k3 && i2 == p_180709_3_.func_177952_p() - (j3 - 1)) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if(l1 == p_180709_3_.func_177958_n() + (j3 - 1) && i2 == j1) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if(l1 == l3 && i2 == p_180709_3_.func_177952_p() - (j3 - 1)) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if(l1 == p_180709_3_.func_177958_n() - (j3 - 1) && i2 == k1) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if(l1 == k3 && i2 == p_180709_3_.func_177952_p() + (j3 - 1)) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if(l1 == p_180709_3_.func_177958_n() + (j3 - 1) && i2 == k1) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }

                           if(l1 == l3 && i2 == p_180709_3_.func_177952_p() + (j3 - 1)) {
                              blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }
                        }

                        if(blockhugemushroom$enumtype == BlockHugeMushroom.EnumType.CENTER && l2 < p_180709_3_.func_177956_o() + i) {
                           blockhugemushroom$enumtype = BlockHugeMushroom.EnumType.ALL_INSIDE;
                        }

                        if(p_180709_3_.func_177956_o() >= p_180709_3_.func_177956_o() + i - 1 || blockhugemushroom$enumtype != BlockHugeMushroom.EnumType.ALL_INSIDE) {
                           BlockPos blockpos = new BlockPos(l1, l2, i2);
                           if(!p_180709_1_.func_180495_p(blockpos).func_177230_c().func_149730_j()) {
                              this.func_175903_a(p_180709_1_, blockpos, this.field_76523_a.func_176223_P().func_177226_a(BlockHugeMushroom.field_176380_a, blockhugemushroom$enumtype));
                           }
                        }
                     }
                  }
               }

               for(int i3 = 0; i3 < i; ++i3) {
                  Block block2 = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(i3)).func_177230_c();
                  if(!block2.func_149730_j()) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(i3), this.field_76523_a.func_176223_P().func_177226_a(BlockHugeMushroom.field_176380_a, BlockHugeMushroom.EnumType.STEM));
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
