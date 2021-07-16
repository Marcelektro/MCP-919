package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCake extends Block {
   public static final PropertyInteger field_176589_a = PropertyInteger.func_177719_a("bites", 0, 6);

   protected BlockCake() {
      super(Material.field_151568_F);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176589_a, Integer.valueOf(0)));
      this.func_149675_a(true);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      float f = 0.0625F;
      float f1 = (float)(1 + ((Integer)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176589_a)).intValue() * 2) / 16.0F;
      float f2 = 0.5F;
      this.func_149676_a(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
   }

   public void func_149683_g() {
      float f = 0.0625F;
      float f1 = 0.5F;
      this.func_149676_a(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      float f = 0.0625F;
      float f1 = (float)(1 + ((Integer)p_180640_3_.func_177229_b(field_176589_a)).intValue() * 2) / 16.0F;
      float f2 = 0.5F;
      return new AxisAlignedBB((double)((float)p_180640_2_.func_177958_n() + f1), (double)p_180640_2_.func_177956_o(), (double)((float)p_180640_2_.func_177952_p() + f), (double)((float)(p_180640_2_.func_177958_n() + 1) - f), (double)((float)p_180640_2_.func_177956_o() + f2), (double)((float)(p_180640_2_.func_177952_p() + 1) - f));
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      return this.func_180640_a(p_180646_1_, p_180646_2_, p_180646_1_.func_180495_p(p_180646_2_));
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      this.func_180682_b(p_180639_1_, p_180639_2_, p_180639_3_, p_180639_4_);
      return true;
   }

   public void func_180649_a(World p_180649_1_, BlockPos p_180649_2_, EntityPlayer p_180649_3_) {
      this.func_180682_b(p_180649_1_, p_180649_2_, p_180649_1_.func_180495_p(p_180649_2_), p_180649_3_);
   }

   private void func_180682_b(World p_180682_1_, BlockPos p_180682_2_, IBlockState p_180682_3_, EntityPlayer p_180682_4_) {
      if(p_180682_4_.func_71043_e(false)) {
         p_180682_4_.func_71029_a(StatList.field_181724_H);
         p_180682_4_.func_71024_bL().func_75122_a(2, 0.1F);
         int i = ((Integer)p_180682_3_.func_177229_b(field_176589_a)).intValue();
         if(i < 6) {
            p_180682_1_.func_180501_a(p_180682_2_, p_180682_3_.func_177226_a(field_176589_a, Integer.valueOf(i + 1)), 3);
         } else {
            p_180682_1_.func_175698_g(p_180682_2_);
         }

      }
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return super.func_176196_c(p_176196_1_, p_176196_2_)?this.func_176588_d(p_176196_1_, p_176196_2_):false;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!this.func_176588_d(p_176204_1_, p_176204_2_)) {
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   private boolean func_176588_d(World p_176588_1_, BlockPos p_176588_2_) {
      return p_176588_1_.func_180495_p(p_176588_2_.func_177977_b()).func_177230_c().func_149688_o().func_76220_a();
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151105_aU;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176589_a, Integer.valueOf(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176589_a)).intValue();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176589_a});
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      return (7 - ((Integer)p_180641_1_.func_180495_p(p_180641_2_).func_177229_b(field_176589_a)).intValue()) * 2;
   }

   public boolean func_149740_M() {
      return true;
   }
}
