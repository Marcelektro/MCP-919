package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderFallingBlock extends Render<EntityFallingBlock> {
   public RenderFallingBlock(RenderManager p_i46177_1_) {
      super(p_i46177_1_);
      this.field_76989_e = 0.5F;
   }

   public void func_76986_a(EntityFallingBlock p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if(p_76986_1_.func_175131_l() != null) {
         this.func_110776_a(TextureMap.field_110575_b);
         IBlockState iblockstate = p_76986_1_.func_175131_l();
         Block block = iblockstate.func_177230_c();
         BlockPos blockpos = new BlockPos(p_76986_1_);
         World world = p_76986_1_.func_145807_e();
         if(iblockstate != world.func_180495_p(blockpos) && block.func_149645_b() != -1) {
            if(block.func_149645_b() == 3) {
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
               GlStateManager.func_179140_f();
               Tessellator tessellator = Tessellator.func_178181_a();
               WorldRenderer worldrenderer = tessellator.func_178180_c();
               worldrenderer.func_181668_a(7, DefaultVertexFormats.field_176600_a);
               int i = blockpos.func_177958_n();
               int j = blockpos.func_177956_o();
               int k = blockpos.func_177952_p();
               worldrenderer.func_178969_c((double)((float)(-i) - 0.5F), (double)(-j), (double)((float)(-k) - 0.5F));
               BlockRendererDispatcher blockrendererdispatcher = Minecraft.func_71410_x().func_175602_ab();
               IBakedModel ibakedmodel = blockrendererdispatcher.func_175022_a(iblockstate, world, (BlockPos)null);
               blockrendererdispatcher.func_175019_b().func_178267_a(world, ibakedmodel, iblockstate, blockpos, worldrenderer, false);
               worldrenderer.func_178969_c(0.0D, 0.0D, 0.0D);
               tessellator.func_78381_a();
               GlStateManager.func_179145_e();
               GlStateManager.func_179121_F();
               super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
            }
         }
      }
   }

   protected ResourceLocation func_110775_a(EntityFallingBlock p_110775_1_) {
      return TextureMap.field_110575_b;
   }
}
