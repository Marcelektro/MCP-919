package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockBreakable extends Block {
   private boolean field_149996_a;

   protected BlockBreakable(Material p_i45712_1_, boolean p_i45712_2_) {
      this(p_i45712_1_, p_i45712_2_, p_i45712_1_.func_151565_r());
   }

   protected BlockBreakable(Material p_i46393_1_, boolean p_i46393_2_, MapColor p_i46393_3_) {
      super(p_i46393_1_, p_i46393_3_);
      this.field_149996_a = p_i46393_2_;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      IBlockState iblockstate = p_176225_1_.func_180495_p(p_176225_2_);
      Block block = iblockstate.func_177230_c();
      if(this == Blocks.field_150359_w || this == Blocks.field_150399_cn) {
         if(p_176225_1_.func_180495_p(p_176225_2_.func_177972_a(p_176225_3_.func_176734_d())) != iblockstate) {
            return true;
         }

         if(block == this) {
            return false;
         }
      }

      return !this.field_149996_a && block == this?false:super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_);
   }
}
