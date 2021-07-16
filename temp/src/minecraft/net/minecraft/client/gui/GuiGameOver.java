package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

public class GuiGameOver extends GuiScreen implements GuiYesNoCallback {
   private int field_146347_a;
   private boolean field_146346_f = false;

   public void func_73866_w_() {
      this.field_146292_n.clear();
      if(this.field_146297_k.field_71441_e.func_72912_H().func_76093_s()) {
         if(this.field_146297_k.func_71387_A()) {
            this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 96, I18n.func_135052_a("deathScreen.deleteWorld", new Object[0])));
         } else {
            this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 96, I18n.func_135052_a("deathScreen.leaveServer", new Object[0])));
         }
      } else {
         this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 72, I18n.func_135052_a("deathScreen.respawn", new Object[0])));
         this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 96, I18n.func_135052_a("deathScreen.titleScreen", new Object[0])));
         if(this.field_146297_k.func_110432_I() == null) {
            ((GuiButton)this.field_146292_n.get(1)).field_146124_l = false;
         }
      }

      for(GuiButton guibutton : this.field_146292_n) {
         guibutton.field_146124_l = false;
      }

   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      switch(p_146284_1_.field_146127_k) {
      case 0:
         this.field_146297_k.field_71439_g.func_71004_bE();
         this.field_146297_k.func_147108_a((GuiScreen)null);
         break;
      case 1:
         if(this.field_146297_k.field_71441_e.func_72912_H().func_76093_s()) {
            this.field_146297_k.func_147108_a(new GuiMainMenu());
         } else {
            GuiYesNo guiyesno = new GuiYesNo(this, I18n.func_135052_a("deathScreen.quit.confirm", new Object[0]), "", I18n.func_135052_a("deathScreen.titleScreen", new Object[0]), I18n.func_135052_a("deathScreen.respawn", new Object[0]), 0);
            this.field_146297_k.func_147108_a(guiyesno);
            guiyesno.func_146350_a(20);
         }
      }

   }

   public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
      if(p_73878_1_) {
         this.field_146297_k.field_71441_e.func_72882_A();
         this.field_146297_k.func_71403_a((WorldClient)null);
         this.field_146297_k.func_147108_a(new GuiMainMenu());
      } else {
         this.field_146297_k.field_71439_g.func_71004_bE();
         this.field_146297_k.func_147108_a((GuiScreen)null);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, 1615855616, -1602211792);
      GlStateManager.func_179094_E();
      GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
      boolean flag = this.field_146297_k.field_71441_e.func_72912_H().func_76093_s();
      String s = flag?I18n.func_135052_a("deathScreen.title.hardcore", new Object[0]):I18n.func_135052_a("deathScreen.title", new Object[0]);
      this.func_73732_a(this.field_146289_q, s, this.field_146294_l / 2 / 2, 30, 16777215);
      GlStateManager.func_179121_F();
      if(flag) {
         this.func_73732_a(this.field_146289_q, I18n.func_135052_a("deathScreen.hardcoreInfo", new Object[0]), this.field_146294_l / 2, 144, 16777215);
      }

      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("deathScreen.score", new Object[0]) + ": " + EnumChatFormatting.YELLOW + this.field_146297_k.field_71439_g.func_71037_bA(), this.field_146294_l / 2, 100, 16777215);
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73876_c() {
      super.func_73876_c();
      ++this.field_146347_a;
      if(this.field_146347_a == 20) {
         for(GuiButton guibutton : this.field_146292_n) {
            guibutton.field_146124_l = true;
         }
      }

   }
}
