package net.minecraft.client.gui;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiMerchant extends GuiContainer {
   private static final Logger field_147039_u = LogManager.getLogger();
   private static final ResourceLocation field_147038_v = new ResourceLocation("textures/gui/container/villager.png");
   private IMerchant field_147037_w;
   private GuiMerchant.MerchantButton field_147043_x;
   private GuiMerchant.MerchantButton field_147042_y;
   private int field_147041_z;
   private IChatComponent field_147040_A;

   public GuiMerchant(InventoryPlayer p_i45500_1_, IMerchant p_i45500_2_, World p_i45500_3_) {
      super(new ContainerMerchant(p_i45500_1_, p_i45500_2_, p_i45500_3_));
      this.field_147037_w = p_i45500_2_;
      this.field_147040_A = p_i45500_2_.func_145748_c_();
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      int i = (this.field_146294_l - this.field_146999_f) / 2;
      int j = (this.field_146295_m - this.field_147000_g) / 2;
      this.field_146292_n.add(this.field_147043_x = new GuiMerchant.MerchantButton(1, i + 120 + 27, j + 24 - 1, true));
      this.field_146292_n.add(this.field_147042_y = new GuiMerchant.MerchantButton(2, i + 36 - 19, j + 24 - 1, false));
      this.field_147043_x.field_146124_l = false;
      this.field_147042_y.field_146124_l = false;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      String s = this.field_147040_A.func_150260_c();
      this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
      this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   public void func_73876_c() {
      super.func_73876_c();
      MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);
      if(merchantrecipelist != null) {
         this.field_147043_x.field_146124_l = this.field_147041_z < merchantrecipelist.size() - 1;
         this.field_147042_y.field_146124_l = this.field_147041_z > 0;
      }

   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      boolean flag = false;
      if(p_146284_1_ == this.field_147043_x) {
         ++this.field_147041_z;
         MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);
         if(merchantrecipelist != null && this.field_147041_z >= merchantrecipelist.size()) {
            this.field_147041_z = merchantrecipelist.size() - 1;
         }

         flag = true;
      } else if(p_146284_1_ == this.field_147042_y) {
         --this.field_147041_z;
         if(this.field_147041_z < 0) {
            this.field_147041_z = 0;
         }

         flag = true;
      }

      if(flag) {
         ((ContainerMerchant)this.field_147002_h).func_75175_c(this.field_147041_z);
         PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
         packetbuffer.writeInt(this.field_147041_z);
         this.field_146297_k.func_147114_u().func_147297_a(new C17PacketCustomPayload("MC|TrSel", packetbuffer));
      }

   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147038_v);
      int i = (this.field_146294_l - this.field_146999_f) / 2;
      int j = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
      MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);
      if(merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
         int k = this.field_147041_z;
         if(k < 0 || k >= merchantrecipelist.size()) {
            return;
         }

         MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(k);
         if(merchantrecipe.func_82784_g()) {
            this.field_146297_k.func_110434_K().func_110577_a(field_147038_v);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179140_f();
            this.func_73729_b(this.field_147003_i + 83, this.field_147009_r + 21, 212, 0, 28, 21);
            this.func_73729_b(this.field_147003_i + 83, this.field_147009_r + 51, 212, 0, 28, 21);
         }
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);
      if(merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
         int i = (this.field_146294_l - this.field_146999_f) / 2;
         int j = (this.field_146295_m - this.field_147000_g) / 2;
         int k = this.field_147041_z;
         MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(k);
         ItemStack itemstack = merchantrecipe.func_77394_a();
         ItemStack itemstack1 = merchantrecipe.func_77396_b();
         ItemStack itemstack2 = merchantrecipe.func_77397_d();
         GlStateManager.func_179094_E();
         RenderHelper.func_74520_c();
         GlStateManager.func_179140_f();
         GlStateManager.func_179091_B();
         GlStateManager.func_179142_g();
         GlStateManager.func_179145_e();
         this.field_146296_j.field_77023_b = 100.0F;
         this.field_146296_j.func_180450_b(itemstack, i + 36, j + 24);
         this.field_146296_j.func_175030_a(this.field_146289_q, itemstack, i + 36, j + 24);
         if(itemstack1 != null) {
            this.field_146296_j.func_180450_b(itemstack1, i + 62, j + 24);
            this.field_146296_j.func_175030_a(this.field_146289_q, itemstack1, i + 62, j + 24);
         }

         this.field_146296_j.func_180450_b(itemstack2, i + 120, j + 24);
         this.field_146296_j.func_175030_a(this.field_146289_q, itemstack2, i + 120, j + 24);
         this.field_146296_j.field_77023_b = 0.0F;
         GlStateManager.func_179140_f();
         if(this.func_146978_c(36, 24, 16, 16, p_73863_1_, p_73863_2_) && itemstack != null) {
            this.func_146285_a(itemstack, p_73863_1_, p_73863_2_);
         } else if(itemstack1 != null && this.func_146978_c(62, 24, 16, 16, p_73863_1_, p_73863_2_) && itemstack1 != null) {
            this.func_146285_a(itemstack1, p_73863_1_, p_73863_2_);
         } else if(itemstack2 != null && this.func_146978_c(120, 24, 16, 16, p_73863_1_, p_73863_2_) && itemstack2 != null) {
            this.func_146285_a(itemstack2, p_73863_1_, p_73863_2_);
         } else if(merchantrecipe.func_82784_g() && (this.func_146978_c(83, 21, 28, 21, p_73863_1_, p_73863_2_) || this.func_146978_c(83, 51, 28, 21, p_73863_1_, p_73863_2_))) {
            this.func_146279_a(I18n.func_135052_a("merchant.deprecated", new Object[0]), p_73863_1_, p_73863_2_);
         }

         GlStateManager.func_179121_F();
         GlStateManager.func_179145_e();
         GlStateManager.func_179126_j();
         RenderHelper.func_74519_b();
      }

   }

   public IMerchant func_147035_g() {
      return this.field_147037_w;
   }

   static class MerchantButton extends GuiButton {
      private final boolean field_146157_o;

      public MerchantButton(int p_i1095_1_, int p_i1095_2_, int p_i1095_3_, boolean p_i1095_4_) {
         super(p_i1095_1_, p_i1095_2_, p_i1095_3_, 12, 19, "");
         this.field_146157_o = p_i1095_4_;
      }

      public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
         if(this.field_146125_m) {
            p_146112_1_.func_110434_K().func_110577_a(GuiMerchant.field_147038_v);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
            int i = 0;
            int j = 176;
            if(!this.field_146124_l) {
               j += this.field_146120_f * 2;
            } else if(flag) {
               j += this.field_146120_f;
            }

            if(!this.field_146157_o) {
               i += this.field_146121_g;
            }

            this.func_73729_b(this.field_146128_h, this.field_146129_i, j, i, this.field_146120_f, this.field_146121_g);
         }
      }
   }
}
