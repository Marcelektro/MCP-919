package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.realms.RealmsScrolledSelectionList;

public class GuiSlotRealmsProxy extends GuiSlot {
   private final RealmsScrolledSelectionList field_154340_k;

   public GuiSlotRealmsProxy(RealmsScrolledSelectionList p_i1085_1_, int p_i1085_2_, int p_i1085_3_, int p_i1085_4_, int p_i1085_5_, int p_i1085_6_) {
      super(Minecraft.func_71410_x(), p_i1085_2_, p_i1085_3_, p_i1085_4_, p_i1085_5_, p_i1085_6_);
      this.field_154340_k = p_i1085_1_;
   }

   protected int func_148127_b() {
      return this.field_154340_k.getItemCount();
   }

   protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      this.field_154340_k.selectItem(p_148144_1_, p_148144_2_, p_148144_3_, p_148144_4_);
   }

   protected boolean func_148131_a(int p_148131_1_) {
      return this.field_154340_k.isSelectedItem(p_148131_1_);
   }

   protected void func_148123_a() {
      this.field_154340_k.renderBackground();
   }

   protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
      this.field_154340_k.renderItem(p_180791_1_, p_180791_2_, p_180791_3_, p_180791_4_, p_180791_5_, p_180791_6_);
   }

   public int func_154338_k() {
      return super.field_148155_a;
   }

   public int func_154339_l() {
      return super.field_148162_h;
   }

   public int func_154337_m() {
      return super.field_148150_g;
   }

   protected int func_148138_e() {
      return this.field_154340_k.getMaxPosition();
   }

   protected int func_148137_d() {
      return this.field_154340_k.getScrollbarPosition();
   }

   public void func_178039_p() {
      super.func_178039_p();
   }
}
