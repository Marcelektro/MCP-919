package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpikes extends WorldGenerator {
   private Block field_150520_a;

   public WorldGenSpikes(Block p_i45464_1_) {
      this.field_150520_a = p_i45464_1_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      if(p_180709_1_.func_175623_d(p_180709_3_) && p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c() == this.field_150520_a) {
         int i = p_180709_2_.nextInt(32) + 6;
         int j = p_180709_2_.nextInt(4) + 1;
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k = p_180709_3_.func_177958_n() - j; k <= p_180709_3_.func_177958_n() + j; ++k) {
            for(int l = p_180709_3_.func_177952_p() - j; l <= p_180709_3_.func_177952_p() + j; ++l) {
               int i1 = k - p_180709_3_.func_177958_n();
               int j1 = l - p_180709_3_.func_177952_p();
               if(i1 * i1 + j1 * j1 <= j * j + 1 && p_180709_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(k, p_180709_3_.func_177956_o() - 1, l)).func_177230_c() != this.field_150520_a) {
                  return false;
               }
            }
         }

         for(int l1 = p_180709_3_.func_177956_o(); l1 < p_180709_3_.func_177956_o() + i && l1 < 256; ++l1) {
            for(int i2 = p_180709_3_.func_177958_n() - j; i2 <= p_180709_3_.func_177958_n() + j; ++i2) {
               for(int j2 = p_180709_3_.func_177952_p() - j; j2 <= p_180709_3_.func_177952_p() + j; ++j2) {
                  int k2 = i2 - p_180709_3_.func_177958_n();
                  int k1 = j2 - p_180709_3_.func_177952_p();
                  if(k2 * k2 + k1 * k1 <= j * j + 1) {
                     p_180709_1_.func_180501_a(new BlockPos(i2, l1, j2), Blocks.field_150343_Z.func_176223_P(), 2);
                  }
               }
            }
         }

         Entity entity = new EntityEnderCrystal(p_180709_1_);
         entity.func_70012_b((double)((float)p_180709_3_.func_177958_n() + 0.5F), (double)(p_180709_3_.func_177956_o() + i), (double)((float)p_180709_3_.func_177952_p() + 0.5F), p_180709_2_.nextFloat() * 360.0F, 0.0F);
         p_180709_1_.func_72838_d(entity);
         p_180709_1_.func_180501_a(p_180709_3_.func_177981_b(i), Blocks.field_150357_h.func_176223_P(), 2);
         return true;
      } else {
         return false;
      }
   }
}
