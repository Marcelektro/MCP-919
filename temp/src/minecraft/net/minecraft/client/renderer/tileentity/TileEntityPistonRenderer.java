package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TileEntityPistonRenderer extends TileEntitySpecialRenderer<TileEntityPiston> {
   private final BlockRendererDispatcher field_178462_c = Minecraft.func_71410_x().func_175602_ab();

   public void func_180535_a(TileEntityPiston p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      BlockPos blockpos = p_180535_1_.func_174877_v();
      IBlockState iblockstate = p_180535_1_.func_174927_b();
      Block block = iblockstate.func_177230_c();
      if(block.func_149688_o() != Material.field_151579_a && p_180535_1_.func_145860_a(p_180535_8_) < 1.0F) {
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         this.func_147499_a(TextureMap.field_110575_b);
         RenderHelper.func_74518_a();
         GlStateManager.func_179112_b(770, 771);
         GlStateManager.func_179147_l();
         GlStateManager.func_179129_p();
         if(Minecraft.func_71379_u()) {
            GlStateManager.func_179103_j(7425);
         } else {
            GlStateManager.func_179103_j(7424);
         }

         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_176600_a);
         worldrenderer.func_178969_c((double)((float)p_180535_2_ - (float)blockpos.func_177958_n() + p_180535_1_.func_174929_b(p_180535_8_)), (double)((float)p_180535_4_ - (float)blockpos.func_177956_o() + p_180535_1_.func_174928_c(p_180535_8_)), (double)((float)p_180535_6_ - (float)blockpos.func_177952_p() + p_180535_1_.func_174926_d(p_180535_8_)));
         World world = this.func_178459_a();
         if(block == Blocks.field_150332_K && p_180535_1_.func_145860_a(p_180535_8_) < 0.5F) {
            iblockstate = iblockstate.func_177226_a(BlockPistonExtension.field_176327_M, Boolean.valueOf(true));
            this.field_178462_c.func_175019_b().func_178267_a(world, this.field_178462_c.func_175022_a(iblockstate, world, blockpos), iblockstate, blockpos, worldrenderer, true);
         } else if(p_180535_1_.func_145867_d() && !p_180535_1_.func_145868_b()) {
            BlockPistonExtension.EnumPistonType blockpistonextension$enumpistontype = block == Blocks.field_150320_F?BlockPistonExtension.EnumPistonType.STICKY:BlockPistonExtension.EnumPistonType.DEFAULT;
            IBlockState iblockstate1 = Blocks.field_150332_K.func_176223_P().func_177226_a(BlockPistonExtension.field_176325_b, blockpistonextension$enumpistontype).func_177226_a(BlockPistonExtension.field_176326_a, iblockstate.func_177229_b(BlockPistonBase.field_176321_a));
            iblockstate1 = iblockstate1.func_177226_a(BlockPistonExtension.field_176327_M, Boolean.valueOf(p_180535_1_.func_145860_a(p_180535_8_) >= 0.5F));
            this.field_178462_c.func_175019_b().func_178267_a(world, this.field_178462_c.func_175022_a(iblockstate1, world, blockpos), iblockstate1, blockpos, worldrenderer, true);
            worldrenderer.func_178969_c((double)((float)p_180535_2_ - (float)blockpos.func_177958_n()), (double)((float)p_180535_4_ - (float)blockpos.func_177956_o()), (double)((float)p_180535_6_ - (float)blockpos.func_177952_p()));
            iblockstate.func_177226_a(BlockPistonBase.field_176320_b, Boolean.valueOf(true));
            this.field_178462_c.func_175019_b().func_178267_a(world, this.field_178462_c.func_175022_a(iblockstate, world, blockpos), iblockstate, blockpos, worldrenderer, true);
         } else {
            this.field_178462_c.func_175019_b().func_178267_a(world, this.field_178462_c.func_175022_a(iblockstate, world, blockpos), iblockstate, blockpos, worldrenderer, false);
         }

         worldrenderer.func_178969_c(0.0D, 0.0D, 0.0D);
         tessellator.func_78381_a();
         RenderHelper.func_74519_b();
      }
   }
}
