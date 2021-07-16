package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBasePressurePlate extends Block {
   protected BlockBasePressurePlate(Material p_i45740_1_) {
      this(p_i45740_1_, p_i45740_1_.func_151565_r());
   }

   protected BlockBasePressurePlate(Material p_i46401_1_, MapColor p_i46401_2_) {
      super(p_i46401_1_, p_i46401_2_);
      this.func_149647_a(CreativeTabs.field_78028_d);
      this.func_149675_a(true);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_180668_d(p_180654_1_.func_180495_p(p_180654_2_));
   }

   protected void func_180668_d(IBlockState p_180668_1_) {
      boolean flag = this.func_176576_e(p_180668_1_) > 0;
      float f = 0.0625F;
      if(flag) {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.03125F, 0.9375F);
      } else {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.0625F, 0.9375F);
      }

   }

   public int func_149738_a(World p_149738_1_) {
      return 20;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return true;
   }

   public boolean func_181623_g() {
      return true;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return this.func_176577_m(p_176196_1_, p_176196_2_.func_177977_b());
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!this.func_176577_m(p_176204_1_, p_176204_2_.func_177977_b())) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   private boolean func_176577_m(World p_176577_1_, BlockPos p_176577_2_) {
      return World.func_175683_a(p_176577_1_, p_176577_2_) || p_176577_1_.func_180495_p(p_176577_2_).func_177230_c() instanceof BlockFence;
   }

   public void func_180645_a(World p_180645_1_, BlockPos p_180645_2_, IBlockState p_180645_3_, Random p_180645_4_) {
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!p_180650_1_.field_72995_K) {
         int i = this.func_176576_e(p_180650_3_);
         if(i > 0) {
            this.func_180666_a(p_180650_1_, p_180650_2_, p_180650_3_, i);
         }

      }
   }

   public void func_180634_a(World p_180634_1_, BlockPos p_180634_2_, IBlockState p_180634_3_, Entity p_180634_4_) {
      if(!p_180634_1_.field_72995_K) {
         int i = this.func_176576_e(p_180634_3_);
         if(i == 0) {
            this.func_180666_a(p_180634_1_, p_180634_2_, p_180634_3_, i);
         }

      }
   }

   protected void func_180666_a(World p_180666_1_, BlockPos p_180666_2_, IBlockState p_180666_3_, int p_180666_4_) {
      int i = this.func_180669_e(p_180666_1_, p_180666_2_);
      boolean flag = p_180666_4_ > 0;
      boolean flag1 = i > 0;
      if(p_180666_4_ != i) {
         p_180666_3_ = this.func_176575_a(p_180666_3_, i);
         p_180666_1_.func_180501_a(p_180666_2_, p_180666_3_, 2);
         this.func_176578_d(p_180666_1_, p_180666_2_);
         p_180666_1_.func_175704_b(p_180666_2_, p_180666_2_);
      }

      if(!flag1 && flag) {
         p_180666_1_.func_72908_a((double)p_180666_2_.func_177958_n() + 0.5D, (double)p_180666_2_.func_177956_o() + 0.1D, (double)p_180666_2_.func_177952_p() + 0.5D, "random.click", 0.3F, 0.5F);
      } else if(flag1 && !flag) {
         p_180666_1_.func_72908_a((double)p_180666_2_.func_177958_n() + 0.5D, (double)p_180666_2_.func_177956_o() + 0.1D, (double)p_180666_2_.func_177952_p() + 0.5D, "random.click", 0.3F, 0.6F);
      }

      if(flag1) {
         p_180666_1_.func_175684_a(p_180666_2_, this, this.func_149738_a(p_180666_1_));
      }

   }

   protected AxisAlignedBB func_180667_a(BlockPos p_180667_1_) {
      float f = 0.125F;
      return new AxisAlignedBB((double)((float)p_180667_1_.func_177958_n() + 0.125F), (double)p_180667_1_.func_177956_o(), (double)((float)p_180667_1_.func_177952_p() + 0.125F), (double)((float)(p_180667_1_.func_177958_n() + 1) - 0.125F), (double)p_180667_1_.func_177956_o() + 0.25D, (double)((float)(p_180667_1_.func_177952_p() + 1) - 0.125F));
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      if(this.func_176576_e(p_180663_3_) > 0) {
         this.func_176578_d(p_180663_1_, p_180663_2_);
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   protected void func_176578_d(World p_176578_1_, BlockPos p_176578_2_) {
      p_176578_1_.func_175685_c(p_176578_2_, this);
      p_176578_1_.func_175685_c(p_176578_2_.func_177977_b(), this);
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      return this.func_176576_e(p_180656_3_);
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      return p_176211_4_ == EnumFacing.UP?this.func_176576_e(p_176211_3_):0;
   }

   public boolean func_149744_f() {
      return true;
   }

   public void func_149683_g() {
      float f = 0.5F;
      float f1 = 0.125F;
      float f2 = 0.5F;
      this.func_149676_a(0.0F, 0.375F, 0.0F, 1.0F, 0.625F, 1.0F);
   }

   public int func_149656_h() {
      return 1;
   }

   protected abstract int func_180669_e(World var1, BlockPos var2);

   protected abstract int func_176576_e(IBlockState var1);

   protected abstract IBlockState func_176575_a(IBlockState var1, int var2);
}
