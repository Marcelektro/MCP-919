package net.minecraft.client.gui.achievement;

import java.io.IOException;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiAchievements extends GuiScreen implements IProgressMeter {
   private static final int field_146572_y = AchievementList.field_76010_a * 24 - 112;
   private static final int field_146571_z = AchievementList.field_76008_b * 24 - 112;
   private static final int field_146559_A = AchievementList.field_76009_c * 24 - 77;
   private static final int field_146560_B = AchievementList.field_76006_d * 24 - 77;
   private static final ResourceLocation field_146561_C = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   protected GuiScreen field_146562_a;
   protected int field_146555_f = 256;
   protected int field_146557_g = 202;
   protected int field_146563_h;
   protected int field_146564_i;
   protected float field_146570_r = 1.0F;
   protected double field_146569_s;
   protected double field_146568_t;
   protected double field_146567_u;
   protected double field_146566_v;
   protected double field_146565_w;
   protected double field_146573_x;
   private int field_146554_D;
   private StatFileWriter field_146556_E;
   private boolean field_146558_F = true;

   public GuiAchievements(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_) {
      this.field_146562_a = p_i45026_1_;
      this.field_146556_E = p_i45026_2_;
      int i = 141;
      int j = 141;
      this.field_146569_s = this.field_146567_u = this.field_146565_w = (double)(AchievementList.field_76004_f.field_75993_a * 24 - i / 2 - 12);
      this.field_146568_t = this.field_146566_v = this.field_146573_x = (double)(AchievementList.field_76004_f.field_75991_b * 24 - j / 2);
   }

   public void func_73866_w_() {
      this.field_146297_k.func_147114_u().func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiOptionButton(1, this.field_146294_l / 2 + 24, this.field_146295_m / 2 + 74, 80, 20, I18n.func_135052_a("gui.done", new Object[0])));
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(!this.field_146558_F) {
         if(p_146284_1_.field_146127_k == 1) {
            this.field_146297_k.func_147108_a(this.field_146562_a);
         }

      }
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if(p_73869_2_ == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
         this.field_146297_k.func_147108_a((GuiScreen)null);
         this.field_146297_k.func_71381_h();
      } else {
         super.func_73869_a(p_73869_1_, p_73869_2_);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      if(this.field_146558_F) {
         this.func_146276_q_();
         this.func_73732_a(this.field_146289_q, I18n.func_135052_a("multiplayer.downloadingStats", new Object[0]), this.field_146294_l / 2, this.field_146295_m / 2, 16777215);
         this.func_73732_a(this.field_146289_q, field_146510_b_[(int)(Minecraft.func_71386_F() / 150L % (long)field_146510_b_.length)], this.field_146294_l / 2, this.field_146295_m / 2 + this.field_146289_q.field_78288_b * 2, 16777215);
      } else {
         if(Mouse.isButtonDown(0)) {
            int i = (this.field_146294_l - this.field_146555_f) / 2;
            int j = (this.field_146295_m - this.field_146557_g) / 2;
            int k = i + 8;
            int l = j + 17;
            if((this.field_146554_D == 0 || this.field_146554_D == 1) && p_73863_1_ >= k && p_73863_1_ < k + 224 && p_73863_2_ >= l && p_73863_2_ < l + 155) {
               if(this.field_146554_D == 0) {
                  this.field_146554_D = 1;
               } else {
                  this.field_146567_u -= (double)((float)(p_73863_1_ - this.field_146563_h) * this.field_146570_r);
                  this.field_146566_v -= (double)((float)(p_73863_2_ - this.field_146564_i) * this.field_146570_r);
                  this.field_146565_w = this.field_146569_s = this.field_146567_u;
                  this.field_146573_x = this.field_146568_t = this.field_146566_v;
               }

               this.field_146563_h = p_73863_1_;
               this.field_146564_i = p_73863_2_;
            }
         } else {
            this.field_146554_D = 0;
         }

         int i1 = Mouse.getDWheel();
         float f3 = this.field_146570_r;
         if(i1 < 0) {
            this.field_146570_r += 0.25F;
         } else if(i1 > 0) {
            this.field_146570_r -= 0.25F;
         }

         this.field_146570_r = MathHelper.func_76131_a(this.field_146570_r, 1.0F, 2.0F);
         if(this.field_146570_r != f3) {
            float f5 = f3 - this.field_146570_r;
            float f4 = f3 * (float)this.field_146555_f;
            float f = f3 * (float)this.field_146557_g;
            float f1 = this.field_146570_r * (float)this.field_146555_f;
            float f2 = this.field_146570_r * (float)this.field_146557_g;
            this.field_146567_u -= (double)((f1 - f4) * 0.5F);
            this.field_146566_v -= (double)((f2 - f) * 0.5F);
            this.field_146565_w = this.field_146569_s = this.field_146567_u;
            this.field_146573_x = this.field_146568_t = this.field_146566_v;
         }

         if(this.field_146565_w < (double)field_146572_y) {
            this.field_146565_w = (double)field_146572_y;
         }

         if(this.field_146573_x < (double)field_146571_z) {
            this.field_146573_x = (double)field_146571_z;
         }

         if(this.field_146565_w >= (double)field_146559_A) {
            this.field_146565_w = (double)(field_146559_A - 1);
         }

         if(this.field_146573_x >= (double)field_146560_B) {
            this.field_146573_x = (double)(field_146560_B - 1);
         }

         this.func_146276_q_();
         this.func_146552_b(p_73863_1_, p_73863_2_, p_73863_3_);
         GlStateManager.func_179140_f();
         GlStateManager.func_179097_i();
         this.func_146553_h();
         GlStateManager.func_179145_e();
         GlStateManager.func_179126_j();
      }

   }

   public void func_146509_g() {
      if(this.field_146558_F) {
         this.field_146558_F = false;
      }

   }

   public void func_73876_c() {
      if(!this.field_146558_F) {
         this.field_146569_s = this.field_146567_u;
         this.field_146568_t = this.field_146566_v;
         double d0 = this.field_146565_w - this.field_146567_u;
         double d1 = this.field_146573_x - this.field_146566_v;
         if(d0 * d0 + d1 * d1 < 4.0D) {
            this.field_146567_u += d0;
            this.field_146566_v += d1;
         } else {
            this.field_146567_u += d0 * 0.85D;
            this.field_146566_v += d1 * 0.85D;
         }

      }
   }

   protected void func_146553_h() {
      int i = (this.field_146294_l - this.field_146555_f) / 2;
      int j = (this.field_146295_m - this.field_146557_g) / 2;
      this.field_146289_q.func_78276_b(I18n.func_135052_a("gui.achievements", new Object[0]), i + 15, j + 5, 4210752);
   }

   protected void func_146552_b(int p_146552_1_, int p_146552_2_, float p_146552_3_) {
      int i = MathHelper.func_76128_c(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double)p_146552_3_);
      int j = MathHelper.func_76128_c(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double)p_146552_3_);
      if(i < field_146572_y) {
         i = field_146572_y;
      }

      if(j < field_146571_z) {
         j = field_146571_z;
      }

      if(i >= field_146559_A) {
         i = field_146559_A - 1;
      }

      if(j >= field_146560_B) {
         j = field_146560_B - 1;
      }

      int k = (this.field_146294_l - this.field_146555_f) / 2;
      int l = (this.field_146295_m - this.field_146557_g) / 2;
      int i1 = k + 16;
      int j1 = l + 17;
      this.field_73735_i = 0.0F;
      GlStateManager.func_179143_c(518);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)i1, (float)j1, -200.0F);
      GlStateManager.func_179152_a(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
      GlStateManager.func_179098_w();
      GlStateManager.func_179140_f();
      GlStateManager.func_179091_B();
      GlStateManager.func_179142_g();
      int k1 = i + 288 >> 4;
      int l1 = j + 288 >> 4;
      int i2 = (i + 288) % 16;
      int j2 = (j + 288) % 16;
      int k2 = 4;
      int l2 = 8;
      int i3 = 10;
      int j3 = 22;
      int k3 = 37;
      Random random = new Random();
      float f = 16.0F / this.field_146570_r;
      float f1 = 16.0F / this.field_146570_r;

      for(int l3 = 0; (float)l3 * f - (float)j2 < 155.0F; ++l3) {
         float f2 = 0.6F - (float)(l1 + l3) / 25.0F * 0.3F;
         GlStateManager.func_179131_c(f2, f2, f2, 1.0F);

         for(int i4 = 0; (float)i4 * f1 - (float)i2 < 224.0F; ++i4) {
            random.setSeed((long)(this.field_146297_k.func_110432_I().func_148255_b().hashCode() + k1 + i4 + (l1 + l3) * 16));
            int j4 = random.nextInt(1 + l1 + l3) + (l1 + l3) / 2;
            TextureAtlasSprite textureatlassprite = this.func_175371_a(Blocks.field_150354_m);
            if(j4 <= 37 && l1 + l3 != 35) {
               if(j4 == 22) {
                  if(random.nextInt(2) == 0) {
                     textureatlassprite = this.func_175371_a(Blocks.field_150482_ag);
                  } else {
                     textureatlassprite = this.func_175371_a(Blocks.field_150450_ax);
                  }
               } else if(j4 == 10) {
                  textureatlassprite = this.func_175371_a(Blocks.field_150366_p);
               } else if(j4 == 8) {
                  textureatlassprite = this.func_175371_a(Blocks.field_150365_q);
               } else if(j4 > 4) {
                  textureatlassprite = this.func_175371_a(Blocks.field_150348_b);
               } else if(j4 > 0) {
                  textureatlassprite = this.func_175371_a(Blocks.field_150346_d);
               }
            } else {
               Block block = Blocks.field_150357_h;
               textureatlassprite = this.func_175371_a(block);
            }

            this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            this.func_175175_a(i4 * 16 - i2, l3 * 16 - j2, textureatlassprite, 16, 16);
         }
      }

      GlStateManager.func_179126_j();
      GlStateManager.func_179143_c(515);
      this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);

      for(int j5 = 0; j5 < AchievementList.field_76007_e.size(); ++j5) {
         Achievement achievement1 = (Achievement)AchievementList.field_76007_e.get(j5);
         if(achievement1.field_75992_c != null) {
            int k5 = achievement1.field_75993_a * 24 - i + 11;
            int l5 = achievement1.field_75991_b * 24 - j + 11;
            int j6 = achievement1.field_75992_c.field_75993_a * 24 - i + 11;
            int k6 = achievement1.field_75992_c.field_75991_b * 24 - j + 11;
            boolean flag = this.field_146556_E.func_77443_a(achievement1);
            boolean flag1 = this.field_146556_E.func_77442_b(achievement1);
            int k4 = this.field_146556_E.func_150874_c(achievement1);
            if(k4 <= 4) {
               int l4 = -16777216;
               if(flag) {
                  l4 = -6250336;
               } else if(flag1) {
                  l4 = -16711936;
               }

               this.func_73730_a(k5, j6, l5, l4);
               this.func_73728_b(j6, l5, k6, l4);
               if(k5 > j6) {
                  this.func_73729_b(k5 - 11 - 7, l5 - 5, 114, 234, 7, 11);
               } else if(k5 < j6) {
                  this.func_73729_b(k5 + 11, l5 - 5, 107, 234, 7, 11);
               } else if(l5 > k6) {
                  this.func_73729_b(k5 - 5, l5 - 11 - 7, 96, 234, 11, 7);
               } else if(l5 < k6) {
                  this.func_73729_b(k5 - 5, l5 + 11, 96, 241, 11, 7);
               }
            }
         }
      }

      Achievement achievement = null;
      float f3 = (float)(p_146552_1_ - i1) * this.field_146570_r;
      float f4 = (float)(p_146552_2_ - j1) * this.field_146570_r;
      RenderHelper.func_74520_c();
      GlStateManager.func_179140_f();
      GlStateManager.func_179091_B();
      GlStateManager.func_179142_g();

      for(int i6 = 0; i6 < AchievementList.field_76007_e.size(); ++i6) {
         Achievement achievement2 = (Achievement)AchievementList.field_76007_e.get(i6);
         int l6 = achievement2.field_75993_a * 24 - i;
         int j7 = achievement2.field_75991_b * 24 - j;
         if(l6 >= -24 && j7 >= -24 && (float)l6 <= 224.0F * this.field_146570_r && (float)j7 <= 155.0F * this.field_146570_r) {
            int l7 = this.field_146556_E.func_150874_c(achievement2);
            if(this.field_146556_E.func_77443_a(achievement2)) {
               float f5 = 0.75F;
               GlStateManager.func_179131_c(f5, f5, f5, 1.0F);
            } else if(this.field_146556_E.func_77442_b(achievement2)) {
               float f6 = 1.0F;
               GlStateManager.func_179131_c(f6, f6, f6, 1.0F);
            } else if(l7 < 3) {
               float f7 = 0.3F;
               GlStateManager.func_179131_c(f7, f7, f7, 1.0F);
            } else if(l7 == 3) {
               float f8 = 0.2F;
               GlStateManager.func_179131_c(f8, f8, f8, 1.0F);
            } else {
               if(l7 != 4) {
                  continue;
               }

               float f9 = 0.1F;
               GlStateManager.func_179131_c(f9, f9, f9, 1.0F);
            }

            this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);
            if(achievement2.func_75984_f()) {
               this.func_73729_b(l6 - 2, j7 - 2, 26, 202, 26, 26);
            } else {
               this.func_73729_b(l6 - 2, j7 - 2, 0, 202, 26, 26);
            }

            if(!this.field_146556_E.func_77442_b(achievement2)) {
               float f10 = 0.1F;
               GlStateManager.func_179131_c(f10, f10, f10, 1.0F);
               this.field_146296_j.func_175039_a(false);
            }

            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            this.field_146296_j.func_180450_b(achievement2.field_75990_d, l6 + 3, j7 + 3);
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179140_f();
            if(!this.field_146556_E.func_77442_b(achievement2)) {
               this.field_146296_j.func_175039_a(true);
            }

            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            if(f3 >= (float)l6 && f3 <= (float)(l6 + 22) && f4 >= (float)j7 && f4 <= (float)(j7 + 22)) {
               achievement = achievement2;
            }
         }
      }

      GlStateManager.func_179097_i();
      GlStateManager.func_179147_l();
      GlStateManager.func_179121_F();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);
      this.func_73729_b(k, l, 0, 0, this.field_146555_f, this.field_146557_g);
      this.field_73735_i = 0.0F;
      GlStateManager.func_179143_c(515);
      GlStateManager.func_179097_i();
      GlStateManager.func_179098_w();
      super.func_73863_a(p_146552_1_, p_146552_2_, p_146552_3_);
      if(achievement != null) {
         String s = achievement.func_150951_e().func_150260_c();
         String s1 = achievement.func_75989_e();
         int i7 = p_146552_1_ + 12;
         int k7 = p_146552_2_ - 4;
         int i8 = this.field_146556_E.func_150874_c(achievement);
         if(this.field_146556_E.func_77442_b(achievement)) {
            int j8 = Math.max(this.field_146289_q.func_78256_a(s), 120);
            int i9 = this.field_146289_q.func_78267_b(s1, j8);
            if(this.field_146556_E.func_77443_a(achievement)) {
               i9 += 12;
            }

            this.func_73733_a(i7 - 3, k7 - 3, i7 + j8 + 3, k7 + i9 + 3 + 12, -1073741824, -1073741824);
            this.field_146289_q.func_78279_b(s1, i7, k7 + 12, j8, -6250336);
            if(this.field_146556_E.func_77443_a(achievement)) {
               this.field_146289_q.func_175063_a(I18n.func_135052_a("achievement.taken", new Object[0]), (float)i7, (float)(k7 + i9 + 4), -7302913);
            }
         } else if(i8 == 3) {
            s = I18n.func_135052_a("achievement.unknown", new Object[0]);
            int k8 = Math.max(this.field_146289_q.func_78256_a(s), 120);
            String s2 = (new ChatComponentTranslation("achievement.requires", new Object[]{achievement.field_75992_c.func_150951_e()})).func_150260_c();
            int i5 = this.field_146289_q.func_78267_b(s2, k8);
            this.func_73733_a(i7 - 3, k7 - 3, i7 + k8 + 3, k7 + i5 + 12 + 3, -1073741824, -1073741824);
            this.field_146289_q.func_78279_b(s2, i7, k7 + 12, k8, -9416624);
         } else if(i8 < 3) {
            int l8 = Math.max(this.field_146289_q.func_78256_a(s), 120);
            String s3 = (new ChatComponentTranslation("achievement.requires", new Object[]{achievement.field_75992_c.func_150951_e()})).func_150260_c();
            int j9 = this.field_146289_q.func_78267_b(s3, l8);
            this.func_73733_a(i7 - 3, k7 - 3, i7 + l8 + 3, k7 + j9 + 12 + 3, -1073741824, -1073741824);
            this.field_146289_q.func_78279_b(s3, i7, k7 + 12, l8, -9416624);
         } else {
            s = null;
         }

         if(s != null) {
            this.field_146289_q.func_175063_a(s, (float)i7, (float)k7, this.field_146556_E.func_77442_b(achievement)?(achievement.func_75984_f()?-128:-1):(achievement.func_75984_f()?-8355776:-8355712));
         }
      }

      GlStateManager.func_179126_j();
      GlStateManager.func_179145_e();
      RenderHelper.func_74518_a();
   }

   private TextureAtlasSprite func_175371_a(Block p_175371_1_) {
      return Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(p_175371_1_.func_176223_P());
   }

   public boolean func_73868_f() {
      return !this.field_146558_F;
   }
}
