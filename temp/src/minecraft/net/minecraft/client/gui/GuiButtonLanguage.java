package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonLanguage extends GuiButton {
   public GuiButtonLanguage(int p_i1041_1_, int p_i1041_2_, int p_i1041_3_) {
      super(p_i1041_1_, p_i1041_2_, p_i1041_3_, 20, 20, "");
   }

   public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      if(this.field_146125_m) {
         p_146112_1_.func_110434_K().func_110577_a(GuiButton.field_146122_a);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
         int i = 106;
         if(flag) {
            i += this.field_146121_g;
         }

         this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, i, this.field_146120_f, this.field_146121_g);
      }
   }
}
