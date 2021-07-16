package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public class BlockHugeMushroom extends Block {
   public static final PropertyEnum<BlockHugeMushroom.EnumType> field_176380_a = PropertyEnum.<BlockHugeMushroom.EnumType>func_177709_a("variant", BlockHugeMushroom.EnumType.class);
   private final Block field_176379_b;

   public BlockHugeMushroom(Material p_i46392_1_, MapColor p_i46392_2_, Block p_i46392_3_) {
      super(p_i46392_1_, p_i46392_2_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176380_a, BlockHugeMushroom.EnumType.ALL_OUTSIDE));
      this.field_176379_b = p_i46392_3_;
   }

   public int func_149745_a(Random p_149745_1_) {
      return Math.max(0, p_149745_1_.nextInt(10) - 7);
   }

   public MapColor func_180659_g(IBlockState p_180659_1_) {
      switch((BlockHugeMushroom.EnumType)p_180659_1_.func_177229_b(field_176380_a)) {
      case ALL_STEM:
         return MapColor.field_151659_e;
      case ALL_INSIDE:
         return MapColor.field_151658_d;
      case STEM:
         return MapColor.field_151658_d;
      default:
         return super.func_180659_g(p_180659_1_);
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(this.field_176379_b);
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Item.func_150898_a(this.field_176379_b);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P();
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176380_a, BlockHugeMushroom.EnumType.func_176895_a(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((BlockHugeMushroom.EnumType)p_176201_1_.func_177229_b(field_176380_a)).func_176896_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176380_a});
   }

   public static enum EnumType implements IStringSerializable {
      NORTH_WEST(1, "north_west"),
      NORTH(2, "north"),
      NORTH_EAST(3, "north_east"),
      WEST(4, "west"),
      CENTER(5, "center"),
      EAST(6, "east"),
      SOUTH_WEST(7, "south_west"),
      SOUTH(8, "south"),
      SOUTH_EAST(9, "south_east"),
      STEM(10, "stem"),
      ALL_INSIDE(0, "all_inside"),
      ALL_OUTSIDE(14, "all_outside"),
      ALL_STEM(15, "all_stem");

      private static final BlockHugeMushroom.EnumType[] field_176905_n = new BlockHugeMushroom.EnumType[16];
      private final int field_176906_o;
      private final String field_176914_p;

      private EnumType(int p_i45710_3_, String p_i45710_4_) {
         this.field_176906_o = p_i45710_3_;
         this.field_176914_p = p_i45710_4_;
      }

      public int func_176896_a() {
         return this.field_176906_o;
      }

      public String toString() {
         return this.field_176914_p;
      }

      public static BlockHugeMushroom.EnumType func_176895_a(int p_176895_0_) {
         if(p_176895_0_ < 0 || p_176895_0_ >= field_176905_n.length) {
            p_176895_0_ = 0;
         }

         BlockHugeMushroom.EnumType blockhugemushroom$enumtype = field_176905_n[p_176895_0_];
         return blockhugemushroom$enumtype == null?field_176905_n[0]:blockhugemushroom$enumtype;
      }

      public String func_176610_l() {
         return this.field_176914_p;
      }

      static {
         for(BlockHugeMushroom.EnumType blockhugemushroom$enumtype : values()) {
            field_176905_n[blockhugemushroom$enumtype.func_176896_a()] = blockhugemushroom$enumtype;
         }

      }
   }
}
