package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityPickupFX extends EntityFX {
   private Entity field_174840_a;
   private Entity field_174843_ax;
   private int field_70594_ar;
   private int field_70593_as;
   private float field_174841_aA;
   private RenderManager field_174842_aB = Minecraft.func_71410_x().func_175598_ae();

   public EntityPickupFX(World p_i1233_1_, Entity p_i1233_2_, Entity p_i1233_3_, float p_i1233_4_) {
      super(p_i1233_1_, p_i1233_2_.field_70165_t, p_i1233_2_.field_70163_u, p_i1233_2_.field_70161_v, p_i1233_2_.field_70159_w, p_i1233_2_.field_70181_x, p_i1233_2_.field_70179_y);
      this.field_174840_a = p_i1233_2_;
      this.field_174843_ax = p_i1233_3_;
      this.field_70593_as = 3;
      this.field_174841_aA = p_i1233_4_;
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = ((float)this.field_70594_ar + p_180434_3_) / (float)this.field_70593_as;
      f = f * f;
      double d0 = this.field_174840_a.field_70165_t;
      double d1 = this.field_174840_a.field_70163_u;
      double d2 = this.field_174840_a.field_70161_v;
      double d3 = this.field_174843_ax.field_70142_S + (this.field_174843_ax.field_70165_t - this.field_174843_ax.field_70142_S) * (double)p_180434_3_;
      double d4 = this.field_174843_ax.field_70137_T + (this.field_174843_ax.field_70163_u - this.field_174843_ax.field_70137_T) * (double)p_180434_3_ + (double)this.field_174841_aA;
      double d5 = this.field_174843_ax.field_70136_U + (this.field_174843_ax.field_70161_v - this.field_174843_ax.field_70136_U) * (double)p_180434_3_;
      double d6 = d0 + (d3 - d0) * (double)f;
      double d7 = d1 + (d4 - d1) * (double)f;
      double d8 = d2 + (d5 - d2) * (double)f;
      int i = this.func_70070_b(p_180434_3_);
      int j = i % 65536;
      int k = i / 65536;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)j / 1.0F, (float)k / 1.0F);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      d6 = d6 - field_70556_an;
      d7 = d7 - field_70554_ao;
      d8 = d8 - field_70555_ap;
      this.field_174842_aB.func_147940_a(this.field_174840_a, (double)((float)d6), (double)((float)d7), (double)((float)d8), this.field_174840_a.field_70177_z, p_180434_3_);
   }

   public void func_70071_h_() {
      ++this.field_70594_ar;
      if(this.field_70594_ar == this.field_70593_as) {
         this.func_70106_y();
      }

   }

   public int func_70537_b() {
      return 3;
   }
}
