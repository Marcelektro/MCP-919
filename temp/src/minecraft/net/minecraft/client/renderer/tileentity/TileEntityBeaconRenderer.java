package net.minecraft.client.renderer.tileentity;

import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityBeaconRenderer extends TileEntitySpecialRenderer<TileEntityBeacon> {
   private static final ResourceLocation field_147523_b = new ResourceLocation("textures/entity/beacon_beam.png");

   public void func_180535_a(TileEntityBeacon p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      float f = p_180535_1_.func_146002_i();
      GlStateManager.func_179092_a(516, 0.1F);
      if(f > 0.0F) {
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         GlStateManager.func_179106_n();
         List<TileEntityBeacon.BeamSegment> list = p_180535_1_.func_174907_n();
         int i = 0;

         for(int j = 0; j < list.size(); ++j) {
            TileEntityBeacon.BeamSegment tileentitybeacon$beamsegment = (TileEntityBeacon.BeamSegment)list.get(j);
            int k = i + tileentitybeacon$beamsegment.func_177264_c();
            this.func_147499_a(field_147523_b);
            GL11.glTexParameterf(3553, 10242, 10497.0F);
            GL11.glTexParameterf(3553, 10243, 10497.0F);
            GlStateManager.func_179140_f();
            GlStateManager.func_179129_p();
            GlStateManager.func_179084_k();
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179120_a(770, 1, 1, 0);
            double d0 = (double)p_180535_1_.func_145831_w().func_82737_E() + (double)p_180535_8_;
            double d1 = MathHelper.func_181162_h(-d0 * 0.2D - (double)MathHelper.func_76128_c(-d0 * 0.1D));
            float f1 = tileentitybeacon$beamsegment.func_177263_b()[0];
            float f2 = tileentitybeacon$beamsegment.func_177263_b()[1];
            float f3 = tileentitybeacon$beamsegment.func_177263_b()[2];
            double d2 = d0 * 0.025D * -1.5D;
            double d3 = 0.2D;
            double d4 = 0.5D + Math.cos(d2 + 2.356194490192345D) * 0.2D;
            double d5 = 0.5D + Math.sin(d2 + 2.356194490192345D) * 0.2D;
            double d6 = 0.5D + Math.cos(d2 + 0.7853981633974483D) * 0.2D;
            double d7 = 0.5D + Math.sin(d2 + 0.7853981633974483D) * 0.2D;
            double d8 = 0.5D + Math.cos(d2 + 3.9269908169872414D) * 0.2D;
            double d9 = 0.5D + Math.sin(d2 + 3.9269908169872414D) * 0.2D;
            double d10 = 0.5D + Math.cos(d2 + 5.497787143782138D) * 0.2D;
            double d11 = 0.5D + Math.sin(d2 + 5.497787143782138D) * 0.2D;
            double d12 = 0.0D;
            double d13 = 1.0D;
            double d14 = -1.0D + d1;
            double d15 = (double)((float)tileentitybeacon$beamsegment.func_177264_c() * f) * 2.5D + d14;
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            worldrenderer.func_181662_b(p_180535_2_ + d4, p_180535_4_ + (double)k, p_180535_6_ + d5).func_181673_a(1.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d4, p_180535_4_ + (double)i, p_180535_6_ + d5).func_181673_a(1.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d6, p_180535_4_ + (double)i, p_180535_6_ + d7).func_181673_a(0.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d6, p_180535_4_ + (double)k, p_180535_6_ + d7).func_181673_a(0.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d10, p_180535_4_ + (double)k, p_180535_6_ + d11).func_181673_a(1.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d10, p_180535_4_ + (double)i, p_180535_6_ + d11).func_181673_a(1.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d8, p_180535_4_ + (double)i, p_180535_6_ + d9).func_181673_a(0.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d8, p_180535_4_ + (double)k, p_180535_6_ + d9).func_181673_a(0.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d6, p_180535_4_ + (double)k, p_180535_6_ + d7).func_181673_a(1.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d6, p_180535_4_ + (double)i, p_180535_6_ + d7).func_181673_a(1.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d10, p_180535_4_ + (double)i, p_180535_6_ + d11).func_181673_a(0.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d10, p_180535_4_ + (double)k, p_180535_6_ + d11).func_181673_a(0.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d8, p_180535_4_ + (double)k, p_180535_6_ + d9).func_181673_a(1.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d8, p_180535_4_ + (double)i, p_180535_6_ + d9).func_181673_a(1.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d4, p_180535_4_ + (double)i, p_180535_6_ + d5).func_181673_a(0.0D, d14).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + d4, p_180535_4_ + (double)k, p_180535_6_ + d5).func_181673_a(0.0D, d15).func_181666_a(f1, f2, f3, 1.0F).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a(770, 771, 1, 0);
            GlStateManager.func_179132_a(false);
            d2 = 0.2D;
            d3 = 0.2D;
            d4 = 0.8D;
            d5 = 0.2D;
            d6 = 0.2D;
            d7 = 0.8D;
            d8 = 0.8D;
            d9 = 0.8D;
            d10 = 0.0D;
            d11 = 1.0D;
            d12 = -1.0D + d1;
            d13 = (double)((float)tileentitybeacon$beamsegment.func_177264_c() * f) + d12;
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)k, p_180535_6_ + 0.2D).func_181673_a(1.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)i, p_180535_6_ + 0.2D).func_181673_a(1.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)i, p_180535_6_ + 0.2D).func_181673_a(0.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)k, p_180535_6_ + 0.2D).func_181673_a(0.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)k, p_180535_6_ + 0.8D).func_181673_a(1.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)i, p_180535_6_ + 0.8D).func_181673_a(1.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)i, p_180535_6_ + 0.8D).func_181673_a(0.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)k, p_180535_6_ + 0.8D).func_181673_a(0.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)k, p_180535_6_ + 0.2D).func_181673_a(1.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)i, p_180535_6_ + 0.2D).func_181673_a(1.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)i, p_180535_6_ + 0.8D).func_181673_a(0.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.8D, p_180535_4_ + (double)k, p_180535_6_ + 0.8D).func_181673_a(0.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)k, p_180535_6_ + 0.8D).func_181673_a(1.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)i, p_180535_6_ + 0.8D).func_181673_a(1.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)i, p_180535_6_ + 0.2D).func_181673_a(0.0D, d12).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            worldrenderer.func_181662_b(p_180535_2_ + 0.2D, p_180535_4_ + (double)k, p_180535_6_ + 0.2D).func_181673_a(0.0D, d13).func_181666_a(f1, f2, f3, 0.125F).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179145_e();
            GlStateManager.func_179098_w();
            GlStateManager.func_179132_a(true);
            i = k;
         }

         GlStateManager.func_179127_m();
      }

   }

   public boolean func_181055_a() {
      return true;
   }
}
