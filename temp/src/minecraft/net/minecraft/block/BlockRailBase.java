package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRailBase extends Block {
   protected final boolean field_150053_a;

   public static boolean func_176562_d(World p_176562_0_, BlockPos p_176562_1_) {
      return func_176563_d(p_176562_0_.func_180495_p(p_176562_1_));
   }

   public static boolean func_176563_d(IBlockState p_176563_0_) {
      Block block = p_176563_0_.func_177230_c();
      return block == Blocks.field_150448_aq || block == Blocks.field_150318_D || block == Blocks.field_150319_E || block == Blocks.field_150408_cc;
   }

   protected BlockRailBase(boolean p_i45389_1_) {
      super(Material.field_151594_q);
      this.field_150053_a = p_i45389_1_;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.func_149647_a(CreativeTabs.field_78029_e);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      this.func_180654_a(p_180636_1_, p_180636_2_);
      return super.func_180636_a(p_180636_1_, p_180636_2_, p_180636_3_, p_180636_4_);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState iblockstate = p_180654_1_.func_180495_p(p_180654_2_);
      BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = iblockstate.func_177230_c() == this?(BlockRailBase.EnumRailDirection)iblockstate.func_177229_b(this.func_176560_l()):null;
      if(blockrailbase$enumraildirection != null && blockrailbase$enumraildirection.func_177018_c()) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      }

   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return World.func_175683_a(p_176196_1_, p_176196_2_.func_177977_b());
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if(!p_176213_1_.field_72995_K) {
         p_176213_3_ = this.func_176564_a(p_176213_1_, p_176213_2_, p_176213_3_, true);
         if(this.field_150053_a) {
            this.func_176204_a(p_176213_1_, p_176213_2_, p_176213_3_, this);
         }
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = (BlockRailBase.EnumRailDirection)p_176204_3_.func_177229_b(this.func_176560_l());
         boolean flag = false;
         if(!World.func_175683_a(p_176204_1_, p_176204_2_.func_177977_b())) {
            flag = true;
         }

         if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.ASCENDING_EAST && !World.func_175683_a(p_176204_1_, p_176204_2_.func_177974_f())) {
            flag = true;
         } else if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.ASCENDING_WEST && !World.func_175683_a(p_176204_1_, p_176204_2_.func_177976_e())) {
            flag = true;
         } else if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.ASCENDING_NORTH && !World.func_175683_a(p_176204_1_, p_176204_2_.func_177978_c())) {
            flag = true;
         } else if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.ASCENDING_SOUTH && !World.func_175683_a(p_176204_1_, p_176204_2_.func_177968_d())) {
            flag = true;
         }

         if(flag) {
            this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
            p_176204_1_.func_175698_g(p_176204_2_);
         } else {
            this.func_176561_b(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
         }

      }
   }

   protected void func_176561_b(World p_176561_1_, BlockPos p_176561_2_, IBlockState p_176561_3_, Block p_176561_4_) {
   }

   protected IBlockState func_176564_a(World p_176564_1_, BlockPos p_176564_2_, IBlockState p_176564_3_, boolean p_176564_4_) {
      return p_176564_1_.field_72995_K?p_176564_3_:(new BlockRailBase.Rail(p_176564_1_, p_176564_2_, p_176564_3_)).func_180364_a(p_176564_1_.func_175640_z(p_176564_2_), p_176564_4_).func_180362_b();
   }

   public int func_149656_h() {
      return 0;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      if(((BlockRailBase.EnumRailDirection)p_180663_3_.func_177229_b(this.func_176560_l())).func_177018_c()) {
         p_180663_1_.func_175685_c(p_180663_2_.func_177984_a(), this);
      }

      if(this.field_150053_a) {
         p_180663_1_.func_175685_c(p_180663_2_, this);
         p_180663_1_.func_175685_c(p_180663_2_.func_177977_b(), this);
      }

   }

   public abstract IProperty<BlockRailBase.EnumRailDirection> func_176560_l();

   public static enum EnumRailDirection implements IStringSerializable {
      NORTH_SOUTH(0, "north_south"),
      EAST_WEST(1, "east_west"),
      ASCENDING_EAST(2, "ascending_east"),
      ASCENDING_WEST(3, "ascending_west"),
      ASCENDING_NORTH(4, "ascending_north"),
      ASCENDING_SOUTH(5, "ascending_south"),
      SOUTH_EAST(6, "south_east"),
      SOUTH_WEST(7, "south_west"),
      NORTH_WEST(8, "north_west"),
      NORTH_EAST(9, "north_east");

      private static final BlockRailBase.EnumRailDirection[] field_177030_k = new BlockRailBase.EnumRailDirection[values().length];
      private final int field_177027_l;
      private final String field_177028_m;

      private EnumRailDirection(int p_i45738_3_, String p_i45738_4_) {
         this.field_177027_l = p_i45738_3_;
         this.field_177028_m = p_i45738_4_;
      }

      public int func_177015_a() {
         return this.field_177027_l;
      }

      public String toString() {
         return this.field_177028_m;
      }

      public boolean func_177018_c() {
         return this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST;
      }

      public static BlockRailBase.EnumRailDirection func_177016_a(int p_177016_0_) {
         if(p_177016_0_ < 0 || p_177016_0_ >= field_177030_k.length) {
            p_177016_0_ = 0;
         }

         return field_177030_k[p_177016_0_];
      }

      public String func_176610_l() {
         return this.field_177028_m;
      }

      static {
         for(BlockRailBase.EnumRailDirection blockrailbase$enumraildirection : values()) {
            field_177030_k[blockrailbase$enumraildirection.func_177015_a()] = blockrailbase$enumraildirection;
         }

      }
   }

   public class Rail {
      private final World field_150660_b;
      private final BlockPos field_180367_c;
      private final BlockRailBase field_180365_d;
      private IBlockState field_180366_e;
      private final boolean field_150656_f;
      private final List<BlockPos> field_150657_g = Lists.<BlockPos>newArrayList();

      public Rail(World p_i45739_2_, BlockPos p_i45739_3_, IBlockState p_i45739_4_) {
         this.field_150660_b = p_i45739_2_;
         this.field_180367_c = p_i45739_3_;
         this.field_180366_e = p_i45739_4_;
         this.field_180365_d = (BlockRailBase)p_i45739_4_.func_177230_c();
         BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = (BlockRailBase.EnumRailDirection)p_i45739_4_.func_177229_b(BlockRailBase.this.func_176560_l());
         this.field_150656_f = this.field_180365_d.field_150053_a;
         this.func_180360_a(blockrailbase$enumraildirection);
      }

      private void func_180360_a(BlockRailBase.EnumRailDirection p_180360_1_) {
         this.field_150657_g.clear();
         switch(p_180360_1_) {
         case NORTH_SOUTH:
            this.field_150657_g.add(this.field_180367_c.func_177978_c());
            this.field_150657_g.add(this.field_180367_c.func_177968_d());
            break;
         case EAST_WEST:
            this.field_150657_g.add(this.field_180367_c.func_177976_e());
            this.field_150657_g.add(this.field_180367_c.func_177974_f());
            break;
         case ASCENDING_EAST:
            this.field_150657_g.add(this.field_180367_c.func_177976_e());
            this.field_150657_g.add(this.field_180367_c.func_177974_f().func_177984_a());
            break;
         case ASCENDING_WEST:
            this.field_150657_g.add(this.field_180367_c.func_177976_e().func_177984_a());
            this.field_150657_g.add(this.field_180367_c.func_177974_f());
            break;
         case ASCENDING_NORTH:
            this.field_150657_g.add(this.field_180367_c.func_177978_c().func_177984_a());
            this.field_150657_g.add(this.field_180367_c.func_177968_d());
            break;
         case ASCENDING_SOUTH:
            this.field_150657_g.add(this.field_180367_c.func_177978_c());
            this.field_150657_g.add(this.field_180367_c.func_177968_d().func_177984_a());
            break;
         case SOUTH_EAST:
            this.field_150657_g.add(this.field_180367_c.func_177974_f());
            this.field_150657_g.add(this.field_180367_c.func_177968_d());
            break;
         case SOUTH_WEST:
            this.field_150657_g.add(this.field_180367_c.func_177976_e());
            this.field_150657_g.add(this.field_180367_c.func_177968_d());
            break;
         case NORTH_WEST:
            this.field_150657_g.add(this.field_180367_c.func_177976_e());
            this.field_150657_g.add(this.field_180367_c.func_177978_c());
            break;
         case NORTH_EAST:
            this.field_150657_g.add(this.field_180367_c.func_177974_f());
            this.field_150657_g.add(this.field_180367_c.func_177978_c());
         }

      }

      private void func_150651_b() {
         for(int i = 0; i < this.field_150657_g.size(); ++i) {
            BlockRailBase.Rail blockrailbase$rail = this.func_180697_b((BlockPos)this.field_150657_g.get(i));
            if(blockrailbase$rail != null && blockrailbase$rail.func_150653_a(this)) {
               this.field_150657_g.set(i, blockrailbase$rail.field_180367_c);
            } else {
               this.field_150657_g.remove(i--);
            }
         }

      }

      private boolean func_180359_a(BlockPos p_180359_1_) {
         return BlockRailBase.func_176562_d(this.field_150660_b, p_180359_1_) || BlockRailBase.func_176562_d(this.field_150660_b, p_180359_1_.func_177984_a()) || BlockRailBase.func_176562_d(this.field_150660_b, p_180359_1_.func_177977_b());
      }

      private BlockRailBase.Rail func_180697_b(BlockPos p_180697_1_) {
         IBlockState iblockstate = this.field_150660_b.func_180495_p(p_180697_1_);
         if(BlockRailBase.func_176563_d(iblockstate)) {
            return BlockRailBase.this.new Rail(this.field_150660_b, p_180697_1_, iblockstate);
         } else {
            BlockPos lvt_2_1_ = p_180697_1_.func_177984_a();
            iblockstate = this.field_150660_b.func_180495_p(lvt_2_1_);
            if(BlockRailBase.func_176563_d(iblockstate)) {
               return BlockRailBase.this.new Rail(this.field_150660_b, lvt_2_1_, iblockstate);
            } else {
               lvt_2_1_ = p_180697_1_.func_177977_b();
               iblockstate = this.field_150660_b.func_180495_p(lvt_2_1_);
               return BlockRailBase.func_176563_d(iblockstate)?BlockRailBase.this.new Rail(this.field_150660_b, lvt_2_1_, iblockstate):null;
            }
         }
      }

      private boolean func_150653_a(BlockRailBase.Rail p_150653_1_) {
         return this.func_180363_c(p_150653_1_.field_180367_c);
      }

      private boolean func_180363_c(BlockPos p_180363_1_) {
         for(int i = 0; i < this.field_150657_g.size(); ++i) {
            BlockPos blockpos = (BlockPos)this.field_150657_g.get(i);
            if(blockpos.func_177958_n() == p_180363_1_.func_177958_n() && blockpos.func_177952_p() == p_180363_1_.func_177952_p()) {
               return true;
            }
         }

         return false;
      }

      protected int func_150650_a() {
         int i = 0;

         for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            if(this.func_180359_a(this.field_180367_c.func_177972_a(enumfacing))) {
               ++i;
            }
         }

         return i;
      }

      private boolean func_150649_b(BlockRailBase.Rail p_150649_1_) {
         return this.func_150653_a(p_150649_1_) || this.field_150657_g.size() != 2;
      }

      private void func_150645_c(BlockRailBase.Rail p_150645_1_) {
         this.field_150657_g.add(p_150645_1_.field_180367_c);
         BlockPos blockpos = this.field_180367_c.func_177978_c();
         BlockPos blockpos1 = this.field_180367_c.func_177968_d();
         BlockPos blockpos2 = this.field_180367_c.func_177976_e();
         BlockPos blockpos3 = this.field_180367_c.func_177974_f();
         boolean flag = this.func_180363_c(blockpos);
         boolean flag1 = this.func_180363_c(blockpos1);
         boolean flag2 = this.func_180363_c(blockpos2);
         boolean flag3 = this.func_180363_c(blockpos3);
         BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = null;
         if(flag || flag1) {
            blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if(flag2 || flag3) {
            blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.EAST_WEST;
         }

         if(!this.field_150656_f) {
            if(flag1 && flag3 && !flag && !flag2) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
            }

            if(flag1 && flag2 && !flag && !flag3) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
            }

            if(flag && flag2 && !flag1 && !flag3) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
            }

            if(flag && flag3 && !flag1 && !flag2) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
            }
         }

         if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
            }

            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos1.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
            }
         }

         if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos3.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
            }

            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos2.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
            }
         }

         if(blockrailbase$enumraildirection == null) {
            blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         this.field_180366_e = this.field_180366_e.func_177226_a(this.field_180365_d.func_176560_l(), blockrailbase$enumraildirection);
         this.field_150660_b.func_180501_a(this.field_180367_c, this.field_180366_e, 3);
      }

      private boolean func_180361_d(BlockPos p_180361_1_) {
         BlockRailBase.Rail blockrailbase$rail = this.func_180697_b(p_180361_1_);
         if(blockrailbase$rail == null) {
            return false;
         } else {
            blockrailbase$rail.func_150651_b();
            return blockrailbase$rail.func_150649_b(this);
         }
      }

      public BlockRailBase.Rail func_180364_a(boolean p_180364_1_, boolean p_180364_2_) {
         BlockPos blockpos = this.field_180367_c.func_177978_c();
         BlockPos blockpos1 = this.field_180367_c.func_177968_d();
         BlockPos blockpos2 = this.field_180367_c.func_177976_e();
         BlockPos blockpos3 = this.field_180367_c.func_177974_f();
         boolean flag = this.func_180361_d(blockpos);
         boolean flag1 = this.func_180361_d(blockpos1);
         boolean flag2 = this.func_180361_d(blockpos2);
         boolean flag3 = this.func_180361_d(blockpos3);
         BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = null;
         if((flag || flag1) && !flag2 && !flag3) {
            blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if((flag2 || flag3) && !flag && !flag1) {
            blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.EAST_WEST;
         }

         if(!this.field_150656_f) {
            if(flag1 && flag3 && !flag && !flag2) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
            }

            if(flag1 && flag2 && !flag && !flag3) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
            }

            if(flag && flag2 && !flag1 && !flag3) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
            }

            if(flag && flag3 && !flag1 && !flag2) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
            }
         }

         if(blockrailbase$enumraildirection == null) {
            if(flag || flag1) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            }

            if(flag2 || flag3) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.EAST_WEST;
            }

            if(!this.field_150656_f) {
               if(p_180364_1_) {
                  if(flag1 && flag3) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
                  }

                  if(flag2 && flag1) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
                  }

                  if(flag3 && flag) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
                  }

                  if(flag && flag2) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
                  }
               } else {
                  if(flag && flag2) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_WEST;
                  }

                  if(flag3 && flag) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_EAST;
                  }

                  if(flag2 && flag1) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_WEST;
                  }

                  if(flag1 && flag3) {
                     blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.SOUTH_EAST;
                  }
               }
            }
         }

         if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
            }

            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos1.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
            }
         }

         if(blockrailbase$enumraildirection == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos3.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
            }

            if(BlockRailBase.func_176562_d(this.field_150660_b, blockpos2.func_177984_a())) {
               blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
            }
         }

         if(blockrailbase$enumraildirection == null) {
            blockrailbase$enumraildirection = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         this.func_180360_a(blockrailbase$enumraildirection);
         this.field_180366_e = this.field_180366_e.func_177226_a(this.field_180365_d.func_176560_l(), blockrailbase$enumraildirection);
         if(p_180364_2_ || this.field_150660_b.func_180495_p(this.field_180367_c) != this.field_180366_e) {
            this.field_150660_b.func_180501_a(this.field_180367_c, this.field_180366_e, 3);

            for(int i = 0; i < this.field_150657_g.size(); ++i) {
               BlockRailBase.Rail blockrailbase$rail = this.func_180697_b((BlockPos)this.field_150657_g.get(i));
               if(blockrailbase$rail != null) {
                  blockrailbase$rail.func_150651_b();
                  if(blockrailbase$rail.func_150649_b(this)) {
                     blockrailbase$rail.func_150645_c(this);
                  }
               }
            }
         }

         return this;
      }

      public IBlockState func_180362_b() {
         return this.field_180366_e;
      }
   }
}
