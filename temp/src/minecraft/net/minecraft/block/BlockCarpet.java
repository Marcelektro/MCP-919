package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCarpet extends Block {
   public static final PropertyEnum<EnumDyeColor> field_176330_a = PropertyEnum.<EnumDyeColor>func_177709_a("color", EnumDyeColor.class);

   protected BlockCarpet() {
      super(Material.field_151593_r);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176330_a, EnumDyeColor.WHITE));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_150089_b(0);
   }

   public MapColor func_180659_g(IBlockState p_180659_1_) {
      return ((EnumDyeColor)p_180659_1_.func_177229_b(field_176330_a)).func_176768_e();
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_149683_g() {
      this.func_150089_b(0);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_150089_b(0);
   }

   protected void func_150089_b(int p_150089_1_) {
      int i = 0;
      float f = (float)(1 * (1 + i)) / 16.0F;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return super.func_176196_c(p_176196_1_, p_176196_2_) && this.func_176329_d(p_176196_1_, p_176196_2_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176328_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   private boolean func_176328_e(World p_176328_1_, BlockPos p_176328_2_, IBlockState p_176328_3_) {
      if(!this.func_176329_d(p_176328_1_, p_176328_2_)) {
         this.func_176226_b(p_176328_1_, p_176328_2_, p_176328_3_, 0);
         p_176328_1_.func_175698_g(p_176328_2_);
         return false;
      } else {
         return true;
      }
   }

   private boolean func_176329_d(World p_176329_1_, BlockPos p_176329_2_) {
      return !p_176329_1_.func_175623_d(p_176329_2_.func_177977_b());
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return p_176225_3_ == EnumFacing.UP?true:super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_);
   }

   public int func_180651_a(IBlockState p_180651_1_) {
      return ((EnumDyeColor)p_180651_1_.func_177229_b(field_176330_a)).func_176765_a();
   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      for(int i = 0; i < 16; ++i) {
         p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
      }

   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176330_a, EnumDyeColor.func_176764_b(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumDyeColor)p_176201_1_.func_177229_b(field_176330_a)).func_176765_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176330_a});
   }
}
