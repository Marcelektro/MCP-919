package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BlockDirectional implements IGrowable {
   public static final PropertyInteger field_176501_a = PropertyInteger.func_177719_a("age", 0, 2);

   public BlockCocoa() {
      super(Material.field_151585_k);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176387_N, EnumFacing.NORTH).func_177226_a(field_176501_a, Integer.valueOf(0)));
      this.func_149675_a(true);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!this.func_176499_e(p_180650_1_, p_180650_2_, p_180650_3_)) {
         this.func_176500_f(p_180650_1_, p_180650_2_, p_180650_3_);
      } else if(p_180650_1_.field_73012_v.nextInt(5) == 0) {
         int i = ((Integer)p_180650_3_.func_177229_b(field_176501_a)).intValue();
         if(i < 2) {
            p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176501_a, Integer.valueOf(i + 1)), 2);
         }
      }

   }

   public boolean func_176499_e(World p_176499_1_, BlockPos p_176499_2_, IBlockState p_176499_3_) {
      p_176499_2_ = p_176499_2_.func_177972_a((EnumFacing)p_176499_3_.func_177229_b(field_176387_N));
      IBlockState iblockstate = p_176499_1_.func_180495_p(p_176499_2_);
      return iblockstate.func_177230_c() == Blocks.field_150364_r && iblockstate.func_177229_b(BlockPlanks.field_176383_a) == BlockPlanks.EnumType.JUNGLE;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      this.func_180654_a(p_180646_1_, p_180646_2_);
      return super.func_180646_a(p_180646_1_, p_180646_2_);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState iblockstate = p_180654_1_.func_180495_p(p_180654_2_);
      EnumFacing enumfacing = (EnumFacing)iblockstate.func_177229_b(field_176387_N);
      int i = ((Integer)iblockstate.func_177229_b(field_176501_a)).intValue();
      int j = 4 + i * 2;
      int k = 5 + i * 2;
      float f = (float)j / 2.0F;
      switch(enumfacing) {
      case SOUTH:
         this.func_149676_a((8.0F - f) / 16.0F, (12.0F - (float)k) / 16.0F, (15.0F - (float)j) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
         break;
      case NORTH:
         this.func_149676_a((8.0F - f) / 16.0F, (12.0F - (float)k) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + (float)j) / 16.0F);
         break;
      case WEST:
         this.func_149676_a(0.0625F, (12.0F - (float)k) / 16.0F, (8.0F - f) / 16.0F, (1.0F + (float)j) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
         break;
      case EAST:
         this.func_149676_a((15.0F - (float)j) / 16.0F, (12.0F - (float)k) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
      }

   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      EnumFacing enumfacing = EnumFacing.func_176733_a((double)p_180633_4_.field_70177_z);
      p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_.func_177226_a(field_176387_N, enumfacing), 2);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      if(!p_180642_3_.func_176740_k().func_176722_c()) {
         p_180642_3_ = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176387_N, p_180642_3_.func_176734_d()).func_177226_a(field_176501_a, Integer.valueOf(0));
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!this.func_176499_e(p_176204_1_, p_176204_2_, p_176204_3_)) {
         this.func_176500_f(p_176204_1_, p_176204_2_, p_176204_3_);
      }

   }

   private void func_176500_f(World p_176500_1_, BlockPos p_176500_2_, IBlockState p_176500_3_) {
      p_176500_1_.func_180501_a(p_176500_2_, Blocks.field_150350_a.func_176223_P(), 3);
      this.func_176226_b(p_176500_1_, p_176500_2_, p_176500_3_, 0);
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      int i = ((Integer)p_180653_3_.func_177229_b(field_176501_a)).intValue();
      int j = 1;
      if(i >= 2) {
         j = 3;
      }

      for(int k = 0; k < j; ++k) {
         func_180635_a(p_180653_1_, p_180653_2_, new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BROWN.func_176767_b()));
      }

   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151100_aR;
   }

   public int func_176222_j(World p_176222_1_, BlockPos p_176222_2_) {
      return EnumDyeColor.BROWN.func_176767_b();
   }

   public boolean func_176473_a(World p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
      return ((Integer)p_176473_3_.func_177229_b(field_176501_a)).intValue() < 2;
   }

   public boolean func_180670_a(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
      return true;
   }

   public void func_176474_b(World p_176474_1_, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
      p_176474_1_.func_180501_a(p_176474_3_, p_176474_4_.func_177226_a(field_176501_a, Integer.valueOf(((Integer)p_176474_4_.func_177229_b(field_176501_a)).intValue() + 1)), 2);
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176387_N, EnumFacing.func_176731_b(p_176203_1_)).func_177226_a(field_176501_a, Integer.valueOf((p_176203_1_ & 15) >> 2));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176387_N)).func_176736_b();
      i = i | ((Integer)p_176201_1_.func_177229_b(field_176501_a)).intValue() << 2;
      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176387_N, field_176501_a});
   }
}
