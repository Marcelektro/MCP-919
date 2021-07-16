package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;

public class GuiRenameWorld extends GuiScreen {
   private GuiScreen field_146585_a;
   private GuiTextField field_146583_f;
   private final String field_146584_g;

   public GuiRenameWorld(GuiScreen p_i46317_1_, String p_i46317_2_) {
      this.field_146585_a = p_i46317_1_;
      this.field_146584_g = p_i46317_2_;
   }

   public void func_73876_c() {
      this.field_146583_f.func_146178_a();
   }

   public void func_73866_w_() {
      Keyboard.enableRepeatEvents(true);
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 96 + 12, I18n.func_135052_a("selectWorld.renameButton", new Object[0])));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 120 + 12, I18n.func_135052_a("gui.cancel", new Object[0])));
      ISaveFormat isaveformat = this.field_146297_k.func_71359_d();
      WorldInfo worldinfo = isaveformat.func_75803_c(this.field_146584_g);
      String s = worldinfo.func_76065_j();
      this.field_146583_f = new GuiTextField(2, this.field_146289_q, this.field_146294_l / 2 - 100, 60, 200, 20);
      this.field_146583_f.func_146195_b(true);
      this.field_146583_f.func_146180_a(s);
   }

   public void func_146281_b() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146124_l) {
         if(p_146284_1_.field_146127_k == 1) {
            this.field_146297_k.func_147108_a(this.field_146585_a);
         } else if(p_146284_1_.field_146127_k == 0) {
            ISaveFormat isaveformat = this.field_146297_k.func_71359_d();
            isaveformat.func_75806_a(this.field_146584_g, this.field_146583_f.func_146179_b().trim());
            this.field_146297_k.func_147108_a(this.field_146585_a);
         }

      }
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      this.field_146583_f.func_146201_a(p_73869_1_, p_73869_2_);
      ((GuiButton)this.field_146292_n.get(0)).field_146124_l = this.field_146583_f.func_146179_b().trim().length() > 0;
      if(p_73869_2_ == 28 || p_73869_2_ == 156) {
         this.func_146284_a((GuiButton)this.field_146292_n.get(0));
      }

   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146583_f.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("selectWorld.renameTitle", new Object[0]), this.field_146294_l / 2, 20, 16777215);
      this.func_73731_b(this.field_146289_q, I18n.func_135052_a("selectWorld.enterName", new Object[0]), this.field_146294_l / 2 - 100, 47, 10526880);
      this.field_146583_f.func_146194_f();
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
