package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class VboChunkFactory implements IRenderChunkFactory {
   public RenderChunk func_178602_a(World p_178602_1_, RenderGlobal p_178602_2_, BlockPos p_178602_3_, int p_178602_4_) {
      return new RenderChunk(p_178602_1_, p_178602_2_, p_178602_3_, p_178602_4_);
   }
}
