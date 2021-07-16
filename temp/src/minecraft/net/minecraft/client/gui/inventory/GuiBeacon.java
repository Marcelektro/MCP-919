package net.minecraft.client.gui.inventory;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiBeacon extends GuiContainer {
   private static final Logger field_147026_u = LogManager.getLogger();
   private static final ResourceLocation field_147025_v = new ResourceLocation("textures/gui/container/beacon.png");
   private IInventory field_147024_w;
   private GuiBeacon.ConfirmButton field_147028_x;
   private boolean field_147027_y;

   public GuiBeacon(InventoryPlayer p_i45507_1_, IInventory p_i45507_2_) {
      super(new ContainerBeacon(p_i45507_1_, p_i45507_2_));
      this.field_147024_w = p_i45507_2_;
      this.field_146999_f = 230;
      this.field_147000_g = 219;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.field_146292_n.add(this.field_147028_x = new GuiBeacon.ConfirmButton(-1, this.field_147003_i + 164, this.field_147009_r + 107));
      this.field_146292_n.add(new GuiBeacon.CancelButton(-2, this.field_147003_i + 190, this.field_147009_r + 107));
      this.field_147027_y = true;
      this.field_147028_x.field_146124_l = false;
   }

   public void func_73876_c() {
      super.func_73876_c();
      int i = this.field_147024_w.func_174887_a_(0);
      int j = this.field_147024_w.func_174887_a_(1);
      int k = this.field_147024_w.func_174887_a_(2);
      if(this.field_147027_y && i >= 0) {
         this.field_147027_y = false;

         for(int l = 0; l <= 2; ++l) {
            int i1 = TileEntityBeacon.field_146009_a[l].length;
            int j1 = i1 * 22 + (i1 - 1) * 2;

            for(int k1 = 0; k1 < i1; ++k1) {
               int l1 = TileEntityBeacon.field_146009_a[l][k1].field_76415_H;
               GuiBeacon.PowerButton guibeacon$powerbutton = new GuiBeacon.PowerButton(l << 8 | l1, this.field_147003_i + 76 + k1 * 24 - j1 / 2, this.field_147009_r + 22 + l * 25, l1, l);
               this.field_146292_n.add(guibeacon$powerbutton);
               if(l >= i) {
                  guibeacon$powerbutton.field_146124_l = false;
               } else if(l1 == j) {
                  guibeacon$powerbutton.func_146140_b(true);
               }
            }
         }

         int i2 = 3;
         int j2 = TileEntityBeacon.field_146009_a[i2].length + 1;
         int k2 = j2 * 22 + (j2 - 1) * 2;

         for(int l2 = 0; l2 < j2 - 1; ++l2) {
            int i3 = TileEntityBeacon.field_146009_a[i2][l2].field_76415_H;
            GuiBeacon.PowerButton guibeacon$powerbutton2 = new GuiBeacon.PowerButton(i2 << 8 | i3, this.field_147003_i + 167 + l2 * 24 - k2 / 2, this.field_147009_r + 47, i3, i2);
            this.field_146292_n.add(guibeacon$powerbutton2);
            if(i2 >= i) {
               guibeacon$powerbutton2.field_146124_l = false;
            } else if(i3 == k) {
               guibeacon$powerbutton2.func_146140_b(true);
            }
         }

         if(j > 0) {
            GuiBeacon.PowerButton guibeacon$powerbutton1 = new GuiBeacon.PowerButton(i2 << 8 | j, this.field_147003_i + 167 + (j2 - 1) * 24 - k2 / 2, this.field_147009_r + 47, j, i2);
            this.field_146292_n.add(guibeacon$powerbutton1);
            if(i2 >= i) {
               guibeacon$powerbutton1.field_146124_l = false;
            } else if(j == k) {
               guibeacon$powerbutton1.func_146140_b(true);
            }
         }
      }

      this.field_147028_x.field_146124_l = this.field_147024_w.func_70301_a(0) != null && j > 0;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146127_k == -2) {
         this.field_146297_k.func_147108_a((GuiScreen)null);
      } else if(p_146284_1_.field_146127_k == -1) {
         String s = "MC|Beacon";
         PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
         packetbuffer.writeInt(this.field_147024_w.func_174887_a_(1));
         packetbuffer.writeInt(this.field_147024_w.func_174887_a_(2));
         this.field_146297_k.func_147114_u().func_147297_a(new C17PacketCustomPayload(s, packetbuffer));
         this.field_146297_k.func_147108_a((GuiScreen)null);
      } else if(p_146284_1_ instanceof GuiBeacon.PowerButton) {
         if(((GuiBeacon.PowerButton)p_146284_1_).func_146141_c()) {
            return;
         }

         int j = p_146284_1_.field_146127_k;
         int k = j & 255;
         int i = j >> 8;
         if(i < 3) {
            this.field_147024_w.func_174885_b(1, k);
         } else {
            this.field_147024_w.func_174885_b(2, k);
         }

         this.field_146292_n.clear();
         this.func_73866_w_();
         this.func_73876_c();
      }

   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      RenderHelper.func_74518_a();
      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("tile.beacon.primary", new Object[0]), 62, 10, 14737632);
      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("tile.beacon.secondary", new Object[0]), 169, 10, 14737632);

      for(GuiButton guibutton : this.field_146292_n) {
         if(guibutton.func_146115_a()) {
            guibutton.func_146111_b(p_146979_1_ - this.field_147003_i, p_146979_2_ - this.field_147009_r);
            break;
         }
      }

      RenderHelper.func_74520_c();
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_147025_v);
      int i = (this.field_146294_l - this.field_146999_f) / 2;
      int j = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
      this.field_146296_j.field_77023_b = 100.0F;
      this.field_146296_j.func_180450_b(new ItemStack(Items.field_151166_bC), i + 42, j + 109);
      this.field_146296_j.func_180450_b(new ItemStack(Items.field_151045_i), i + 42 + 22, j + 109);
      this.field_146296_j.func_180450_b(new ItemStack(Items.field_151043_k), i + 42 + 44, j + 109);
      this.field_146296_j.func_180450_b(new ItemStack(Items.field_151042_j), i + 42 + 66, j + 109);
      this.field_146296_j.field_77023_b = 0.0F;
   }

   static class Button extends GuiButton {
      private final ResourceLocation field_146145_o;
      private final int field_146144_p;
      private final int field_146143_q;
      private boolean field_146142_r;

      protected Button(int p_i1077_1_, int p_i1077_2_, int p_i1077_3_, ResourceLocation p_i1077_4_, int p_i1077_5_, int p_i1077_6_) {
         super(p_i1077_1_, p_i1077_2_, p_i1077_3_, 22, 22, "");
         this.field_146145_o = p_i1077_4_;
         this.field_146144_p = p_i1077_5_;
         this.field_146143_q = p_i1077_6_;
      }

      public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
         if(this.field_146125_m) {
            p_146112_1_.func_110434_K().func_110577_a(GuiBeacon.field_147025_v);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
            int i = 219;
            int j = 0;
            if(!this.field_146124_l) {
               j += this.field_146120_f * 2;
            } else if(this.field_146142_r) {
               j += this.field_146120_f * 1;
            } else if(this.field_146123_n) {
               j += this.field_146120_f * 3;
            }

            this.func_73729_b(this.field_146128_h, this.field_146129_i, j, i, this.field_146120_f, this.field_146121_g);
            if(!GuiBeacon.field_147025_v.equals(this.field_146145_o)) {
               p_146112_1_.func_110434_K().func_110577_a(this.field_146145_o);
            }

            this.func_73729_b(this.field_146128_h + 2, this.field_146129_i + 2, this.field_146144_p, this.field_146143_q, 18, 18);
         }
      }

      public boolean func_146141_c() {
         return this.field_146142_r;
      }

      public void func_146140_b(boolean p_146140_1_) {
         this.field_146142_r = p_146140_1_;
      }
   }

   class CancelButton extends GuiBeacon.Button {
      public CancelButton(int p_i1074_2_, int p_i1074_3_, int p_i1074_4_) {
         super(p_i1074_2_, p_i1074_3_, p_i1074_4_, GuiBeacon.field_147025_v, 112, 220);
      }

      public void func_146111_b(int p_146111_1_, int p_146111_2_) {
         GuiBeacon.this.func_146279_a(I18n.func_135052_a("gui.cancel", new Object[0]), p_146111_1_, p_146111_2_);
      }
   }

   class ConfirmButton extends GuiBeacon.Button {
      public ConfirmButton(int p_i1075_2_, int p_i1075_3_, int p_i1075_4_) {
         super(p_i1075_2_, p_i1075_3_, p_i1075_4_, GuiBeacon.field_147025_v, 90, 220);
      }

      public void func_146111_b(int p_146111_1_, int p_146111_2_) {
         GuiBeacon.this.func_146279_a(I18n.func_135052_a("gui.done", new Object[0]), p_146111_1_, p_146111_2_);
      }
   }

   class PowerButton extends GuiBeacon.Button {
      private final int field_146149_p;
      private final int field_146148_q;

      public PowerButton(int p_i1076_2_, int p_i1076_3_, int p_i1076_4_, int p_i1076_5_, int p_i1076_6_) {
         super(p_i1076_2_, p_i1076_3_, p_i1076_4_, GuiContainer.field_147001_a, 0 + Potion.field_76425_a[p_i1076_5_].func_76392_e() % 8 * 18, 198 + Potion.field_76425_a[p_i1076_5_].func_76392_e() / 8 * 18);
         this.field_146149_p = p_i1076_5_;
         this.field_146148_q = p_i1076_6_;
      }

      public void func_146111_b(int p_146111_1_, int p_146111_2_) {
         String s = I18n.func_135052_a(Potion.field_76425_a[this.field_146149_p].func_76393_a(), new Object[0]);
         if(this.field_146148_q >= 3 && this.field_146149_p != Potion.field_76428_l.field_76415_H) {
            s = s + " II";
         }

         GuiBeacon.this.func_146279_a(s, p_146111_1_, p_146111_2_);
      }
   }
}
