package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReed extends Block {
   public static final PropertyInteger field_176355_a = PropertyInteger.func_177719_a("age", 0, 15);

   protected BlockReed() {
      super(Material.field_151585_k);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176355_a, Integer.valueOf(0)));
      float f = 0.375F;
      this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
      this.func_149675_a(true);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(p_180650_1_.func_180495_p(p_180650_2_.func_177977_b()).func_177230_c() == Blocks.field_150436_aH || this.func_176353_e(p_180650_1_, p_180650_2_, p_180650_3_)) {
         if(p_180650_1_.func_175623_d(p_180650_2_.func_177984_a())) {
            int i;
            for(i = 1; p_180650_1_.func_180495_p(p_180650_2_.func_177979_c(i)).func_177230_c() == this; ++i) {
               ;
            }

            if(i < 3) {
               int j = ((Integer)p_180650_3_.func_177229_b(field_176355_a)).intValue();
               if(j == 15) {
                  p_180650_1_.func_175656_a(p_180650_2_.func_177984_a(), this.func_176223_P());
                  p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176355_a, Integer.valueOf(0)), 4);
               } else {
                  p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176355_a, Integer.valueOf(j + 1)), 4);
               }
            }
         }

      }
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      Block block = p_176196_1_.func_180495_p(p_176196_2_.func_177977_b()).func_177230_c();
      if(block == this) {
         return true;
      } else if(block != Blocks.field_150349_c && block != Blocks.field_150346_d && block != Blocks.field_150354_m) {
         return false;
      } else {
         for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            if(p_176196_1_.func_180495_p(p_176196_2_.func_177972_a(enumfacing).func_177977_b()).func_177230_c().func_149688_o() == Material.field_151586_h) {
               return true;
            }
         }

         return false;
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176353_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   protected final boolean func_176353_e(World p_176353_1_, BlockPos p_176353_2_, IBlockState p_176353_3_) {
      if(this.func_176354_d(p_176353_1_, p_176353_2_)) {
         return true;
      } else {
         this.func_176226_b(p_176353_1_, p_176353_2_, p_176353_3_, 0);
         p_176353_1_.func_175698_g(p_176353_2_);
         return false;
      }
   }

   public boolean func_176354_d(World p_176354_1_, BlockPos p_176354_2_) {
      return this.func_176196_c(p_176354_1_, p_176354_2_);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151120_aE;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151120_aE;
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return p_180662_1_.func_180494_b(p_180662_2_).func_180627_b(p_180662_2_);
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176355_a, Integer.valueOf(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176355_a)).intValue();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176355_a});
   }
}
