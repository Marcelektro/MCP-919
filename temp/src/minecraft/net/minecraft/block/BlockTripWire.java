package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripWire extends Block {
   public static final PropertyBool field_176293_a = PropertyBool.func_177716_a("powered");
   public static final PropertyBool field_176290_b = PropertyBool.func_177716_a("suspended");
   public static final PropertyBool field_176294_M = PropertyBool.func_177716_a("attached");
   public static final PropertyBool field_176295_N = PropertyBool.func_177716_a("disarmed");
   public static final PropertyBool field_176296_O = PropertyBool.func_177716_a("north");
   public static final PropertyBool field_176291_P = PropertyBool.func_177716_a("east");
   public static final PropertyBool field_176289_Q = PropertyBool.func_177716_a("south");
   public static final PropertyBool field_176292_R = PropertyBool.func_177716_a("west");

   public BlockTripWire() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176293_a, Boolean.valueOf(false)).func_177226_a(field_176290_b, Boolean.valueOf(false)).func_177226_a(field_176294_M, Boolean.valueOf(false)).func_177226_a(field_176295_N, Boolean.valueOf(false)).func_177226_a(field_176296_O, Boolean.valueOf(false)).func_177226_a(field_176291_P, Boolean.valueOf(false)).func_177226_a(field_176289_Q, Boolean.valueOf(false)).func_177226_a(field_176292_R, Boolean.valueOf(false)));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
      this.func_149675_a(true);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      return p_176221_1_.func_177226_a(field_176296_O, Boolean.valueOf(func_176287_c(p_176221_2_, p_176221_3_, p_176221_1_, EnumFacing.NORTH))).func_177226_a(field_176291_P, Boolean.valueOf(func_176287_c(p_176221_2_, p_176221_3_, p_176221_1_, EnumFacing.EAST))).func_177226_a(field_176289_Q, Boolean.valueOf(func_176287_c(p_176221_2_, p_176221_3_, p_176221_1_, EnumFacing.SOUTH))).func_177226_a(field_176292_R, Boolean.valueOf(func_176287_c(p_176221_2_, p_176221_3_, p_176221_1_, EnumFacing.WEST)));
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

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151007_F;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151007_F;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      boolean flag = ((Boolean)p_176204_3_.func_177229_b(field_176290_b)).booleanValue();
      boolean flag1 = !World.func_175683_a(p_176204_1_, p_176204_2_.func_177977_b());
      if(flag != flag1) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState iblockstate = p_180654_1_.func_180495_p(p_180654_2_);
      boolean flag = ((Boolean)iblockstate.func_177229_b(field_176294_M)).booleanValue();
      boolean flag1 = ((Boolean)iblockstate.func_177229_b(field_176290_b)).booleanValue();
      if(!flag1) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
      } else if(!flag) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      } else {
         this.func_149676_a(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
      }

   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      p_176213_3_ = p_176213_3_.func_177226_a(field_176290_b, Boolean.valueOf(!World.func_175683_a(p_176213_1_, p_176213_2_.func_177977_b())));
      p_176213_1_.func_180501_a(p_176213_2_, p_176213_3_, 3);
      this.func_176286_e(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      this.func_176286_e(p_180663_1_, p_180663_2_, p_180663_3_.func_177226_a(field_176293_a, Boolean.valueOf(true)));
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      if(!p_176208_1_.field_72995_K) {
         if(p_176208_4_.func_71045_bC() != null && p_176208_4_.func_71045_bC().func_77973_b() == Items.field_151097_aZ) {
            p_176208_1_.func_180501_a(p_176208_2_, p_176208_3_.func_177226_a(field_176295_N, Boolean.valueOf(true)), 4);
         }

      }
   }

   private void func_176286_e(World p_176286_1_, BlockPos p_176286_2_, IBlockState p_176286_3_) {
      for(EnumFacing enumfacing : new EnumFacing[]{EnumFacing.SOUTH, EnumFacing.WEST}) {
         for(int i = 1; i < 42; ++i) {
            BlockPos blockpos = p_176286_2_.func_177967_a(enumfacing, i);
            IBlockState iblockstate = p_176286_1_.func_180495_p(blockpos);
            if(iblockstate.func_177230_c() == Blocks.field_150479_bC) {
               if(iblockstate.func_177229_b(BlockTripWireHook.field_176264_a) == enumfacing.func_176734_d()) {
                  Blocks.field_150479_bC.func_176260_a(p_176286_1_, blockpos, iblockstate, false, true, i, p_176286_3_);
               }
               break;
            }

            if(iblockstate.func_177230_c() != Blocks.field_150473_bD) {
               break;
            }
         }
      }

   }

   public void func_180634_a(World p_180634_1_, BlockPos p_180634_2_, IBlockState p_180634_3_, Entity p_180634_4_) {
      if(!p_180634_1_.field_72995_K) {
         if(!((Boolean)p_180634_3_.func_177229_b(field_176293_a)).booleanValue()) {
            this.func_176288_d(p_180634_1_, p_180634_2_);
         }
      }
   }

   public void func_180645_a(World p_180645_1_, BlockPos p_180645_2_, IBlockState p_180645_3_, Random p_180645_4_) {
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!p_180650_1_.field_72995_K) {
         if(((Boolean)p_180650_1_.func_180495_p(p_180650_2_).func_177229_b(field_176293_a)).booleanValue()) {
            this.func_176288_d(p_180650_1_, p_180650_2_);
         }
      }
   }

   private void func_176288_d(World p_176288_1_, BlockPos p_176288_2_) {
      IBlockState iblockstate = p_176288_1_.func_180495_p(p_176288_2_);
      boolean flag = ((Boolean)iblockstate.func_177229_b(field_176293_a)).booleanValue();
      boolean flag1 = false;
      List<? extends Entity> list = p_176288_1_.func_72839_b((Entity)null, new AxisAlignedBB((double)p_176288_2_.func_177958_n() + this.field_149759_B, (double)p_176288_2_.func_177956_o() + this.field_149760_C, (double)p_176288_2_.func_177952_p() + this.field_149754_D, (double)p_176288_2_.func_177958_n() + this.field_149755_E, (double)p_176288_2_.func_177956_o() + this.field_149756_F, (double)p_176288_2_.func_177952_p() + this.field_149757_G));
      if(!list.isEmpty()) {
         for(Entity entity : list) {
            if(!entity.func_145773_az()) {
               flag1 = true;
               break;
            }
         }
      }

      if(flag1 != flag) {
         iblockstate = iblockstate.func_177226_a(field_176293_a, Boolean.valueOf(flag1));
         p_176288_1_.func_180501_a(p_176288_2_, iblockstate, 3);
         this.func_176286_e(p_176288_1_, p_176288_2_, iblockstate);
      }

      if(flag1) {
         p_176288_1_.func_175684_a(p_176288_2_, this, this.func_149738_a(p_176288_1_));
      }

   }

   public static boolean func_176287_c(IBlockAccess p_176287_0_, BlockPos p_176287_1_, IBlockState p_176287_2_, EnumFacing p_176287_3_) {
      BlockPos blockpos = p_176287_1_.func_177972_a(p_176287_3_);
      IBlockState iblockstate = p_176287_0_.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      if(block == Blocks.field_150479_bC) {
         EnumFacing enumfacing = p_176287_3_.func_176734_d();
         return iblockstate.func_177229_b(BlockTripWireHook.field_176264_a) == enumfacing;
      } else if(block == Blocks.field_150473_bD) {
         boolean flag = ((Boolean)p_176287_2_.func_177229_b(field_176290_b)).booleanValue();
         boolean flag1 = ((Boolean)iblockstate.func_177229_b(field_176290_b)).booleanValue();
         return flag == flag1;
      } else {
         return false;
      }
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176293_a, Boolean.valueOf((p_176203_1_ & 1) > 0)).func_177226_a(field_176290_b, Boolean.valueOf((p_176203_1_ & 2) > 0)).func_177226_a(field_176294_M, Boolean.valueOf((p_176203_1_ & 4) > 0)).func_177226_a(field_176295_N, Boolean.valueOf((p_176203_1_ & 8) > 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      if(((Boolean)p_176201_1_.func_177229_b(field_176293_a)).booleanValue()) {
         i |= 1;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176290_b)).booleanValue()) {
         i |= 2;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176294_M)).booleanValue()) {
         i |= 4;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176295_N)).booleanValue()) {
         i |= 8;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176293_a, field_176290_b, field_176294_M, field_176295_N, field_176296_O, field_176291_P, field_176292_R, field_176289_Q});
   }
}
