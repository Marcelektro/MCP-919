package net.minecraft.client.renderer.tileentity;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer<TileEntityChest> {
   private static final ResourceLocation field_147507_b = new ResourceLocation("textures/entity/chest/trapped_double.png");
   private static final ResourceLocation field_147508_c = new ResourceLocation("textures/entity/chest/christmas_double.png");
   private static final ResourceLocation field_147505_d = new ResourceLocation("textures/entity/chest/normal_double.png");
   private static final ResourceLocation field_147506_e = new ResourceLocation("textures/entity/chest/trapped.png");
   private static final ResourceLocation field_147503_f = new ResourceLocation("textures/entity/chest/christmas.png");
   private static final ResourceLocation field_147504_g = new ResourceLocation("textures/entity/chest/normal.png");
   private ModelChest field_147510_h = new ModelChest();
   private ModelChest field_147511_i = new ModelLargeChest();
   private boolean field_147509_j;

   public TileEntityChestRenderer() {
      Calendar calendar = Calendar.getInstance();
      if(calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
         this.field_147509_j = true;
      }

   }

   public void func_180535_a(TileEntityChest p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      GlStateManager.func_179126_j();
      GlStateManager.func_179143_c(515);
      GlStateManager.func_179132_a(true);
      int i;
      if(!p_180535_1_.func_145830_o()) {
         i = 0;
      } else {
         Block block = p_180535_1_.func_145838_q();
         i = p_180535_1_.func_145832_p();
         if(block instanceof BlockChest && i == 0) {
            ((BlockChest)block).func_176455_e(p_180535_1_.func_145831_w(), p_180535_1_.func_174877_v(), p_180535_1_.func_145831_w().func_180495_p(p_180535_1_.func_174877_v()));
            i = p_180535_1_.func_145832_p();
         }

         p_180535_1_.func_145979_i();
      }

      if(p_180535_1_.field_145992_i == null && p_180535_1_.field_145991_k == null) {
         ModelChest modelchest;
         if(p_180535_1_.field_145990_j == null && p_180535_1_.field_145988_l == null) {
            modelchest = this.field_147510_h;
            if(p_180535_9_ >= 0) {
               this.func_147499_a(field_178460_a[p_180535_9_]);
               GlStateManager.func_179128_n(5890);
               GlStateManager.func_179094_E();
               GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
               GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.func_179128_n(5888);
            } else if(this.field_147509_j) {
               this.func_147499_a(field_147503_f);
            } else if(p_180535_1_.func_145980_j() == 1) {
               this.func_147499_a(field_147506_e);
            } else {
               this.func_147499_a(field_147504_g);
            }
         } else {
            modelchest = this.field_147511_i;
            if(p_180535_9_ >= 0) {
               this.func_147499_a(field_178460_a[p_180535_9_]);
               GlStateManager.func_179128_n(5890);
               GlStateManager.func_179094_E();
               GlStateManager.func_179152_a(8.0F, 4.0F, 1.0F);
               GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.func_179128_n(5888);
            } else if(this.field_147509_j) {
               this.func_147499_a(field_147508_c);
            } else if(p_180535_1_.func_145980_j() == 1) {
               this.func_147499_a(field_147507_b);
            } else {
               this.func_147499_a(field_147505_d);
            }
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179091_B();
         if(p_180535_9_ < 0) {
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GlStateManager.func_179109_b((float)p_180535_2_, (float)p_180535_4_ + 1.0F, (float)p_180535_6_ + 1.0F);
         GlStateManager.func_179152_a(1.0F, -1.0F, -1.0F);
         GlStateManager.func_179109_b(0.5F, 0.5F, 0.5F);
         int j = 0;
         if(i == 2) {
            j = 180;
         }

         if(i == 3) {
            j = 0;
         }

         if(i == 4) {
            j = 90;
         }

         if(i == 5) {
            j = -90;
         }

         if(i == 2 && p_180535_1_.field_145990_j != null) {
            GlStateManager.func_179109_b(1.0F, 0.0F, 0.0F);
         }

         if(i == 5 && p_180535_1_.field_145988_l != null) {
            GlStateManager.func_179109_b(0.0F, 0.0F, -1.0F);
         }

         GlStateManager.func_179114_b((float)j, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
         float f = p_180535_1_.field_145986_n + (p_180535_1_.field_145989_m - p_180535_1_.field_145986_n) * p_180535_8_;
         if(p_180535_1_.field_145992_i != null) {
            float f1 = p_180535_1_.field_145992_i.field_145986_n + (p_180535_1_.field_145992_i.field_145989_m - p_180535_1_.field_145992_i.field_145986_n) * p_180535_8_;
            if(f1 > f) {
               f = f1;
            }
         }

         if(p_180535_1_.field_145991_k != null) {
            float f2 = p_180535_1_.field_145991_k.field_145986_n + (p_180535_1_.field_145991_k.field_145989_m - p_180535_1_.field_145991_k.field_145986_n) * p_180535_8_;
            if(f2 > f) {
               f = f2;
            }
         }

         f = 1.0F - f;
         f = 1.0F - f * f * f;
         modelchest.field_78234_a.field_78795_f = -(f * 3.1415927F / 2.0F);
         modelchest.func_78231_a();
         GlStateManager.func_179101_C();
         GlStateManager.func_179121_F();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         if(p_180535_9_ >= 0) {
            GlStateManager.func_179128_n(5890);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5888);
         }

      }
   }
}
