package net.minecraft.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneWire extends Block {
   public static final PropertyEnum<BlockRedstoneWire.EnumAttachPosition> field_176348_a = PropertyEnum.<BlockRedstoneWire.EnumAttachPosition>func_177709_a("north", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyEnum<BlockRedstoneWire.EnumAttachPosition> field_176347_b = PropertyEnum.<BlockRedstoneWire.EnumAttachPosition>func_177709_a("east", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyEnum<BlockRedstoneWire.EnumAttachPosition> field_176349_M = PropertyEnum.<BlockRedstoneWire.EnumAttachPosition>func_177709_a("south", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyEnum<BlockRedstoneWire.EnumAttachPosition> field_176350_N = PropertyEnum.<BlockRedstoneWire.EnumAttachPosition>func_177709_a("west", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyInteger field_176351_O = PropertyInteger.func_177719_a("power", 0, 15);
   private boolean field_150181_a = true;
   private final Set<BlockPos> field_150179_b = Sets.<BlockPos>newHashSet();

   public BlockRedstoneWire() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176348_a, BlockRedstoneWire.EnumAttachPosition.NONE).func_177226_a(field_176347_b, BlockRedstoneWire.EnumAttachPosition.NONE).func_177226_a(field_176349_M, BlockRedstoneWire.EnumAttachPosition.NONE).func_177226_a(field_176350_N, BlockRedstoneWire.EnumAttachPosition.NONE).func_177226_a(field_176351_O, Integer.valueOf(0)));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      p_176221_1_ = p_176221_1_.func_177226_a(field_176350_N, this.func_176341_c(p_176221_2_, p_176221_3_, EnumFacing.WEST));
      p_176221_1_ = p_176221_1_.func_177226_a(field_176347_b, this.func_176341_c(p_176221_2_, p_176221_3_, EnumFacing.EAST));
      p_176221_1_ = p_176221_1_.func_177226_a(field_176348_a, this.func_176341_c(p_176221_2_, p_176221_3_, EnumFacing.NORTH));
      p_176221_1_ = p_176221_1_.func_177226_a(field_176349_M, this.func_176341_c(p_176221_2_, p_176221_3_, EnumFacing.SOUTH));
      return p_176221_1_;
   }

   private BlockRedstoneWire.EnumAttachPosition func_176341_c(IBlockAccess p_176341_1_, BlockPos p_176341_2_, EnumFacing p_176341_3_) {
      BlockPos blockpos = p_176341_2_.func_177972_a(p_176341_3_);
      Block block = p_176341_1_.func_180495_p(p_176341_2_.func_177972_a(p_176341_3_)).func_177230_c();
      if(!func_176343_a(p_176341_1_.func_180495_p(blockpos), p_176341_3_) && (block.func_149637_q() || !func_176346_d(p_176341_1_.func_180495_p(blockpos.func_177977_b())))) {
         Block block1 = p_176341_1_.func_180495_p(p_176341_2_.func_177984_a()).func_177230_c();
         return !block1.func_149637_q() && block.func_149637_q() && func_176346_d(p_176341_1_.func_180495_p(blockpos.func_177984_a()))?BlockRedstoneWire.EnumAttachPosition.UP:BlockRedstoneWire.EnumAttachPosition.NONE;
      } else {
         return BlockRedstoneWire.EnumAttachPosition.SIDE;
      }
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

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      IBlockState iblockstate = p_180662_1_.func_180495_p(p_180662_2_);
      return iblockstate.func_177230_c() != this?super.func_180662_a(p_180662_1_, p_180662_2_, p_180662_3_):this.func_176337_b(((Integer)iblockstate.func_177229_b(field_176351_O)).intValue());
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return World.func_175683_a(p_176196_1_, p_176196_2_.func_177977_b()) || p_176196_1_.func_180495_p(p_176196_2_.func_177977_b()).func_177230_c() == Blocks.field_150426_aN;
   }

   private IBlockState func_176338_e(World p_176338_1_, BlockPos p_176338_2_, IBlockState p_176338_3_) {
      p_176338_3_ = this.func_176345_a(p_176338_1_, p_176338_2_, p_176338_2_, p_176338_3_);
      List<BlockPos> list = Lists.newArrayList(this.field_150179_b);
      this.field_150179_b.clear();

      for(BlockPos blockpos : list) {
         p_176338_1_.func_175685_c(blockpos, this);
      }

      return p_176338_3_;
   }

   private IBlockState func_176345_a(World p_176345_1_, BlockPos p_176345_2_, BlockPos p_176345_3_, IBlockState p_176345_4_) {
      IBlockState iblockstate = p_176345_4_;
      int i = ((Integer)p_176345_4_.func_177229_b(field_176351_O)).intValue();
      int j = 0;
      j = this.func_176342_a(p_176345_1_, p_176345_3_, j);
      this.field_150181_a = false;
      int k = p_176345_1_.func_175687_A(p_176345_2_);
      this.field_150181_a = true;
      if(k > 0 && k > j - 1) {
         j = k;
      }

      int l = 0;

      for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
         BlockPos blockpos = p_176345_2_.func_177972_a(enumfacing);
         boolean flag = blockpos.func_177958_n() != p_176345_3_.func_177958_n() || blockpos.func_177952_p() != p_176345_3_.func_177952_p();
         if(flag) {
            l = this.func_176342_a(p_176345_1_, blockpos, l);
         }

         if(p_176345_1_.func_180495_p(blockpos).func_177230_c().func_149721_r() && !p_176345_1_.func_180495_p(p_176345_2_.func_177984_a()).func_177230_c().func_149721_r()) {
            if(flag && p_176345_2_.func_177956_o() >= p_176345_3_.func_177956_o()) {
               l = this.func_176342_a(p_176345_1_, blockpos.func_177984_a(), l);
            }
         } else if(!p_176345_1_.func_180495_p(blockpos).func_177230_c().func_149721_r() && flag && p_176345_2_.func_177956_o() <= p_176345_3_.func_177956_o()) {
            l = this.func_176342_a(p_176345_1_, blockpos.func_177977_b(), l);
         }
      }

      if(l > j) {
         j = l - 1;
      } else if(j > 0) {
         --j;
      } else {
         j = 0;
      }

      if(k > j - 1) {
         j = k;
      }

      if(i != j) {
         p_176345_4_ = p_176345_4_.func_177226_a(field_176351_O, Integer.valueOf(j));
         if(p_176345_1_.func_180495_p(p_176345_2_) == iblockstate) {
            p_176345_1_.func_180501_a(p_176345_2_, p_176345_4_, 2);
         }

         this.field_150179_b.add(p_176345_2_);

         for(EnumFacing enumfacing1 : EnumFacing.values()) {
            this.field_150179_b.add(p_176345_2_.func_177972_a(enumfacing1));
         }
      }

      return p_176345_4_;
   }

   private void func_176344_d(World p_176344_1_, BlockPos p_176344_2_) {
      if(p_176344_1_.func_180495_p(p_176344_2_).func_177230_c() == this) {
         p_176344_1_.func_175685_c(p_176344_2_, this);

         for(EnumFacing enumfacing : EnumFacing.values()) {
            p_176344_1_.func_175685_c(p_176344_2_.func_177972_a(enumfacing), this);
         }

      }
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if(!p_176213_1_.field_72995_K) {
         this.func_176338_e(p_176213_1_, p_176213_2_, p_176213_3_);

         for(EnumFacing enumfacing : EnumFacing.Plane.VERTICAL) {
            p_176213_1_.func_175685_c(p_176213_2_.func_177972_a(enumfacing), this);
         }

         for(EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
            this.func_176344_d(p_176213_1_, p_176213_2_.func_177972_a(enumfacing1));
         }

         for(EnumFacing enumfacing2 : EnumFacing.Plane.HORIZONTAL) {
            BlockPos blockpos = p_176213_2_.func_177972_a(enumfacing2);
            if(p_176213_1_.func_180495_p(blockpos).func_177230_c().func_149721_r()) {
               this.func_176344_d(p_176213_1_, blockpos.func_177984_a());
            } else {
               this.func_176344_d(p_176213_1_, blockpos.func_177977_b());
            }
         }

      }
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      if(!p_180663_1_.field_72995_K) {
         for(EnumFacing enumfacing : EnumFacing.values()) {
            p_180663_1_.func_175685_c(p_180663_2_.func_177972_a(enumfacing), this);
         }

         this.func_176338_e(p_180663_1_, p_180663_2_, p_180663_3_);

         for(EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
            this.func_176344_d(p_180663_1_, p_180663_2_.func_177972_a(enumfacing1));
         }

         for(EnumFacing enumfacing2 : EnumFacing.Plane.HORIZONTAL) {
            BlockPos blockpos = p_180663_2_.func_177972_a(enumfacing2);
            if(p_180663_1_.func_180495_p(blockpos).func_177230_c().func_149721_r()) {
               this.func_176344_d(p_180663_1_, blockpos.func_177984_a());
            } else {
               this.func_176344_d(p_180663_1_, blockpos.func_177977_b());
            }
         }

      }
   }

   private int func_176342_a(World p_176342_1_, BlockPos p_176342_2_, int p_176342_3_) {
      if(p_176342_1_.func_180495_p(p_176342_2_).func_177230_c() != this) {
         return p_176342_3_;
      } else {
         int i = ((Integer)p_176342_1_.func_180495_p(p_176342_2_).func_177229_b(field_176351_O)).intValue();
         return i > p_176342_3_?i:p_176342_3_;
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         if(this.func_176196_c(p_176204_1_, p_176204_2_)) {
            this.func_176338_e(p_176204_1_, p_176204_2_, p_176204_3_);
         } else {
            this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
            p_176204_1_.func_175698_g(p_176204_2_);
         }

      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151137_ax;
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      return !this.field_150181_a?0:this.func_180656_a(p_176211_1_, p_176211_2_, p_176211_3_, p_176211_4_);
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      if(!this.field_150181_a) {
         return 0;
      } else {
         int i = ((Integer)p_180656_3_.func_177229_b(field_176351_O)).intValue();
         if(i == 0) {
            return 0;
         } else if(p_180656_4_ == EnumFacing.UP) {
            return i;
         } else {
            EnumSet<EnumFacing> enumset = EnumSet.<EnumFacing>noneOf(EnumFacing.class);

            for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
               if(this.func_176339_d(p_180656_1_, p_180656_2_, enumfacing)) {
                  enumset.add(enumfacing);
               }
            }

            if(p_180656_4_.func_176740_k().func_176722_c() && enumset.isEmpty()) {
               return i;
            } else if(enumset.contains(p_180656_4_) && !enumset.contains(p_180656_4_.func_176735_f()) && !enumset.contains(p_180656_4_.func_176746_e())) {
               return i;
            } else {
               return 0;
            }
         }
      }
   }

   private boolean func_176339_d(IBlockAccess p_176339_1_, BlockPos p_176339_2_, EnumFacing p_176339_3_) {
      BlockPos blockpos = p_176339_2_.func_177972_a(p_176339_3_);
      IBlockState iblockstate = p_176339_1_.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      boolean flag = block.func_149721_r();
      boolean flag1 = p_176339_1_.func_180495_p(p_176339_2_.func_177984_a()).func_177230_c().func_149721_r();
      return !flag1 && flag && func_176340_e(p_176339_1_, blockpos.func_177984_a())?true:(func_176343_a(iblockstate, p_176339_3_)?true:(block == Blocks.field_150416_aS && iblockstate.func_177229_b(BlockRedstoneDiode.field_176387_N) == p_176339_3_?true:!flag && func_176340_e(p_176339_1_, blockpos.func_177977_b())));
   }

   protected static boolean func_176340_e(IBlockAccess p_176340_0_, BlockPos p_176340_1_) {
      return func_176346_d(p_176340_0_.func_180495_p(p_176340_1_));
   }

   protected static boolean func_176346_d(IBlockState p_176346_0_) {
      return func_176343_a(p_176346_0_, (EnumFacing)null);
   }

   protected static boolean func_176343_a(IBlockState p_176343_0_, EnumFacing p_176343_1_) {
      Block block = p_176343_0_.func_177230_c();
      if(block == Blocks.field_150488_af) {
         return true;
      } else if(Blocks.field_150413_aR.func_149907_e(block)) {
         EnumFacing enumfacing = (EnumFacing)p_176343_0_.func_177229_b(BlockRedstoneRepeater.field_176387_N);
         return enumfacing == p_176343_1_ || enumfacing.func_176734_d() == p_176343_1_;
      } else {
         return block.func_149744_f() && p_176343_1_ != null;
      }
   }

   public boolean func_149744_f() {
      return this.field_150181_a;
   }

   private int func_176337_b(int p_176337_1_) {
      float f = (float)p_176337_1_ / 15.0F;
      float f1 = f * 0.6F + 0.4F;
      if(p_176337_1_ == 0) {
         f1 = 0.3F;
      }

      float f2 = f * f * 0.7F - 0.5F;
      float f3 = f * f * 0.6F - 0.7F;
      if(f2 < 0.0F) {
         f2 = 0.0F;
      }

      if(f3 < 0.0F) {
         f3 = 0.0F;
      }

      int i = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
      int j = MathHelper.func_76125_a((int)(f2 * 255.0F), 0, 255);
      int k = MathHelper.func_76125_a((int)(f3 * 255.0F), 0, 255);
      return -16777216 | i << 16 | j << 8 | k;
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      int i = ((Integer)p_180655_3_.func_177229_b(field_176351_O)).intValue();
      if(i != 0) {
         double d0 = (double)p_180655_2_.func_177958_n() + 0.5D + ((double)p_180655_4_.nextFloat() - 0.5D) * 0.2D;
         double d1 = (double)((float)p_180655_2_.func_177956_o() + 0.0625F);
         double d2 = (double)p_180655_2_.func_177952_p() + 0.5D + ((double)p_180655_4_.nextFloat() - 0.5D) * 0.2D;
         float f = (float)i / 15.0F;
         float f1 = f * 0.6F + 0.4F;
         float f2 = Math.max(0.0F, f * f * 0.7F - 0.5F);
         float f3 = Math.max(0.0F, f * f * 0.6F - 0.7F);
         p_180655_1_.func_175688_a(EnumParticleTypes.REDSTONE, d0, d1, d2, (double)f1, (double)f2, (double)f3, new int[0]);
      }
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151137_ax;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176351_O, Integer.valueOf(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176351_O)).intValue();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176348_a, field_176347_b, field_176349_M, field_176350_N, field_176351_O});
   }

   static enum EnumAttachPosition implements IStringSerializable {
      UP("up"),
      SIDE("side"),
      NONE("none");

      private final String field_176820_d;

      private EnumAttachPosition(String p_i45689_3_) {
         this.field_176820_d = p_i45689_3_;
      }

      public String toString() {
         return this.func_176610_l();
      }

      public String func_176610_l() {
         return this.field_176820_d;
      }
   }
}
