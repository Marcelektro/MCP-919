package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnow extends Block {
   public static final PropertyInteger field_176315_a = PropertyInteger.func_177719_a("layers", 1, 8);

   protected BlockSnow() {
      super(Material.field_151597_y);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176315_a, Integer.valueOf(1)));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_149683_g();
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return ((Integer)p_176205_1_.func_180495_p(p_176205_2_).func_177229_b(field_176315_a)).intValue() < 5;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      int i = ((Integer)p_180640_3_.func_177229_b(field_176315_a)).intValue() - 1;
      float f = 0.125F;
      return new AxisAlignedBB((double)p_180640_2_.func_177958_n() + this.field_149759_B, (double)p_180640_2_.func_177956_o() + this.field_149760_C, (double)p_180640_2_.func_177952_p() + this.field_149754_D, (double)p_180640_2_.func_177958_n() + this.field_149755_E, (double)((float)p_180640_2_.func_177956_o() + (float)i * f), (double)p_180640_2_.func_177952_p() + this.field_149757_G);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_149683_g() {
      this.func_150154_b(0);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState iblockstate = p_180654_1_.func_180495_p(p_180654_2_);
      this.func_150154_b(((Integer)iblockstate.func_177229_b(field_176315_a)).intValue());
   }

   protected void func_150154_b(int p_150154_1_) {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, (float)p_150154_1_ / 8.0F, 1.0F);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      IBlockState iblockstate = p_176196_1_.func_180495_p(p_176196_2_.func_177977_b());
      Block block = iblockstate.func_177230_c();
      return block != Blocks.field_150432_aD && block != Blocks.field_150403_cj?(block.func_149688_o() == Material.field_151584_j?true:(block == this && ((Integer)iblockstate.func_177229_b(field_176315_a)).intValue() >= 7?true:block.func_149662_c() && block.field_149764_J.func_76230_c())):false;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176314_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   private boolean func_176314_e(World p_176314_1_, BlockPos p_176314_2_, IBlockState p_176314_3_) {
      if(!this.func_176196_c(p_176314_1_, p_176314_2_)) {
         this.func_176226_b(p_176314_1_, p_176314_2_, p_176314_3_, 0);
         p_176314_1_.func_175698_g(p_176314_2_);
         return false;
      } else {
         return true;
      }
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_) {
      func_180635_a(p_180657_1_, p_180657_3_, new ItemStack(Items.field_151126_ay, ((Integer)p_180657_4_.func_177229_b(field_176315_a)).intValue() + 1, 0));
      p_180657_1_.func_175698_g(p_180657_3_);
      p_180657_2_.func_71029_a(StatList.field_75934_C[Block.func_149682_b(this)]);
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151126_ay;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(p_180650_1_.func_175642_b(EnumSkyBlock.BLOCK, p_180650_2_) > 11) {
         this.func_176226_b(p_180650_1_, p_180650_2_, p_180650_1_.func_180495_p(p_180650_2_), 0);
         p_180650_1_.func_175698_g(p_180650_2_);
      }

   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return p_176225_3_ == EnumFacing.UP?true:super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176315_a, Integer.valueOf((p_176203_1_ & 7) + 1));
   }

   public boolean func_176200_f(World p_176200_1_, BlockPos p_176200_2_) {
      return ((Integer)p_176200_1_.func_180495_p(p_176200_2_).func_177229_b(field_176315_a)).intValue() == 1;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176315_a)).intValue() - 1;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176315_a});
   }
}
