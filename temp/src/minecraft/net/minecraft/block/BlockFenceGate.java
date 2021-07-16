package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFenceGate extends BlockDirectional {
   public static final PropertyBool field_176466_a = PropertyBool.func_177716_a("open");
   public static final PropertyBool field_176465_b = PropertyBool.func_177716_a("powered");
   public static final PropertyBool field_176467_M = PropertyBool.func_177716_a("in_wall");

   public BlockFenceGate(BlockPlanks.EnumType p_i46394_1_) {
      super(Material.field_151575_d, p_i46394_1_.func_181070_c());
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176466_a, Boolean.valueOf(false)).func_177226_a(field_176465_b, Boolean.valueOf(false)).func_177226_a(field_176467_M, Boolean.valueOf(false)));
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      EnumFacing.Axis enumfacing$axis = ((EnumFacing)p_176221_1_.func_177229_b(field_176387_N)).func_176740_k();
      if(enumfacing$axis == EnumFacing.Axis.Z && (p_176221_2_.func_180495_p(p_176221_3_.func_177976_e()).func_177230_c() == Blocks.field_150463_bK || p_176221_2_.func_180495_p(p_176221_3_.func_177974_f()).func_177230_c() == Blocks.field_150463_bK) || enumfacing$axis == EnumFacing.Axis.X && (p_176221_2_.func_180495_p(p_176221_3_.func_177978_c()).func_177230_c() == Blocks.field_150463_bK || p_176221_2_.func_180495_p(p_176221_3_.func_177968_d()).func_177230_c() == Blocks.field_150463_bK)) {
         p_176221_1_ = p_176221_1_.func_177226_a(field_176467_M, Boolean.valueOf(true));
      }

      return p_176221_1_;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return p_176196_1_.func_180495_p(p_176196_2_.func_177977_b()).func_177230_c().func_149688_o().func_76220_a()?super.func_176196_c(p_176196_1_, p_176196_2_):false;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      if(((Boolean)p_180640_3_.func_177229_b(field_176466_a)).booleanValue()) {
         return null;
      } else {
         EnumFacing.Axis enumfacing$axis = ((EnumFacing)p_180640_3_.func_177229_b(field_176387_N)).func_176740_k();
         return enumfacing$axis == EnumFacing.Axis.Z?new AxisAlignedBB((double)p_180640_2_.func_177958_n(), (double)p_180640_2_.func_177956_o(), (double)((float)p_180640_2_.func_177952_p() + 0.375F), (double)(p_180640_2_.func_177958_n() + 1), (double)((float)p_180640_2_.func_177956_o() + 1.5F), (double)((float)p_180640_2_.func_177952_p() + 0.625F)):new AxisAlignedBB((double)((float)p_180640_2_.func_177958_n() + 0.375F), (double)p_180640_2_.func_177956_o(), (double)p_180640_2_.func_177952_p(), (double)((float)p_180640_2_.func_177958_n() + 0.625F), (double)((float)p_180640_2_.func_177956_o() + 1.5F), (double)(p_180640_2_.func_177952_p() + 1));
      }
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      EnumFacing.Axis enumfacing$axis = ((EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176387_N)).func_176740_k();
      if(enumfacing$axis == EnumFacing.Axis.Z) {
         this.func_149676_a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
      } else {
         this.func_149676_a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
      }

   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return ((Boolean)p_176205_1_.func_180495_p(p_176205_2_).func_177229_b(field_176466_a)).booleanValue();
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176387_N, p_180642_8_.func_174811_aO()).func_177226_a(field_176466_a, Boolean.valueOf(false)).func_177226_a(field_176465_b, Boolean.valueOf(false)).func_177226_a(field_176467_M, Boolean.valueOf(false));
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(((Boolean)p_180639_3_.func_177229_b(field_176466_a)).booleanValue()) {
         p_180639_3_ = p_180639_3_.func_177226_a(field_176466_a, Boolean.valueOf(false));
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 2);
      } else {
         EnumFacing enumfacing = EnumFacing.func_176733_a((double)p_180639_4_.field_70177_z);
         if(p_180639_3_.func_177229_b(field_176387_N) == enumfacing.func_176734_d()) {
            p_180639_3_ = p_180639_3_.func_177226_a(field_176387_N, enumfacing);
         }

         p_180639_3_ = p_180639_3_.func_177226_a(field_176466_a, Boolean.valueOf(true));
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 2);
      }

      p_180639_1_.func_180498_a(p_180639_4_, ((Boolean)p_180639_3_.func_177229_b(field_176466_a)).booleanValue()?1003:1006, p_180639_2_, 0);
      return true;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         boolean flag = p_176204_1_.func_175640_z(p_176204_2_);
         if(flag || p_176204_4_.func_149744_f()) {
            if(flag && !((Boolean)p_176204_3_.func_177229_b(field_176466_a)).booleanValue() && !((Boolean)p_176204_3_.func_177229_b(field_176465_b)).booleanValue()) {
               p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176466_a, Boolean.valueOf(true)).func_177226_a(field_176465_b, Boolean.valueOf(true)), 2);
               p_176204_1_.func_180498_a((EntityPlayer)null, 1003, p_176204_2_, 0);
            } else if(!flag && ((Boolean)p_176204_3_.func_177229_b(field_176466_a)).booleanValue() && ((Boolean)p_176204_3_.func_177229_b(field_176465_b)).booleanValue()) {
               p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176466_a, Boolean.valueOf(false)).func_177226_a(field_176465_b, Boolean.valueOf(false)), 2);
               p_176204_1_.func_180498_a((EntityPlayer)null, 1006, p_176204_2_, 0);
            } else if(flag != ((Boolean)p_176204_3_.func_177229_b(field_176465_b)).booleanValue()) {
               p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176465_b, Boolean.valueOf(flag)), 2);
            }
         }

      }
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return true;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176387_N, EnumFacing.func_176731_b(p_176203_1_)).func_177226_a(field_176466_a, Boolean.valueOf((p_176203_1_ & 4) != 0)).func_177226_a(field_176465_b, Boolean.valueOf((p_176203_1_ & 8) != 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176387_N)).func_176736_b();
      if(((Boolean)p_176201_1_.func_177229_b(field_176465_b)).booleanValue()) {
         i |= 8;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176466_a)).booleanValue()) {
         i |= 4;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176387_N, field_176466_a, field_176465_b, field_176467_M});
   }
}
