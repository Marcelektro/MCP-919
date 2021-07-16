package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonMoving extends BlockContainer {
   public static final PropertyDirection field_176426_a = BlockPistonExtension.field_176326_a;
   public static final PropertyEnum<BlockPistonExtension.EnumPistonType> field_176425_b = BlockPistonExtension.field_176325_b;

   public BlockPistonMoving() {
      super(Material.field_76233_E);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176426_a, EnumFacing.NORTH).func_177226_a(field_176425_b, BlockPistonExtension.EnumPistonType.DEFAULT));
      this.func_149711_c(-1.0F);
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return null;
   }

   public static TileEntity func_176423_a(IBlockState p_176423_0_, EnumFacing p_176423_1_, boolean p_176423_2_, boolean p_176423_3_) {
      return new TileEntityPiston(p_176423_0_, p_176423_1_, p_176423_2_, p_176423_3_);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      TileEntity tileentity = p_180663_1_.func_175625_s(p_180663_2_);
      if(tileentity instanceof TileEntityPiston) {
         ((TileEntityPiston)tileentity).func_145866_f();
      } else {
         super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      }

   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return false;
   }

   public boolean func_176198_a(World p_176198_1_, BlockPos p_176198_2_, EnumFacing p_176198_3_) {
      return false;
   }

   public void func_176206_d(World p_176206_1_, BlockPos p_176206_2_, IBlockState p_176206_3_) {
      BlockPos blockpos = p_176206_2_.func_177972_a(((EnumFacing)p_176206_3_.func_177229_b(field_176426_a)).func_176734_d());
      IBlockState iblockstate = p_176206_1_.func_180495_p(blockpos);
      if(iblockstate.func_177230_c() instanceof BlockPistonBase && ((Boolean)iblockstate.func_177229_b(BlockPistonBase.field_176320_b)).booleanValue()) {
         p_176206_1_.func_175698_g(blockpos);
      }

   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(!p_180639_1_.field_72995_K && p_180639_1_.func_175625_s(p_180639_2_) == null) {
         p_180639_1_.func_175698_g(p_180639_2_);
         return true;
      } else {
         return false;
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      if(!p_180653_1_.field_72995_K) {
         TileEntityPiston tileentitypiston = this.func_176422_e(p_180653_1_, p_180653_2_);
         if(tileentitypiston != null) {
            IBlockState iblockstate = tileentitypiston.func_174927_b();
            iblockstate.func_177230_c().func_176226_b(p_180653_1_, p_180653_2_, iblockstate, 0);
         }
      }
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      return null;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         p_176204_1_.func_175625_s(p_176204_2_);
      }

   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      TileEntityPiston tileentitypiston = this.func_176422_e(p_180640_1_, p_180640_2_);
      if(tileentitypiston == null) {
         return null;
      } else {
         float f = tileentitypiston.func_145860_a(0.0F);
         if(tileentitypiston.func_145868_b()) {
            f = 1.0F - f;
         }

         return this.func_176424_a(p_180640_1_, p_180640_2_, tileentitypiston.func_174927_b(), f, tileentitypiston.func_174930_e());
      }
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      TileEntityPiston tileentitypiston = this.func_176422_e(p_180654_1_, p_180654_2_);
      if(tileentitypiston != null) {
         IBlockState iblockstate = tileentitypiston.func_174927_b();
         Block block = iblockstate.func_177230_c();
         if(block == this || block.func_149688_o() == Material.field_151579_a) {
            return;
         }

         float f = tileentitypiston.func_145860_a(0.0F);
         if(tileentitypiston.func_145868_b()) {
            f = 1.0F - f;
         }

         block.func_180654_a(p_180654_1_, p_180654_2_);
         if(block == Blocks.field_150331_J || block == Blocks.field_150320_F) {
            f = 0.0F;
         }

         EnumFacing enumfacing = tileentitypiston.func_174930_e();
         this.field_149759_B = block.func_149704_x() - (double)((float)enumfacing.func_82601_c() * f);
         this.field_149760_C = block.func_149665_z() - (double)((float)enumfacing.func_96559_d() * f);
         this.field_149754_D = block.func_149706_B() - (double)((float)enumfacing.func_82599_e() * f);
         this.field_149755_E = block.func_149753_y() - (double)((float)enumfacing.func_82601_c() * f);
         this.field_149756_F = block.func_149669_A() - (double)((float)enumfacing.func_96559_d() * f);
         this.field_149757_G = block.func_149693_C() - (double)((float)enumfacing.func_82599_e() * f);
      }

   }

   public AxisAlignedBB func_176424_a(World p_176424_1_, BlockPos p_176424_2_, IBlockState p_176424_3_, float p_176424_4_, EnumFacing p_176424_5_) {
      if(p_176424_3_.func_177230_c() != this && p_176424_3_.func_177230_c().func_149688_o() != Material.field_151579_a) {
         AxisAlignedBB axisalignedbb = p_176424_3_.func_177230_c().func_180640_a(p_176424_1_, p_176424_2_, p_176424_3_);
         if(axisalignedbb == null) {
            return null;
         } else {
            double d0 = axisalignedbb.field_72340_a;
            double d1 = axisalignedbb.field_72338_b;
            double d2 = axisalignedbb.field_72339_c;
            double d3 = axisalignedbb.field_72336_d;
            double d4 = axisalignedbb.field_72337_e;
            double d5 = axisalignedbb.field_72334_f;
            if(p_176424_5_.func_82601_c() < 0) {
               d0 -= (double)((float)p_176424_5_.func_82601_c() * p_176424_4_);
            } else {
               d3 -= (double)((float)p_176424_5_.func_82601_c() * p_176424_4_);
            }

            if(p_176424_5_.func_96559_d() < 0) {
               d1 -= (double)((float)p_176424_5_.func_96559_d() * p_176424_4_);
            } else {
               d4 -= (double)((float)p_176424_5_.func_96559_d() * p_176424_4_);
            }

            if(p_176424_5_.func_82599_e() < 0) {
               d2 -= (double)((float)p_176424_5_.func_82599_e() * p_176424_4_);
            } else {
               d5 -= (double)((float)p_176424_5_.func_82599_e() * p_176424_4_);
            }

            return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
         }
      } else {
         return null;
      }
   }

   private TileEntityPiston func_176422_e(IBlockAccess p_176422_1_, BlockPos p_176422_2_) {
      TileEntity tileentity = p_176422_1_.func_175625_s(p_176422_2_);
      return tileentity instanceof TileEntityPiston?(TileEntityPiston)tileentity:null;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return null;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176426_a, BlockPistonExtension.func_176322_b(p_176203_1_)).func_177226_a(field_176425_b, (p_176203_1_ & 8) > 0?BlockPistonExtension.EnumPistonType.STICKY:BlockPistonExtension.EnumPistonType.DEFAULT);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176426_a)).func_176745_a();
      if(p_176201_1_.func_177229_b(field_176425_b) == BlockPistonExtension.EnumPistonType.STICKY) {
         i |= 8;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176426_a, field_176425_b});
   }
}
