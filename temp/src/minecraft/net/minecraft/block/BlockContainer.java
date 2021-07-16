package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockContainer extends Block implements ITileEntityProvider {
   protected BlockContainer(Material p_i45386_1_) {
      this(p_i45386_1_, p_i45386_1_.func_151565_r());
   }

   protected BlockContainer(Material p_i46402_1_, MapColor p_i46402_2_) {
      super(p_i46402_1_, p_i46402_2_);
      this.field_149758_A = true;
   }

   protected boolean func_181086_a(World p_181086_1_, BlockPos p_181086_2_, EnumFacing p_181086_3_) {
      return p_181086_1_.func_180495_p(p_181086_2_.func_177972_a(p_181086_3_)).func_177230_c().func_149688_o() == Material.field_151570_A;
   }

   protected boolean func_181087_e(World p_181087_1_, BlockPos p_181087_2_) {
      return this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.NORTH) || this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.SOUTH) || this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.WEST) || this.func_181086_a(p_181087_1_, p_181087_2_, EnumFacing.EAST);
   }

   public int func_149645_b() {
      return -1;
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      p_180663_1_.func_175713_t(p_180663_2_);
   }

   public boolean func_180648_a(World p_180648_1_, BlockPos p_180648_2_, IBlockState p_180648_3_, int p_180648_4_, int p_180648_5_) {
      super.func_180648_a(p_180648_1_, p_180648_2_, p_180648_3_, p_180648_4_, p_180648_5_);
      TileEntity tileentity = p_180648_1_.func_175625_s(p_180648_2_);
      return tileentity == null?false:tileentity.func_145842_c(p_180648_4_, p_180648_5_);
   }
}
