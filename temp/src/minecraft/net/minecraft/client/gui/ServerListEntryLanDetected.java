package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanDetected implements GuiListExtended.IGuiListEntry {
   private final GuiMultiplayer field_148292_c;
   protected final Minecraft field_148293_a;
   protected final LanServerDetector.LanServer field_148291_b;
   private long field_148290_d = 0L;

   protected ServerListEntryLanDetected(GuiMultiplayer p_i45046_1_, LanServerDetector.LanServer p_i45046_2_) {
      this.field_148292_c = p_i45046_1_;
      this.field_148291_b = p_i45046_2_;
      this.field_148293_a = Minecraft.func_71410_x();
   }

   public void func_180790_a(int p_180790_1_, int p_180790_2_, int p_180790_3_, int p_180790_4_, int p_180790_5_, int p_180790_6_, int p_180790_7_, boolean p_180790_8_) {
      this.field_148293_a.field_71466_p.func_78276_b(I18n.func_135052_a("lanServer.title", new Object[0]), p_180790_2_ + 32 + 3, p_180790_3_ + 1, 16777215);
      this.field_148293_a.field_71466_p.func_78276_b(this.field_148291_b.func_77487_a(), p_180790_2_ + 32 + 3, p_180790_3_ + 12, 8421504);
      if(this.field_148293_a.field_71474_y.field_80005_w) {
         this.field_148293_a.field_71466_p.func_78276_b(I18n.func_135052_a("selectServer.hiddenAddress", new Object[0]), p_180790_2_ + 32 + 3, p_180790_3_ + 12 + 11, 3158064);
      } else {
         this.field_148293_a.field_71466_p.func_78276_b(this.field_148291_b.func_77488_b(), p_180790_2_ + 32 + 3, p_180790_3_ + 12 + 11, 3158064);
      }

   }

   public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
      this.field_148292_c.func_146790_a(p_148278_1_);
      if(Minecraft.func_71386_F() - this.field_148290_d < 250L) {
         this.field_148292_c.func_146796_h();
      }

      this.field_148290_d = Minecraft.func_71386_F();
      return false;
   }

   public void func_178011_a(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
   }

   public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {
   }

   public LanServerDetector.LanServer func_148289_a() {
      return this.field_148291_b;
   }
}
