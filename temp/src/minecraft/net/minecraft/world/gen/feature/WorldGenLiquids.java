package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLiquids extends WorldGenerator {
   private Block field_150521_a;

   public WorldGenLiquids(Block p_i45465_1_) {
      this.field_150521_a = p_i45465_1_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      if(p_180709_1_.func_180495_p(p_180709_3_.func_177984_a()).func_177230_c() != Blocks.field_150348_b) {
         return false;
      } else if(p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c() != Blocks.field_150348_b) {
         return false;
      } else if(p_180709_1_.func_180495_p(p_180709_3_).func_177230_c().func_149688_o() != Material.field_151579_a && p_180709_1_.func_180495_p(p_180709_3_).func_177230_c() != Blocks.field_150348_b) {
         return false;
      } else {
         int i = 0;
         if(p_180709_1_.func_180495_p(p_180709_3_.func_177976_e()).func_177230_c() == Blocks.field_150348_b) {
            ++i;
         }

         if(p_180709_1_.func_180495_p(p_180709_3_.func_177974_f()).func_177230_c() == Blocks.field_150348_b) {
            ++i;
         }

         if(p_180709_1_.func_180495_p(p_180709_3_.func_177978_c()).func_177230_c() == Blocks.field_150348_b) {
            ++i;
         }

         if(p_180709_1_.func_180495_p(p_180709_3_.func_177968_d()).func_177230_c() == Blocks.field_150348_b) {
            ++i;
         }

         int j = 0;
         if(p_180709_1_.func_175623_d(p_180709_3_.func_177976_e())) {
            ++j;
         }

         if(p_180709_1_.func_175623_d(p_180709_3_.func_177974_f())) {
            ++j;
         }

         if(p_180709_1_.func_175623_d(p_180709_3_.func_177978_c())) {
            ++j;
         }

         if(p_180709_1_.func_175623_d(p_180709_3_.func_177968_d())) {
            ++j;
         }

         if(i == 3 && j == 1) {
            p_180709_1_.func_180501_a(p_180709_3_, this.field_150521_a.func_176223_P(), 2);
            p_180709_1_.func_175637_a(this.field_150521_a, p_180709_3_, p_180709_2_);
         }

         return true;
      }
   }
}
