package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSoulSand extends Block {
   public BlockSoulSand() {
      super(Material.field_151595_p, MapColor.field_151650_B);
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      float f = 0.125F;
      return new AxisAlignedBB((double)p_180640_2_.func_177958_n(), (double)p_180640_2_.func_177956_o(), (double)p_180640_2_.func_177952_p(), (double)(p_180640_2_.func_177958_n() + 1), (double)((float)(p_180640_2_.func_177956_o() + 1) - f), (double)(p_180640_2_.func_177952_p() + 1));
   }

   public void func_180634_a(World p_180634_1_, BlockPos p_180634_2_, IBlockState p_180634_3_, Entity p_180634_4_) {
      p_180634_4_.field_70159_w *= 0.4D;
      p_180634_4_.field_70179_y *= 0.4D;
   }
}
