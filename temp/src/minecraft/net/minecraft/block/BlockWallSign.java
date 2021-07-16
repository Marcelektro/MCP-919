package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSign;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallSign extends BlockSign {
   public static final PropertyDirection field_176412_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);

   public BlockWallSign() {
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176412_a, EnumFacing.NORTH));
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      EnumFacing enumfacing = (EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176412_a);
      float f = 0.28125F;
      float f1 = 0.78125F;
      float f2 = 0.0F;
      float f3 = 1.0F;
      float f4 = 0.125F;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      switch(enumfacing) {
      case NORTH:
         this.func_149676_a(f2, f, 1.0F - f4, f3, f1, 1.0F);
         break;
      case SOUTH:
         this.func_149676_a(f2, f, 0.0F, f3, f1, f4);
         break;
      case WEST:
         this.func_149676_a(1.0F - f4, f, f2, 1.0F, f1, f3);
         break;
      case EAST:
         this.func_149676_a(0.0F, f, f2, f4, f1, f3);
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      EnumFacing enumfacing = (EnumFacing)p_176204_3_.func_177229_b(field_176412_a);
      if(!p_176204_1_.func_180495_p(p_176204_2_.func_177972_a(enumfacing.func_176734_d())).func_177230_c().func_149688_o().func_76220_a()) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_176203_1_);
      if(enumfacing.func_176740_k() == EnumFacing.Axis.Y) {
         enumfacing = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176412_a, enumfacing);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176412_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176412_a});
   }
}
