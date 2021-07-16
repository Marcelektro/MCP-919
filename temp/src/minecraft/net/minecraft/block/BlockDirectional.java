package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public abstract class BlockDirectional extends Block {
   public static final PropertyDirection field_176387_N = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);

   protected BlockDirectional(Material p_i45401_1_) {
      super(p_i45401_1_);
   }

   protected BlockDirectional(Material p_i46398_1_, MapColor p_i46398_2_) {
      super(p_i46398_1_, p_i46398_2_);
   }
}
