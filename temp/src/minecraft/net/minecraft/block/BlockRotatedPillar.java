package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public abstract class BlockRotatedPillar extends Block {
   public static final PropertyEnum<EnumFacing.Axis> field_176298_M = PropertyEnum.<EnumFacing.Axis>func_177709_a("axis", EnumFacing.Axis.class);

   protected BlockRotatedPillar(Material p_i45425_1_) {
      super(p_i45425_1_, p_i45425_1_.func_151565_r());
   }

   protected BlockRotatedPillar(Material p_i46385_1_, MapColor p_i46385_2_) {
      super(p_i46385_1_, p_i46385_2_);
   }
}
