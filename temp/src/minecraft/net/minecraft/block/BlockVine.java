package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {
   public static final PropertyBool field_176277_a = PropertyBool.func_177716_a("up");
   public static final PropertyBool field_176273_b = PropertyBool.func_177716_a("north");
   public static final PropertyBool field_176278_M = PropertyBool.func_177716_a("east");
   public static final PropertyBool field_176279_N = PropertyBool.func_177716_a("south");
   public static final PropertyBool field_176280_O = PropertyBool.func_177716_a("west");
   public static final PropertyBool[] field_176274_P = new PropertyBool[]{field_176277_a, field_176273_b, field_176279_N, field_176280_O, field_176278_M};

   public BlockVine() {
      super(Material.field_151582_l);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176277_a, Boolean.valueOf(false)).func_177226_a(field_176273_b, Boolean.valueOf(false)).func_177226_a(field_176278_M, Boolean.valueOf(false)).func_177226_a(field_176279_N, Boolean.valueOf(false)).func_177226_a(field_176280_O, Boolean.valueOf(false)));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      return p_176221_1_.func_177226_a(field_176277_a, Boolean.valueOf(p_176221_2_.func_180495_p(p_176221_3_.func_177984_a()).func_177230_c().func_149637_q()));
   }

   public void func_149683_g() {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176200_f(World p_176200_1_, BlockPos p_176200_2_) {
      return true;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      float f = 0.0625F;
      float f1 = 1.0F;
      float f2 = 1.0F;
      float f3 = 1.0F;
      float f4 = 0.0F;
      float f5 = 0.0F;
      float f6 = 0.0F;
      boolean flag = false;
      if(((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176280_O)).booleanValue()) {
         f4 = Math.max(f4, 0.0625F);
         f1 = 0.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         f3 = 0.0F;
         f6 = 1.0F;
         flag = true;
      }

      if(((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176278_M)).booleanValue()) {
         f1 = Math.min(f1, 0.9375F);
         f4 = 1.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         f3 = 0.0F;
         f6 = 1.0F;
         flag = true;
      }

      if(((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176273_b)).booleanValue()) {
         f6 = Math.max(f6, 0.0625F);
         f3 = 0.0F;
         f1 = 0.0F;
         f4 = 1.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         flag = true;
      }

      if(((Boolean)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176279_N)).booleanValue()) {
         f3 = Math.min(f3, 0.9375F);
         f6 = 1.0F;
         f1 = 0.0F;
         f4 = 1.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         flag = true;
      }

      if(!flag && this.func_150093_a(p_180654_1_.func_180495_p(p_180654_2_.func_177984_a()).func_177230_c())) {
         f2 = Math.min(f2, 0.9375F);
         f5 = 1.0F;
         f1 = 0.0F;
         f4 = 1.0F;
         f3 = 0.0F;
         f6 = 1.0F;
      }

      this.func_149676_a(f1, f2, f3, f4, f5, f6);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_176198_a(World p_176198_1_, BlockPos p_176198_2_, EnumFacing p_176198_3_) {
      switch(p_176198_3_) {
      case UP:
         return this.func_150093_a(p_176198_1_.func_180495_p(p_176198_2_.func_177984_a()).func_177230_c());
      case NORTH:
      case SOUTH:
      case EAST:
      case WEST:
         return this.func_150093_a(p_176198_1_.func_180495_p(p_176198_2_.func_177972_a(p_176198_3_.func_176734_d())).func_177230_c());
      default:
         return false;
      }
   }

   private boolean func_150093_a(Block p_150093_1_) {
      return p_150093_1_.func_149686_d() && p_150093_1_.field_149764_J.func_76230_c();
   }

   private boolean func_176269_e(World p_176269_1_, BlockPos p_176269_2_, IBlockState p_176269_3_) {
      IBlockState iblockstate = p_176269_3_;

      for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
         PropertyBool propertybool = func_176267_a(enumfacing);
         if(((Boolean)p_176269_3_.func_177229_b(propertybool)).booleanValue() && !this.func_150093_a(p_176269_1_.func_180495_p(p_176269_2_.func_177972_a(enumfacing)).func_177230_c())) {
            IBlockState iblockstate1 = p_176269_1_.func_180495_p(p_176269_2_.func_177984_a());
            if(iblockstate1.func_177230_c() != this || !((Boolean)iblockstate1.func_177229_b(propertybool)).booleanValue()) {
               p_176269_3_ = p_176269_3_.func_177226_a(propertybool, Boolean.valueOf(false));
            }
         }
      }

      if(func_176268_d(p_176269_3_) == 0) {
         return false;
      } else {
         if(iblockstate != p_176269_3_) {
            p_176269_1_.func_180501_a(p_176269_2_, p_176269_3_, 2);
         }

         return true;
      }
   }

   public int func_149635_D() {
      return ColorizerFoliage.func_77468_c();
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      return ColorizerFoliage.func_77468_c();
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return p_180662_1_.func_180494_b(p_180662_2_).func_180625_c(p_180662_2_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K && !this.func_176269_e(p_176204_1_, p_176204_2_, p_176204_3_)) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!p_180650_1_.field_72995_K) {
         if(p_180650_1_.field_73012_v.nextInt(4) == 0) {
            int i = 4;
            int j = 5;
            boolean flag = false;

            label62:
            for(int k = -i; k <= i; ++k) {
               for(int l = -i; l <= i; ++l) {
                  for(int i1 = -1; i1 <= 1; ++i1) {
                     if(p_180650_1_.func_180495_p(p_180650_2_.func_177982_a(k, i1, l)).func_177230_c() == this) {
                        --j;
                        if(j <= 0) {
                           flag = true;
                           break label62;
                        }
                     }
                  }
               }
            }

            EnumFacing enumfacing1 = EnumFacing.func_176741_a(p_180650_4_);
            BlockPos blockpos1 = p_180650_2_.func_177984_a();
            if(enumfacing1 == EnumFacing.UP && p_180650_2_.func_177956_o() < 255 && p_180650_1_.func_175623_d(blockpos1)) {
               if(!flag) {
                  IBlockState iblockstate2 = p_180650_3_;

                  for(EnumFacing enumfacing3 : EnumFacing.Plane.HORIZONTAL) {
                     if(p_180650_4_.nextBoolean() || !this.func_150093_a(p_180650_1_.func_180495_p(blockpos1.func_177972_a(enumfacing3)).func_177230_c())) {
                        iblockstate2 = iblockstate2.func_177226_a(func_176267_a(enumfacing3), Boolean.valueOf(false));
                     }
                  }

                  if(((Boolean)iblockstate2.func_177229_b(field_176273_b)).booleanValue() || ((Boolean)iblockstate2.func_177229_b(field_176278_M)).booleanValue() || ((Boolean)iblockstate2.func_177229_b(field_176279_N)).booleanValue() || ((Boolean)iblockstate2.func_177229_b(field_176280_O)).booleanValue()) {
                     p_180650_1_.func_180501_a(blockpos1, iblockstate2, 2);
                  }

               }
            } else if(enumfacing1.func_176740_k().func_176722_c() && !((Boolean)p_180650_3_.func_177229_b(func_176267_a(enumfacing1))).booleanValue()) {
               if(!flag) {
                  BlockPos blockpos3 = p_180650_2_.func_177972_a(enumfacing1);
                  Block block1 = p_180650_1_.func_180495_p(blockpos3).func_177230_c();
                  if(block1.field_149764_J == Material.field_151579_a) {
                     EnumFacing enumfacing2 = enumfacing1.func_176746_e();
                     EnumFacing enumfacing4 = enumfacing1.func_176735_f();
                     boolean flag1 = ((Boolean)p_180650_3_.func_177229_b(func_176267_a(enumfacing2))).booleanValue();
                     boolean flag2 = ((Boolean)p_180650_3_.func_177229_b(func_176267_a(enumfacing4))).booleanValue();
                     BlockPos blockpos4 = blockpos3.func_177972_a(enumfacing2);
                     BlockPos blockpos = blockpos3.func_177972_a(enumfacing4);
                     if(flag1 && this.func_150093_a(p_180650_1_.func_180495_p(blockpos4).func_177230_c())) {
                        p_180650_1_.func_180501_a(blockpos3, this.func_176223_P().func_177226_a(func_176267_a(enumfacing2), Boolean.valueOf(true)), 2);
                     } else if(flag2 && this.func_150093_a(p_180650_1_.func_180495_p(blockpos).func_177230_c())) {
                        p_180650_1_.func_180501_a(blockpos3, this.func_176223_P().func_177226_a(func_176267_a(enumfacing4), Boolean.valueOf(true)), 2);
                     } else if(flag1 && p_180650_1_.func_175623_d(blockpos4) && this.func_150093_a(p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(enumfacing2)).func_177230_c())) {
                        p_180650_1_.func_180501_a(blockpos4, this.func_176223_P().func_177226_a(func_176267_a(enumfacing1.func_176734_d()), Boolean.valueOf(true)), 2);
                     } else if(flag2 && p_180650_1_.func_175623_d(blockpos) && this.func_150093_a(p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(enumfacing4)).func_177230_c())) {
                        p_180650_1_.func_180501_a(blockpos, this.func_176223_P().func_177226_a(func_176267_a(enumfacing1.func_176734_d()), Boolean.valueOf(true)), 2);
                     } else if(this.func_150093_a(p_180650_1_.func_180495_p(blockpos3.func_177984_a()).func_177230_c())) {
                        p_180650_1_.func_180501_a(blockpos3, this.func_176223_P(), 2);
                     }
                  } else if(block1.field_149764_J.func_76218_k() && block1.func_149686_d()) {
                     p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(func_176267_a(enumfacing1), Boolean.valueOf(true)), 2);
                  }

               }
            } else {
               if(p_180650_2_.func_177956_o() > 1) {
                  BlockPos blockpos2 = p_180650_2_.func_177977_b();
                  IBlockState iblockstate = p_180650_1_.func_180495_p(blockpos2);
                  Block block = iblockstate.func_177230_c();
                  if(block.field_149764_J == Material.field_151579_a) {
                     IBlockState iblockstate1 = p_180650_3_;

                     for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                        if(p_180650_4_.nextBoolean()) {
                           iblockstate1 = iblockstate1.func_177226_a(func_176267_a(enumfacing), Boolean.valueOf(false));
                        }
                     }

                     if(((Boolean)iblockstate1.func_177229_b(field_176273_b)).booleanValue() || ((Boolean)iblockstate1.func_177229_b(field_176278_M)).booleanValue() || ((Boolean)iblockstate1.func_177229_b(field_176279_N)).booleanValue() || ((Boolean)iblockstate1.func_177229_b(field_176280_O)).booleanValue()) {
                        p_180650_1_.func_180501_a(blockpos2, iblockstate1, 2);
                     }
                  } else if(block == this) {
                     IBlockState iblockstate3 = iblockstate;

                     for(EnumFacing enumfacing5 : EnumFacing.Plane.HORIZONTAL) {
                        PropertyBool propertybool = func_176267_a(enumfacing5);
                        if(p_180650_4_.nextBoolean() && ((Boolean)p_180650_3_.func_177229_b(propertybool)).booleanValue()) {
                           iblockstate3 = iblockstate3.func_177226_a(propertybool, Boolean.valueOf(true));
                        }
                     }

                     if(((Boolean)iblockstate3.func_177229_b(field_176273_b)).booleanValue() || ((Boolean)iblockstate3.func_177229_b(field_176278_M)).booleanValue() || ((Boolean)iblockstate3.func_177229_b(field_176279_N)).booleanValue() || ((Boolean)iblockstate3.func_177229_b(field_176280_O)).booleanValue()) {
                        p_180650_1_.func_180501_a(blockpos2, iblockstate3, 2);
                     }
                  }
               }

            }
         }
      }
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      IBlockState iblockstate = this.func_176223_P().func_177226_a(field_176277_a, Boolean.valueOf(false)).func_177226_a(field_176273_b, Boolean.valueOf(false)).func_177226_a(field_176278_M, Boolean.valueOf(false)).func_177226_a(field_176279_N, Boolean.valueOf(false)).func_177226_a(field_176280_O, Boolean.valueOf(false));
      return p_180642_3_.func_176740_k().func_176722_c()?iblockstate.func_177226_a(func_176267_a(p_180642_3_.func_176734_d()), Boolean.valueOf(true)):iblockstate;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_) {
      if(!p_180657_1_.field_72995_K && p_180657_2_.func_71045_bC() != null && p_180657_2_.func_71045_bC().func_77973_b() == Items.field_151097_aZ) {
         p_180657_2_.func_71029_a(StatList.field_75934_C[Block.func_149682_b(this)]);
         func_180635_a(p_180657_1_, p_180657_3_, new ItemStack(Blocks.field_150395_bd, 1, 0));
      } else {
         super.func_180657_a(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, p_180657_5_);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176279_N, Boolean.valueOf((p_176203_1_ & 1) > 0)).func_177226_a(field_176280_O, Boolean.valueOf((p_176203_1_ & 2) > 0)).func_177226_a(field_176273_b, Boolean.valueOf((p_176203_1_ & 4) > 0)).func_177226_a(field_176278_M, Boolean.valueOf((p_176203_1_ & 8) > 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      if(((Boolean)p_176201_1_.func_177229_b(field_176279_N)).booleanValue()) {
         i |= 1;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176280_O)).booleanValue()) {
         i |= 2;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176273_b)).booleanValue()) {
         i |= 4;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176278_M)).booleanValue()) {
         i |= 8;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176277_a, field_176273_b, field_176278_M, field_176279_N, field_176280_O});
   }

   public static PropertyBool func_176267_a(EnumFacing p_176267_0_) {
      switch(p_176267_0_) {
      case UP:
         return field_176277_a;
      case NORTH:
         return field_176273_b;
      case SOUTH:
         return field_176279_N;
      case EAST:
         return field_176278_M;
      case WEST:
         return field_176280_O;
      default:
         throw new IllegalArgumentException(p_176267_0_ + " is an invalid choice");
      }
   }

   public static int func_176268_d(IBlockState p_176268_0_) {
      int i = 0;

      for(PropertyBool propertybool : field_176274_P) {
         if(((Boolean)p_176268_0_.func_177229_b(propertybool)).booleanValue()) {
            ++i;
         }
      }

      return i;
   }
}
