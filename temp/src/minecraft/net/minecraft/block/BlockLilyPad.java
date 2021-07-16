package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad extends BlockBush {
   protected BlockLilyPad() {
      float f = 0.5F;
      float f1 = 0.015625F;
      this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public void func_180638_a(World p_180638_1_, BlockPos p_180638_2_, IBlockState p_180638_3_, AxisAlignedBB p_180638_4_, List<AxisAlignedBB> p_180638_5_, Entity p_180638_6_) {
      if(p_180638_6_ == null || !(p_180638_6_ instanceof EntityBoat)) {
         super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      }

   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return new AxisAlignedBB((double)p_180640_2_.func_177958_n() + this.field_149759_B, (double)p_180640_2_.func_177956_o() + this.field_149760_C, (double)p_180640_2_.func_177952_p() + this.field_149754_D, (double)p_180640_2_.func_177958_n() + this.field_149755_E, (double)p_180640_2_.func_177956_o() + this.field_149756_F, (double)p_180640_2_.func_177952_p() + this.field_149757_G);
   }

   public int func_149635_D() {
      return 7455580;
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      return 7455580;
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return 2129968;
   }

   protected boolean func_149854_a(Block p_149854_1_) {
      return p_149854_1_ == Blocks.field_150355_j;
   }

   public boolean func_180671_f(World p_180671_1_, BlockPos p_180671_2_, IBlockState p_180671_3_) {
      if(p_180671_2_.func_177956_o() >= 0 && p_180671_2_.func_177956_o() < 256) {
         IBlockState iblockstate = p_180671_1_.func_180495_p(p_180671_2_.func_177977_b());
         return iblockstate.func_177230_c().func_149688_o() == Material.field_151586_h && ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue() == 0;
      } else {
         return false;
      }
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return 0;
   }
}
