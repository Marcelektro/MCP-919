package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public class BlockQuartz extends Block {
   public static final PropertyEnum<BlockQuartz.EnumType> field_176335_a = PropertyEnum.<BlockQuartz.EnumType>func_177709_a("variant", BlockQuartz.EnumType.class);

   public BlockQuartz() {
      super(Material.field_151576_e);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176335_a, BlockQuartz.EnumType.DEFAULT));
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      if(p_180642_7_ == BlockQuartz.EnumType.LINES_Y.func_176796_a()) {
         switch(p_180642_3_.func_176740_k()) {
         case Z:
            return this.func_176223_P().func_177226_a(field_176335_a, BlockQuartz.EnumType.LINES_Z);
         case X:
            return this.func_176223_P().func_177226_a(field_176335_a, BlockQuartz.EnumType.LINES_X);
         case Y:
         default:
            return this.func_176223_P().func_177226_a(field_176335_a, BlockQuartz.EnumType.LINES_Y);
         }
      } else {
         return p_180642_7_ == BlockQuartz.EnumType.CHISELED.func_176796_a()?this.func_176223_P().func_177226_a(field_176335_a, BlockQuartz.EnumType.CHISELED):this.func_176223_P().func_177226_a(field_176335_a, BlockQuartz.EnumType.DEFAULT);
      }
   }

   public int func_180651_a(IBlockState p_180651_1_) {
      BlockQuartz.EnumType blockquartz$enumtype = (BlockQuartz.EnumType)p_180651_1_.func_177229_b(field_176335_a);
      return blockquartz$enumtype != BlockQuartz.EnumType.LINES_X && blockquartz$enumtype != BlockQuartz.EnumType.LINES_Z?blockquartz$enumtype.func_176796_a():BlockQuartz.EnumType.LINES_Y.func_176796_a();
   }

   protected ItemStack func_180643_i(IBlockState p_180643_1_) {
      BlockQuartz.EnumType blockquartz$enumtype = (BlockQuartz.EnumType)p_180643_1_.func_177229_b(field_176335_a);
      return blockquartz$enumtype != BlockQuartz.EnumType.LINES_X && blockquartz$enumtype != BlockQuartz.EnumType.LINES_Z?super.func_180643_i(p_180643_1_):new ItemStack(Item.func_150898_a(this), 1, BlockQuartz.EnumType.LINES_Y.func_176796_a());
   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockQuartz.EnumType.DEFAULT.func_176796_a()));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockQuartz.EnumType.CHISELED.func_176796_a()));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, BlockQuartz.EnumType.LINES_Y.func_176796_a()));
   }

   public MapColor func_180659_g(IBlockState p_180659_1_) {
      return MapColor.field_151677_p;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176335_a, BlockQuartz.EnumType.func_176794_a(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((BlockQuartz.EnumType)p_176201_1_.func_177229_b(field_176335_a)).func_176796_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176335_a});
   }

   public static enum EnumType implements IStringSerializable {
      DEFAULT(0, "default", "default"),
      CHISELED(1, "chiseled", "chiseled"),
      LINES_Y(2, "lines_y", "lines"),
      LINES_X(3, "lines_x", "lines"),
      LINES_Z(4, "lines_z", "lines");

      private static final BlockQuartz.EnumType[] field_176797_f = new BlockQuartz.EnumType[values().length];
      private final int field_176798_g;
      private final String field_176805_h;
      private final String field_176806_i;

      private EnumType(int p_i45691_3_, String p_i45691_4_, String p_i45691_5_) {
         this.field_176798_g = p_i45691_3_;
         this.field_176805_h = p_i45691_4_;
         this.field_176806_i = p_i45691_5_;
      }

      public int func_176796_a() {
         return this.field_176798_g;
      }

      public String toString() {
         return this.field_176806_i;
      }

      public static BlockQuartz.EnumType func_176794_a(int p_176794_0_) {
         if(p_176794_0_ < 0 || p_176794_0_ >= field_176797_f.length) {
            p_176794_0_ = 0;
         }

         return field_176797_f[p_176794_0_];
      }

      public String func_176610_l() {
         return this.field_176805_h;
      }

      static {
         for(BlockQuartz.EnumType blockquartz$enumtype : values()) {
            field_176797_f[blockquartz$enumtype.func_176796_a()] = blockquartz$enumtype;
         }

      }
   }
}
