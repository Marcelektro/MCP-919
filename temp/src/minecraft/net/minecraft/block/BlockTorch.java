package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockTorch extends Block {
   public static final PropertyDirection field_176596_a = PropertyDirection.func_177712_a("facing", new Predicate<EnumFacing>() {
      public boolean apply(EnumFacing p_apply_1_) {
         return p_apply_1_ != EnumFacing.DOWN;
      }
   });

   protected BlockTorch() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176596_a, EnumFacing.UP));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
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

   private boolean func_176594_d(World p_176594_1_, BlockPos p_176594_2_) {
      if(World.func_175683_a(p_176594_1_, p_176594_2_)) {
         return true;
      } else {
         Block block = p_176594_1_.func_180495_p(p_176594_2_).func_177230_c();
         return block instanceof BlockFence || block == Blocks.field_150359_w || block == Blocks.field_150463_bK || block == Blocks.field_150399_cn;
      }
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      for(EnumFacing enumfacing : field_176596_a.func_177700_c()) {
         if(this.func_176595_b(p_176196_1_, p_176196_2_, enumfacing)) {
            return true;
         }
      }

      return false;
   }

   private boolean func_176595_b(World p_176595_1_, BlockPos p_176595_2_, EnumFacing p_176595_3_) {
      BlockPos blockpos = p_176595_2_.func_177972_a(p_176595_3_.func_176734_d());
      boolean flag = p_176595_3_.func_176740_k().func_176722_c();
      return flag && p_176595_1_.func_175677_d(blockpos, true) || p_176595_3_.equals(EnumFacing.UP) && this.func_176594_d(p_176595_1_, blockpos);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      if(this.func_176595_b(p_180642_1_, p_180642_2_, p_180642_3_)) {
         return this.func_176223_P().func_177226_a(field_176596_a, p_180642_3_);
      } else {
         for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            if(p_180642_1_.func_175677_d(p_180642_2_.func_177972_a(enumfacing.func_176734_d()), true)) {
               return this.func_176223_P().func_177226_a(field_176596_a, enumfacing);
            }
         }

         return this.func_176223_P();
      }
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176593_f(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176592_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   protected boolean func_176592_e(World p_176592_1_, BlockPos p_176592_2_, IBlockState p_176592_3_) {
      if(!this.func_176593_f(p_176592_1_, p_176592_2_, p_176592_3_)) {
         return true;
      } else {
         EnumFacing enumfacing = (EnumFacing)p_176592_3_.func_177229_b(field_176596_a);
         EnumFacing.Axis enumfacing$axis = enumfacing.func_176740_k();
         EnumFacing enumfacing1 = enumfacing.func_176734_d();
         boolean flag = false;
         if(enumfacing$axis.func_176722_c() && !p_176592_1_.func_175677_d(p_176592_2_.func_177972_a(enumfacing1), true)) {
            flag = true;
         } else if(enumfacing$axis.func_176720_b() && !this.func_176594_d(p_176592_1_, p_176592_2_.func_177972_a(enumfacing1))) {
            flag = true;
         }

         if(flag) {
            this.func_176226_b(p_176592_1_, p_176592_2_, p_176592_3_, 0);
            p_176592_1_.func_175698_g(p_176592_2_);
            return true;
         } else {
            return false;
         }
      }
   }

   protected boolean func_176593_f(World p_176593_1_, BlockPos p_176593_2_, IBlockState p_176593_3_) {
      if(p_176593_3_.func_177230_c() == this && this.func_176595_b(p_176593_1_, p_176593_2_, (EnumFacing)p_176593_3_.func_177229_b(field_176596_a))) {
         return true;
      } else {
         if(p_176593_1_.func_180495_p(p_176593_2_).func_177230_c() == this) {
            this.func_176226_b(p_176593_1_, p_176593_2_, p_176593_3_, 0);
            p_176593_1_.func_175698_g(p_176593_2_);
         }

         return false;
      }
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      EnumFacing enumfacing = (EnumFacing)p_180636_1_.func_180495_p(p_180636_2_).func_177229_b(field_176596_a);
      float f = 0.15F;
      if(enumfacing == EnumFacing.EAST) {
         this.func_149676_a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
      } else if(enumfacing == EnumFacing.WEST) {
         this.func_149676_a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
      } else if(enumfacing == EnumFacing.SOUTH) {
         this.func_149676_a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
      } else if(enumfacing == EnumFacing.NORTH) {
         this.func_149676_a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
      } else {
         f = 0.1F;
         this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
      }

      return super.func_180636_a(p_180636_1_, p_180636_2_, p_180636_3_, p_180636_4_);
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      EnumFacing enumfacing = (EnumFacing)p_180655_3_.func_177229_b(field_176596_a);
      double d0 = (double)p_180655_2_.func_177958_n() + 0.5D;
      double d1 = (double)p_180655_2_.func_177956_o() + 0.7D;
      double d2 = (double)p_180655_2_.func_177952_p() + 0.5D;
      double d3 = 0.22D;
      double d4 = 0.27D;
      if(enumfacing.func_176740_k().func_176722_c()) {
         EnumFacing enumfacing1 = enumfacing.func_176734_d();
         p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 * (double)enumfacing1.func_82601_c(), d1 + d3, d2 + d4 * (double)enumfacing1.func_82599_e(), 0.0D, 0.0D, 0.0D, new int[0]);
         p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, d0 + d4 * (double)enumfacing1.func_82601_c(), d1 + d3, d2 + d4 * (double)enumfacing1.func_82599_e(), 0.0D, 0.0D, 0.0D, new int[0]);
      } else {
         p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
         p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      IBlockState iblockstate = this.func_176223_P();
      switch(p_176203_1_) {
      case 1:
         iblockstate = iblockstate.func_177226_a(field_176596_a, EnumFacing.EAST);
         break;
      case 2:
         iblockstate = iblockstate.func_177226_a(field_176596_a, EnumFacing.WEST);
         break;
      case 3:
         iblockstate = iblockstate.func_177226_a(field_176596_a, EnumFacing.SOUTH);
         break;
      case 4:
         iblockstate = iblockstate.func_177226_a(field_176596_a, EnumFacing.NORTH);
         break;
      case 5:
      default:
         iblockstate = iblockstate.func_177226_a(field_176596_a, EnumFacing.UP);
      }

      return iblockstate;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      switch((EnumFacing)p_176201_1_.func_177229_b(field_176596_a)) {
      case EAST:
         i = i | 1;
         break;
      case WEST:
         i = i | 2;
         break;
      case SOUTH:
         i = i | 3;
         break;
      case NORTH:
         i = i | 4;
         break;
      case DOWN:
      case UP:
      default:
         i = i | 5;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176596_a});
   }
}
