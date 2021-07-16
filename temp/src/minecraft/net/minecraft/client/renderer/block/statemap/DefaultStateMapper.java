package net.minecraft.client.renderer.block.statemap;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class DefaultStateMapper extends StateMapperBase {
   protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
      return new ModelResourceLocation((ResourceLocation)Block.field_149771_c.func_177774_c(p_178132_1_.func_177230_c()), this.func_178131_a(p_178132_1_.func_177228_b()));
   }
}
