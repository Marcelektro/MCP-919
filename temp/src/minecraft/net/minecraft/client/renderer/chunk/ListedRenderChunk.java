package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class ListedRenderChunk extends RenderChunk {
   private final int field_178601_d = GLAllocation.func_74526_a(EnumWorldBlockLayer.values().length);

   public ListedRenderChunk(World p_i46198_1_, RenderGlobal p_i46198_2_, BlockPos p_i46198_3_, int p_i46198_4_) {
      super(p_i46198_1_, p_i46198_2_, p_i46198_3_, p_i46198_4_);
   }

   public int func_178600_a(EnumWorldBlockLayer p_178600_1_, CompiledChunk p_178600_2_) {
      return !p_178600_2_.func_178491_b(p_178600_1_)?this.field_178601_d + p_178600_1_.ordinal():-1;
   }

   public void func_178566_a() {
      super.func_178566_a();
      GLAllocation.func_178874_a(this.field_178601_d, EnumWorldBlockLayer.values().length);
   }
}
