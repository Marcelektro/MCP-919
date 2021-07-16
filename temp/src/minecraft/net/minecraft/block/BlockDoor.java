package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor extends Block {
   public static final PropertyDirection field_176520_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyBool field_176519_b = PropertyBool.func_177716_a("open");
   public static final PropertyEnum<BlockDoor.EnumHingePosition> field_176521_M = PropertyEnum.<BlockDoor.EnumHingePosition>func_177709_a("hinge", BlockDoor.EnumHingePosition.class);
   public static final PropertyBool field_176522_N = PropertyBool.func_177716_a("powered");
   public static final PropertyEnum<BlockDoor.EnumDoorHalf> field_176523_O = PropertyEnum.<BlockDoor.EnumDoorHalf>func_177709_a("half", BlockDoor.EnumDoorHalf.class);

   protected BlockDoor(Material p_i45402_1_) {
      super(p_i45402_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176520_a, EnumFacing.NORTH).func_177226_a(field_176519_b, Boolean.valueOf(false)).func_177226_a(field_176521_M, BlockDoor.EnumHingePosition.LEFT).func_177226_a(field_176522_N, Boolean.valueOf(false)).func_177226_a(field_176523_O, BlockDoor.EnumDoorHalf.LOWER));
   }

   public String func_149732_F() {
      return StatCollector.func_74838_a((this.func_149739_a() + ".name").replaceAll("tile", "item"));
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return func_176516_g(func_176515_e(p_176205_1_, p_176205_2_));
   }

   public boolean func_149686_d() {
      return false;
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      this.func_180654_a(p_180646_1_, p_180646_2_);
      return super.func_180646_a(p_180646_1_, p_180646_2_);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_150011_b(func_176515_e(p_180654_1_, p_180654_2_));
   }

   private void func_150011_b(int p_150011_1_) {
      float f = 0.1875F;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
      EnumFacing enumfacing = func_176511_f(p_150011_1_);
      boolean flag = func_176516_g(p_150011_1_);
      boolean flag1 = func_176513_j(p_150011_1_);
      if(flag) {
         if(enumfacing == EnumFacing.EAST) {
            if(!flag1) {
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            } else {
               this.func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            }
         } else if(enumfacing == EnumFacing.SOUTH) {
            if(!flag1) {
               this.func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
         } else if(enumfacing == EnumFacing.WEST) {
            if(!flag1) {
               this.func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            } else {
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            }
         } else if(enumfacing == EnumFacing.NORTH) {
            if(!flag1) {
               this.func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            } else {
               this.func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      } else if(enumfacing == EnumFacing.EAST) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
      } else if(enumfacing == EnumFacing.SOUTH) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
      } else if(enumfacing == EnumFacing.WEST) {
         this.func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else if(enumfacing == EnumFacing.NORTH) {
         this.func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
      }

   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(this.field_149764_J == Material.field_151573_f) {
         return true;
      } else {
         BlockPos blockpos = p_180639_3_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.LOWER?p_180639_2_:p_180639_2_.func_177977_b();
         IBlockState iblockstate = p_180639_2_.equals(blockpos)?p_180639_3_:p_180639_1_.func_180495_p(blockpos);
         if(iblockstate.func_177230_c() != this) {
            return false;
         } else {
            p_180639_3_ = iblockstate.func_177231_a(field_176519_b);
            p_180639_1_.func_180501_a(blockpos, p_180639_3_, 2);
            p_180639_1_.func_175704_b(blockpos, p_180639_2_);
            p_180639_1_.func_180498_a(p_180639_4_, ((Boolean)p_180639_3_.func_177229_b(field_176519_b)).booleanValue()?1003:1006, p_180639_2_, 0);
            return true;
         }
      }
   }

   public void func_176512_a(World p_176512_1_, BlockPos p_176512_2_, boolean p_176512_3_) {
      IBlockState iblockstate = p_176512_1_.func_180495_p(p_176512_2_);
      if(iblockstate.func_177230_c() == this) {
         BlockPos blockpos = iblockstate.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.LOWER?p_176512_2_:p_176512_2_.func_177977_b();
         IBlockState iblockstate1 = p_176512_2_ == blockpos?iblockstate:p_176512_1_.func_180495_p(blockpos);
         if(iblockstate1.func_177230_c() == this && ((Boolean)iblockstate1.func_177229_b(field_176519_b)).booleanValue() != p_176512_3_) {
            p_176512_1_.func_180501_a(blockpos, iblockstate1.func_177226_a(field_176519_b, Boolean.valueOf(p_176512_3_)), 2);
            p_176512_1_.func_175704_b(blockpos, p_176512_2_);
            p_176512_1_.func_180498_a((EntityPlayer)null, p_176512_3_?1003:1006, p_176512_2_, 0);
         }

      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(p_176204_3_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER) {
         BlockPos blockpos = p_176204_2_.func_177977_b();
         IBlockState iblockstate = p_176204_1_.func_180495_p(blockpos);
         if(iblockstate.func_177230_c() != this) {
            p_176204_1_.func_175698_g(p_176204_2_);
         } else if(p_176204_4_ != this) {
            this.func_176204_a(p_176204_1_, blockpos, iblockstate, p_176204_4_);
         }
      } else {
         boolean flag1 = false;
         BlockPos blockpos1 = p_176204_2_.func_177984_a();
         IBlockState iblockstate1 = p_176204_1_.func_180495_p(blockpos1);
         if(iblockstate1.func_177230_c() != this) {
            p_176204_1_.func_175698_g(p_176204_2_);
            flag1 = true;
         }

         if(!World.func_175683_a(p_176204_1_, p_176204_2_.func_177977_b())) {
            p_176204_1_.func_175698_g(p_176204_2_);
            flag1 = true;
            if(iblockstate1.func_177230_c() == this) {
               p_176204_1_.func_175698_g(blockpos1);
            }
         }

         if(flag1) {
            if(!p_176204_1_.field_72995_K) {
               this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
            }
         } else {
            boolean flag = p_176204_1_.func_175640_z(p_176204_2_) || p_176204_1_.func_175640_z(blockpos1);
            if((flag || p_176204_4_.func_149744_f()) && p_176204_4_ != this && flag != ((Boolean)iblockstate1.func_177229_b(field_176522_N)).booleanValue()) {
               p_176204_1_.func_180501_a(blockpos1, iblockstate1.func_177226_a(field_176522_N, Boolean.valueOf(flag)), 2);
               if(flag != ((Boolean)p_176204_3_.func_177229_b(field_176519_b)).booleanValue()) {
                  p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176519_b, Boolean.valueOf(flag)), 2);
                  p_176204_1_.func_175704_b(p_176204_2_, p_176204_2_);
                  p_176204_1_.func_180498_a((EntityPlayer)null, flag?1003:1006, p_176204_2_, 0);
               }
            }
         }
      }

   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return p_180660_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER?null:this.func_176509_j();
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      this.func_180654_a(p_180636_1_, p_180636_2_);
      return super.func_180636_a(p_180636_1_, p_180636_2_, p_180636_3_, p_180636_4_);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return p_176196_2_.func_177956_o() >= 255?false:World.func_175683_a(p_176196_1_, p_176196_2_.func_177977_b()) && super.func_176196_c(p_176196_1_, p_176196_2_) && super.func_176196_c(p_176196_1_, p_176196_2_.func_177984_a());
   }

   public int func_149656_h() {
      return 1;
   }

   public static int func_176515_e(IBlockAccess p_176515_0_, BlockPos p_176515_1_) {
      IBlockState iblockstate = p_176515_0_.func_180495_p(p_176515_1_);
      int i = iblockstate.func_177230_c().func_176201_c(iblockstate);
      boolean flag = func_176518_i(i);
      IBlockState iblockstate1 = p_176515_0_.func_180495_p(p_176515_1_.func_177977_b());
      int j = iblockstate1.func_177230_c().func_176201_c(iblockstate1);
      int k = flag?j:i;
      IBlockState iblockstate2 = p_176515_0_.func_180495_p(p_176515_1_.func_177984_a());
      int l = iblockstate2.func_177230_c().func_176201_c(iblockstate2);
      int i1 = flag?i:l;
      boolean flag1 = (i1 & 1) != 0;
      boolean flag2 = (i1 & 2) != 0;
      return func_176510_b(k) | (flag?8:0) | (flag1?16:0) | (flag2?32:0);
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return this.func_176509_j();
   }

   private Item func_176509_j() {
      return this == Blocks.field_150454_av?Items.field_151139_aw:(this == Blocks.field_180414_ap?Items.field_179569_ar:(this == Blocks.field_180412_aq?Items.field_179568_as:(this == Blocks.field_180411_ar?Items.field_179567_at:(this == Blocks.field_180410_as?Items.field_179572_au:(this == Blocks.field_180409_at?Items.field_179571_av:Items.field_179570_aq)))));
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      BlockPos blockpos = p_176208_2_.func_177977_b();
      if(p_176208_4_.field_71075_bZ.field_75098_d && p_176208_3_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER && p_176208_1_.func_180495_p(blockpos).func_177230_c() == this) {
         p_176208_1_.func_175698_g(blockpos);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      if(p_176221_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.LOWER) {
         IBlockState iblockstate = p_176221_2_.func_180495_p(p_176221_3_.func_177984_a());
         if(iblockstate.func_177230_c() == this) {
            p_176221_1_ = p_176221_1_.func_177226_a(field_176521_M, iblockstate.func_177229_b(field_176521_M)).func_177226_a(field_176522_N, iblockstate.func_177229_b(field_176522_N));
         }
      } else {
         IBlockState iblockstate1 = p_176221_2_.func_180495_p(p_176221_3_.func_177977_b());
         if(iblockstate1.func_177230_c() == this) {
            p_176221_1_ = p_176221_1_.func_177226_a(field_176520_a, iblockstate1.func_177229_b(field_176520_a)).func_177226_a(field_176519_b, iblockstate1.func_177229_b(field_176519_b));
         }
      }

      return p_176221_1_;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return (p_176203_1_ & 8) > 0?this.func_176223_P().func_177226_a(field_176523_O, BlockDoor.EnumDoorHalf.UPPER).func_177226_a(field_176521_M, (p_176203_1_ & 1) > 0?BlockDoor.EnumHingePosition.RIGHT:BlockDoor.EnumHingePosition.LEFT).func_177226_a(field_176522_N, Boolean.valueOf((p_176203_1_ & 2) > 0)):this.func_176223_P().func_177226_a(field_176523_O, BlockDoor.EnumDoorHalf.LOWER).func_177226_a(field_176520_a, EnumFacing.func_176731_b(p_176203_1_ & 3).func_176735_f()).func_177226_a(field_176519_b, Boolean.valueOf((p_176203_1_ & 4) > 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      if(p_176201_1_.func_177229_b(field_176523_O) == BlockDoor.EnumDoorHalf.UPPER) {
         i = i | 8;
         if(p_176201_1_.func_177229_b(field_176521_M) == BlockDoor.EnumHingePosition.RIGHT) {
            i |= 1;
         }

         if(((Boolean)p_176201_1_.func_177229_b(field_176522_N)).booleanValue()) {
            i |= 2;
         }
      } else {
         i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176520_a)).func_176746_e().func_176736_b();
         if(((Boolean)p_176201_1_.func_177229_b(field_176519_b)).booleanValue()) {
            i |= 4;
         }
      }

      return i;
   }

   protected static int func_176510_b(int p_176510_0_) {
      return p_176510_0_ & 7;
   }

   public static boolean func_176514_f(IBlockAccess p_176514_0_, BlockPos p_176514_1_) {
      return func_176516_g(func_176515_e(p_176514_0_, p_176514_1_));
   }

   public static EnumFacing func_176517_h(IBlockAccess p_176517_0_, BlockPos p_176517_1_) {
      return func_176511_f(func_176515_e(p_176517_0_, p_176517_1_));
   }

   public static EnumFacing func_176511_f(int p_176511_0_) {
      return EnumFacing.func_176731_b(p_176511_0_ & 3).func_176735_f();
   }

   protected static boolean func_176516_g(int p_176516_0_) {
      return (p_176516_0_ & 4) != 0;
   }

   protected static boolean func_176518_i(int p_176518_0_) {
      return (p_176518_0_ & 8) != 0;
   }

   protected static boolean func_176513_j(int p_176513_0_) {
      return (p_176513_0_ & 16) != 0;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176523_O, field_176520_a, field_176519_b, field_176521_M, field_176522_N});
   }

   public static enum EnumDoorHalf implements IStringSerializable {
      UPPER,
      LOWER;

      public String toString() {
         return this.func_176610_l();
      }

      public String func_176610_l() {
         return this == UPPER?"upper":"lower";
      }
   }

   public static enum EnumHingePosition implements IStringSerializable {
      LEFT,
      RIGHT;

      public String toString() {
         return this.func_176610_l();
      }

      public String func_176610_l() {
         return this == LEFT?"left":"right";
      }
   }
}
