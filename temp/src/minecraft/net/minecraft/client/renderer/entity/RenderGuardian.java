package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderGuardian extends RenderLiving<EntityGuardian> {
   private static final ResourceLocation field_177114_e = new ResourceLocation("textures/entity/guardian.png");
   private static final ResourceLocation field_177116_j = new ResourceLocation("textures/entity/guardian_elder.png");
   private static final ResourceLocation field_177117_k = new ResourceLocation("textures/entity/guardian_beam.png");
   int field_177115_a;

   public RenderGuardian(RenderManager p_i46171_1_) {
      super(p_i46171_1_, new ModelGuardian(), 0.5F);
      this.field_177115_a = ((ModelGuardian)this.field_77045_g).func_178706_a();
   }

   public boolean func_177071_a(EntityGuardian p_177071_1_, ICamera p_177071_2_, double p_177071_3_, double p_177071_5_, double p_177071_7_) {
      if(super.func_177071_a(p_177071_1_, p_177071_2_, p_177071_3_, p_177071_5_, p_177071_7_)) {
         return true;
      } else {
         if(p_177071_1_.func_175474_cn()) {
            EntityLivingBase entitylivingbase = p_177071_1_.func_175466_co();
            if(entitylivingbase != null) {
               Vec3 vec3 = this.func_177110_a(entitylivingbase, (double)entitylivingbase.field_70131_O * 0.5D, 1.0F);
               Vec3 vec31 = this.func_177110_a(p_177071_1_, (double)p_177071_1_.func_70047_e(), 1.0F);
               if(p_177071_2_.func_78546_a(AxisAlignedBB.func_178781_a(vec31.field_72450_a, vec31.field_72448_b, vec31.field_72449_c, vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private Vec3 func_177110_a(EntityLivingBase p_177110_1_, double p_177110_2_, float p_177110_4_) {
      double d0 = p_177110_1_.field_70142_S + (p_177110_1_.field_70165_t - p_177110_1_.field_70142_S) * (double)p_177110_4_;
      double d1 = p_177110_2_ + p_177110_1_.field_70137_T + (p_177110_1_.field_70163_u - p_177110_1_.field_70137_T) * (double)p_177110_4_;
      double d2 = p_177110_1_.field_70136_U + (p_177110_1_.field_70161_v - p_177110_1_.field_70136_U) * (double)p_177110_4_;
      return new Vec3(d0, d1, d2);
   }

   public void func_76986_a(EntityGuardian p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if(this.field_177115_a != ((ModelGuardian)this.field_77045_g).func_178706_a()) {
         this.field_77045_g = new ModelGuardian();
         this.field_177115_a = ((ModelGuardian)this.field_77045_g).func_178706_a();
      }

      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
      EntityLivingBase entitylivingbase = p_76986_1_.func_175466_co();
      if(entitylivingbase != null) {
         float f = p_76986_1_.func_175477_p(p_76986_9_);
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         this.func_110776_a(field_177117_k);
         GL11.glTexParameterf(3553, 10242, 10497.0F);
         GL11.glTexParameterf(3553, 10243, 10497.0F);
         GlStateManager.func_179140_f();
         GlStateManager.func_179129_p();
         GlStateManager.func_179084_k();
         GlStateManager.func_179132_a(true);
         float f1 = 240.0F;
         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, f1, f1);
         GlStateManager.func_179120_a(770, 1, 1, 0);
         float f2 = (float)p_76986_1_.field_70170_p.func_82737_E() + p_76986_9_;
         float f3 = f2 * 0.5F % 1.0F;
         float f4 = p_76986_1_.func_70047_e();
         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_ + f4, (float)p_76986_6_);
         Vec3 vec3 = this.func_177110_a(entitylivingbase, (double)entitylivingbase.field_70131_O * 0.5D, p_76986_9_);
         Vec3 vec31 = this.func_177110_a(p_76986_1_, (double)f4, p_76986_9_);
         Vec3 vec32 = vec3.func_178788_d(vec31);
         double d0 = vec32.func_72433_c() + 1.0D;
         vec32 = vec32.func_72432_b();
         float f5 = (float)Math.acos(vec32.field_72448_b);
         float f6 = (float)Math.atan2(vec32.field_72449_c, vec32.field_72450_a);
         GlStateManager.func_179114_b((1.5707964F + -f6) * 57.295776F, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(f5 * 57.295776F, 1.0F, 0.0F, 0.0F);
         int i = 1;
         double d1 = (double)f2 * 0.05D * (1.0D - (double)(i & 1) * 2.5D);
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         float f7 = f * f;
         int j = 64 + (int)(f7 * 240.0F);
         int k = 32 + (int)(f7 * 192.0F);
         int l = 128 - (int)(f7 * 64.0F);
         double d2 = (double)i * 0.2D;
         double d3 = d2 * 1.41D;
         double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * d3;
         double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * d3;
         double d6 = 0.0D + Math.cos(d1 + 0.7853981633974483D) * d3;
         double d7 = 0.0D + Math.sin(d1 + 0.7853981633974483D) * d3;
         double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * d3;
         double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * d3;
         double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * d3;
         double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * d3;
         double d12 = 0.0D + Math.cos(d1 + 3.141592653589793D) * d2;
         double d13 = 0.0D + Math.sin(d1 + 3.141592653589793D) * d2;
         double d14 = 0.0D + Math.cos(d1 + 0.0D) * d2;
         double d15 = 0.0D + Math.sin(d1 + 0.0D) * d2;
         double d16 = 0.0D + Math.cos(d1 + 1.5707963267948966D) * d2;
         double d17 = 0.0D + Math.sin(d1 + 1.5707963267948966D) * d2;
         double d18 = 0.0D + Math.cos(d1 + 4.71238898038469D) * d2;
         double d19 = 0.0D + Math.sin(d1 + 4.71238898038469D) * d2;
         double d20 = 0.0D;
         double d21 = 0.4999D;
         double d22 = (double)(-1.0F + f3);
         double d23 = d0 * (0.5D / d2) + d22;
         worldrenderer.func_181662_b(d12, d0, d13).func_181673_a(0.4999D, d23).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d12, 0.0D, d13).func_181673_a(0.4999D, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d14, 0.0D, d15).func_181673_a(0.0D, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d14, d0, d15).func_181673_a(0.0D, d23).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d16, d0, d17).func_181673_a(0.4999D, d23).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d16, 0.0D, d17).func_181673_a(0.4999D, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d18, 0.0D, d19).func_181673_a(0.0D, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d18, d0, d19).func_181673_a(0.0D, d23).func_181669_b(j, k, l, 255).func_181675_d();
         double d24 = 0.0D;
         if(p_76986_1_.field_70173_aa % 2 == 0) {
            d24 = 0.5D;
         }

         worldrenderer.func_181662_b(d4, d0, d5).func_181673_a(0.5D, d24 + 0.5D).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d6, d0, d7).func_181673_a(1.0D, d24 + 0.5D).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d10, d0, d11).func_181673_a(1.0D, d24).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d8, d0, d9).func_181673_a(0.5D, d24).func_181669_b(j, k, l, 255).func_181675_d();
         tessellator.func_78381_a();
         GlStateManager.func_179121_F();
      }

   }

   protected void func_77041_b(EntityGuardian p_77041_1_, float p_77041_2_) {
      if(p_77041_1_.func_175461_cl()) {
         GlStateManager.func_179152_a(2.35F, 2.35F, 2.35F);
      }

   }

   protected ResourceLocation func_110775_a(EntityGuardian p_110775_1_) {
      return p_110775_1_.func_175461_cl()?field_177116_j:field_177114_e;
   }
}
