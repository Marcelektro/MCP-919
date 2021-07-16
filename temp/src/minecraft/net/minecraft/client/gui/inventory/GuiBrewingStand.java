package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBrewingStand extends GuiContainer {
   private static final ResourceLocation field_147014_u = new ResourceLocation("textures/gui/container/brewing_stand.png");
   private final InventoryPlayer field_175384_v;
   private IInventory field_147013_v;

   public GuiBrewingStand(InventoryPlayer p_i45506_1_, IInventory p_i45506_2_) {
      super(new ContainerBrewingStand(p_i45506_1_, p_i45506_2_));
      this.field_175384_v = p_i45506_1_;
      this.field_147013_v = p_i45506_2_;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      String s = this.field_147013_v.func_145748_c_().func_150260_c();
      this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
      this.field_146289_q.func_78276_b(this.field_175384_v.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147014_u);
      int i = (this.field_146294_l - this.field_146999_f) / 2;
      int j = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
      int k = this.field_147013_v.func_174887_a_(0);
      if(k > 0) {
         int l = (int)(28.0F * (1.0F - (float)k / 400.0F));
         if(l > 0) {
            this.func_73729_b(i + 97, j + 16, 176, 0, 9, l);
         }

         int i1 = k / 2 % 7;
         switch(i1) {
         case 0:
            l = 29;
            break;
         case 1:
            l = 24;
            break;
         case 2:
            l = 20;
            break;
         case 3:
            l = 16;
            break;
         case 4:
            l = 11;
            break;
         case 5:
            l = 6;
            break;
         case 6:
            l = 0;
         }

         if(l > 0) {
            this.func_73729_b(i + 65, j + 14 + 29 - l, 185, 29 - l, 12, l);
         }
      }

   }
}
