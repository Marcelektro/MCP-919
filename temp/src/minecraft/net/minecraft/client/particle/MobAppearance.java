package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MobAppearance extends EntityFX {
   private EntityLivingBase field_174844_a;

   protected MobAppearance(World p_i46283_1_, double p_i46283_2_, double p_i46283_4_, double p_i46283_6_) {
      super(p_i46283_1_, p_i46283_2_, p_i46283_4_, p_i46283_6_, 0.0D, 0.0D, 0.0D);
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
      this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
      this.field_70545_g = 0.0F;
      this.field_70547_e = 30;
   }

   public int func_70537_b() {
      return 3;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_174844_a == null) {
         EntityGuardian entityguardian = new EntityGuardian(this.field_70170_p);
         entityguardian.func_175465_cm();
         this.field_174844_a = entityguardian;
      }

   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      if(this.field_174844_a != null) {
         RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
         rendermanager.func_178628_a(EntityFX.field_70556_an, EntityFX.field_70554_ao, EntityFX.field_70555_ap);
         float f = 0.42553192F;
         float f1 = ((float)this.field_70546_d + p_180434_3_) / (float)this.field_70547_e;
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179147_l();
         GlStateManager.func_179126_j();
         GlStateManager.func_179112_b(770, 771);
         float f2 = 240.0F;
         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, f2, f2);
         GlStateManager.func_179094_E();
         float f3 = 0.05F + 0.5F * MathHelper.func_76126_a(f1 * 3.1415927F);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, f3);
         GlStateManager.func_179109_b(0.0F, 1.8F, 0.0F);
         GlStateManager.func_179114_b(180.0F - p_180434_2_.field_70177_z, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(60.0F - 150.0F * f1 - p_180434_2_.field_70125_A, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179109_b(0.0F, -0.4F, -1.5F);
         GlStateManager.func_179152_a(f, f, f);
         this.field_174844_a.field_70177_z = this.field_174844_a.field_70126_B = 0.0F;
         this.field_174844_a.field_70759_as = this.field_174844_a.field_70758_at = 0.0F;
         rendermanager.func_147940_a(this.field_174844_a, 0.0D, 0.0D, 0.0D, 0.0F, p_180434_3_);
         GlStateManager.func_179121_F();
         GlStateManager.func_179126_j();
      }
   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new MobAppearance(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_);
      }
   }
}
