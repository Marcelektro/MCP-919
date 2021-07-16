package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFootStepFX extends EntityFX {
   private static final ResourceLocation field_110126_a = new ResourceLocation("textures/particle/footprint.png");
   private int field_70576_a;
   private int field_70578_aq;
   private TextureManager field_70577_ar;

   protected EntityFootStepFX(TextureManager p_i1210_1_, World p_i1210_2_, double p_i1210_3_, double p_i1210_5_, double p_i1210_7_) {
      super(p_i1210_2_, p_i1210_3_, p_i1210_5_, p_i1210_7_, 0.0D, 0.0D, 0.0D);
      this.field_70577_ar = p_i1210_1_;
      this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
      this.field_70578_aq = 200;
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = ((float)this.field_70576_a + p_180434_3_) / (float)this.field_70578_aq;
      f = f * f;
      float f1 = 2.0F - f * 2.0F;
      if(f1 > 1.0F) {
         f1 = 1.0F;
      }

      f1 = f1 * 0.2F;
      GlStateManager.func_179140_f();
      float f2 = 0.125F;
      float f3 = (float)(this.field_70165_t - field_70556_an);
      float f4 = (float)(this.field_70163_u - field_70554_ao);
      float f5 = (float)(this.field_70161_v - field_70555_ap);
      float f6 = this.field_70170_p.func_175724_o(new BlockPos(this));
      this.field_70577_ar.func_110577_a(field_110126_a);
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(770, 771);
      p_180434_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      p_180434_1_.func_181662_b((double)(f3 - 0.125F), (double)f4, (double)(f5 + 0.125F)).func_181673_a(0.0D, 1.0D).func_181666_a(f6, f6, f6, f1).func_181675_d();
      p_180434_1_.func_181662_b((double)(f3 + 0.125F), (double)f4, (double)(f5 + 0.125F)).func_181673_a(1.0D, 1.0D).func_181666_a(f6, f6, f6, f1).func_181675_d();
      p_180434_1_.func_181662_b((double)(f3 + 0.125F), (double)f4, (double)(f5 - 0.125F)).func_181673_a(1.0D, 0.0D).func_181666_a(f6, f6, f6, f1).func_181675_d();
      p_180434_1_.func_181662_b((double)(f3 - 0.125F), (double)f4, (double)(f5 - 0.125F)).func_181673_a(0.0D, 0.0D).func_181666_a(f6, f6, f6, f1).func_181675_d();
      Tessellator.func_178181_a().func_78381_a();
      GlStateManager.func_179084_k();
      GlStateManager.func_179145_e();
   }

   public void func_70071_h_() {
      ++this.field_70576_a;
      if(this.field_70576_a == this.field_70578_aq) {
         this.func_70106_y();
      }

   }

   public int func_70537_b() {
      return 3;
   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityFootStepFX(Minecraft.func_71410_x().func_110434_K(), p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_);
      }
   }
}
