package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockBush extends Block {
   protected BlockBush() {
      this(Material.field_151585_k);
   }

   protected BlockBush(Material p_i45395_1_) {
      this(p_i45395_1_, p_i45395_1_.func_151565_r());
   }

   protected BlockBush(Material p_i46452_1_, MapColor p_i46452_2_) {
      super(p_i46452_1_, p_i46452_2_);
      this.func_149675_a(true);
      float f = 0.2F;
      this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return super.func_176196_c(p_176196_1_, p_176196_2_) && this.func_149854_a(p_176196_1_.func_180495_p(p_176196_2_.func_177977_b()).func_177230_c());
   }

   protected boolean func_149854_a(Block p_149854_1_) {
      return p_149854_1_ == Blocks.field_150349_c || p_149854_1_ == Blocks.field_150346_d || p_149854_1_ == Blocks.field_150458_ak;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      this.func_176475_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      this.func_176475_e(p_180650_1_, p_180650_2_, p_180650_3_);
   }

   protected void func_176475_e(World p_176475_1_, BlockPos p_176475_2_, IBlockState p_176475_3_) {
      if(!this.func_180671_f(p_176475_1_, p_176475_2_, p_176475_3_)) {
         this.func_176226_b(p_176475_1_, p_176475_2_, p_176475_3_, 0);
         p_176475_1_.func_180501_a(p_176475_2_, Blocks.field_150350_a.func_176223_P(), 3);
      }

   }

   public boolean func_180671_f(World p_180671_1_, BlockPos p_180671_2_, IBlockState p_180671_3_) {
      return this.func_149854_a(p_180671_1_.func_180495_p(p_180671_2_.func_177977_b()).func_177230_c());
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }
}
