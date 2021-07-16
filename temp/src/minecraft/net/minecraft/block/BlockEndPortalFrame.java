package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEndPortalFrame extends Block {
   public static final PropertyDirection field_176508_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyBool field_176507_b = PropertyBool.func_177716_a("eye");

   public BlockEndPortalFrame() {
      super(Material.field_151576_e, MapColor.field_151651_C);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176508_a, EnumFacing.NORTH).func_177226_a(field_176507_b, Boolean.valueOf(false)));
   }

   public boolean func_149662_c() {
      return false;
   }

   public void func_149683_g() {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
   }

   public void func_180638_a(World p_180638_1_, BlockPos p_180638_2_, IBlockState p_180638_3_, AxisAlignedBB p_180638_4_, List<AxisAlignedBB> p_180638_5_, Entity p_180638_6_) {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      if(((Boolean)p_180638_1_.func_180495_p(p_180638_2_).func_177229_b(field_176507_b)).booleanValue()) {
         this.func_149676_a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
         super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      }

      this.func_149683_g();
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176508_a, p_180642_8_.func_174811_aO().func_176734_d()).func_177226_a(field_176507_b, Boolean.valueOf(false));
   }

   public boolean func_149740_M() {
      return true;
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      return ((Boolean)p_180641_1_.func_180495_p(p_180641_2_).func_177229_b(field_176507_b)).booleanValue()?15:0;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176507_b, Boolean.valueOf((p_176203_1_ & 4) != 0)).func_177226_a(field_176508_a, EnumFacing.func_176731_b(p_176203_1_ & 3));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176508_a)).func_176736_b();
      if(((Boolean)p_176201_1_.func_177229_b(field_176507_b)).booleanValue()) {
         i |= 4;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176508_a, field_176507_b});
   }
}
