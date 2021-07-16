package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockLeavesBase extends Block {
   protected boolean field_150121_P;

   protected BlockLeavesBase(Material p_i45433_1_, boolean p_i45433_2_) {
      super(p_i45433_1_);
      this.field_150121_P = p_i45433_2_;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return !this.field_150121_P && p_176225_1_.func_180495_p(p_176225_2_).func_177230_c() == this?false:super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_);
   }
}
