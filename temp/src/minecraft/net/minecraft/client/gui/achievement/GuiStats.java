package net.minecraft.client.gui.achievement;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiStats extends GuiScreen implements IProgressMeter {
   protected GuiScreen field_146549_a;
   protected String field_146542_f = "Select world";
   private GuiStats.StatsGeneral field_146550_h;
   private GuiStats.StatsItem field_146551_i;
   private GuiStats.StatsBlock field_146548_r;
   private GuiStats.StatsMobsList field_146547_s;
   private StatFileWriter field_146546_t;
   private GuiSlot field_146545_u;
   private boolean field_146543_v = true;

   public GuiStats(GuiScreen p_i1071_1_, StatFileWriter p_i1071_2_) {
      this.field_146549_a = p_i1071_1_;
      this.field_146546_t = p_i1071_2_;
   }

   public void func_73866_w_() {
      this.field_146542_f = I18n.func_135052_a("gui.stats", new Object[0]);
      this.field_146543_v = true;
      this.field_146297_k.func_147114_u().func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      if(this.field_146545_u != null) {
         this.field_146545_u.func_178039_p();
      }

   }

   public void func_175366_f() {
      this.field_146550_h = new GuiStats.StatsGeneral(this.field_146297_k);
      this.field_146550_h.func_148134_d(1, 1);
      this.field_146551_i = new GuiStats.StatsItem(this.field_146297_k);
      this.field_146551_i.func_148134_d(1, 1);
      this.field_146548_r = new GuiStats.StatsBlock(this.field_146297_k);
      this.field_146548_r.func_148134_d(1, 1);
      this.field_146547_s = new GuiStats.StatsMobsList(this.field_146297_k);
      this.field_146547_s.func_148134_d(1, 1);
   }

   public void func_146541_h() {
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 + 4, this.field_146295_m - 28, 150, 20, I18n.func_135052_a("gui.done", new Object[0])));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 160, this.field_146295_m - 52, 80, 20, I18n.func_135052_a("stat.generalButton", new Object[0])));
      GuiButton guibutton;
      this.field_146292_n.add(guibutton = new GuiButton(2, this.field_146294_l / 2 - 80, this.field_146295_m - 52, 80, 20, I18n.func_135052_a("stat.blocksButton", new Object[0])));
      GuiButton guibutton1;
      this.field_146292_n.add(guibutton1 = new GuiButton(3, this.field_146294_l / 2, this.field_146295_m - 52, 80, 20, I18n.func_135052_a("stat.itemsButton", new Object[0])));
      GuiButton guibutton2;
      this.field_146292_n.add(guibutton2 = new GuiButton(4, this.field_146294_l / 2 + 80, this.field_146295_m - 52, 80, 20, I18n.func_135052_a("stat.mobsButton", new Object[0])));
      if(this.field_146548_r.func_148127_b() == 0) {
         guibutton.field_146124_l = false;
      }

      if(this.field_146551_i.func_148127_b() == 0) {
         guibutton1.field_146124_l = false;
      }

      if(this.field_146547_s.func_148127_b() == 0) {
         guibutton2.field_146124_l = false;
      }

   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146124_l) {
         if(p_146284_1_.field_146127_k == 0) {
            this.field_146297_k.func_147108_a(this.field_146549_a);
         } else if(p_146284_1_.field_146127_k == 1) {
            this.field_146545_u = this.field_146550_h;
         } else if(p_146284_1_.field_146127_k == 3) {
            this.field_146545_u = this.field_146551_i;
         } else if(p_146284_1_.field_146127_k == 2) {
            this.field_146545_u = this.field_146548_r;
         } else if(p_146284_1_.field_146127_k == 4) {
            this.field_146545_u = this.field_146547_s;
         } else {
            this.field_146545_u.func_148147_a(p_146284_1_);
         }

      }
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      if(this.field_146543_v) {
         this.func_146276_q_();
         this.func_73732_a(this.field_146289_q, I18n.func_135052_a("multiplayer.downloadingStats", new Object[0]), this.field_146294_l / 2, this.field_146295_m / 2, 16777215);
         this.func_73732_a(this.field_146289_q, field_146510_b_[(int)(Minecraft.func_71386_F() / 150L % (long)field_146510_b_.length)], this.field_146294_l / 2, this.field_146295_m / 2 + this.field_146289_q.field_78288_b * 2, 16777215);
      } else {
         this.field_146545_u.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
         this.func_73732_a(this.field_146289_q, this.field_146542_f, this.field_146294_l / 2, 20, 16777215);
         super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      }

   }

   public void func_146509_g() {
      if(this.field_146543_v) {
         this.func_175366_f();
         this.func_146541_h();
         this.field_146545_u = this.field_146550_h;
         this.field_146543_v = false;
      }

   }

   public boolean func_73868_f() {
      return !this.field_146543_v;
   }

   private void func_146521_a(int p_146521_1_, int p_146521_2_, Item p_146521_3_) {
      this.func_146531_b(p_146521_1_ + 1, p_146521_2_ + 1);
      GlStateManager.func_179091_B();
      RenderHelper.func_74520_c();
      this.field_146296_j.func_175042_a(new ItemStack(p_146521_3_, 1, 0), p_146521_1_ + 2, p_146521_2_ + 2);
      RenderHelper.func_74518_a();
      GlStateManager.func_179101_C();
   }

   private void func_146531_b(int p_146531_1_, int p_146531_2_) {
      this.func_146527_c(p_146531_1_, p_146531_2_, 0, 0);
   }

   private void func_146527_c(int p_146527_1_, int p_146527_2_, int p_146527_3_, int p_146527_4_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_110323_l);
      float f = 0.0078125F;
      float f1 = 0.0078125F;
      int i = 18;
      int j = 18;
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b((double)(p_146527_1_ + 0), (double)(p_146527_2_ + 18), (double)this.field_73735_i).func_181673_a((double)((float)(p_146527_3_ + 0) * 0.0078125F), (double)((float)(p_146527_4_ + 18) * 0.0078125F)).func_181675_d();
      worldrenderer.func_181662_b((double)(p_146527_1_ + 18), (double)(p_146527_2_ + 18), (double)this.field_73735_i).func_181673_a((double)((float)(p_146527_3_ + 18) * 0.0078125F), (double)((float)(p_146527_4_ + 18) * 0.0078125F)).func_181675_d();
      worldrenderer.func_181662_b((double)(p_146527_1_ + 18), (double)(p_146527_2_ + 0), (double)this.field_73735_i).func_181673_a((double)((float)(p_146527_3_ + 18) * 0.0078125F), (double)((float)(p_146527_4_ + 0) * 0.0078125F)).func_181675_d();
      worldrenderer.func_181662_b((double)(p_146527_1_ + 0), (double)(p_146527_2_ + 0), (double)this.field_73735_i).func_181673_a((double)((float)(p_146527_3_ + 0) * 0.0078125F), (double)((float)(p_146527_4_ + 0) * 0.0078125F)).func_181675_d();
      tessellator.func_78381_a();
   }

   abstract class Stats extends GuiSlot {
      protected int field_148218_l = -1;
      protected List<StatCrafting> field_148219_m;
      protected Comparator<StatCrafting> field_148216_n;
      protected int field_148217_o = -1;
      protected int field_148215_p;

      protected Stats(Minecraft p_i45509_2_) {
         super(p_i45509_2_, GuiStats.this.field_146294_l, GuiStats.this.field_146295_m, 32, GuiStats.this.field_146295_m - 64, 20);
         this.func_148130_a(false);
         this.func_148133_a(true, 20);
      }

      protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      }

      protected boolean func_148131_a(int p_148131_1_) {
         return false;
      }

      protected void func_148123_a() {
         GuiStats.this.func_146276_q_();
      }

      protected void func_148129_a(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
         if(!Mouse.isButtonDown(0)) {
            this.field_148218_l = -1;
         }

         if(this.field_148218_l == 0) {
            GuiStats.this.func_146527_c(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 0, 0);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 0, 18);
         }

         if(this.field_148218_l == 1) {
            GuiStats.this.func_146527_c(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 0, 0);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 0, 18);
         }

         if(this.field_148218_l == 2) {
            GuiStats.this.func_146527_c(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 0, 0);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 0, 18);
         }

         if(this.field_148217_o != -1) {
            int i = 79;
            int j = 18;
            if(this.field_148217_o == 1) {
               i = 129;
            } else if(this.field_148217_o == 2) {
               i = 179;
            }

            if(this.field_148215_p == 1) {
               j = 36;
            }

            GuiStats.this.func_146527_c(p_148129_1_ + i, p_148129_2_ + 1, j, 0);
         }

      }

      protected void func_148132_a(int p_148132_1_, int p_148132_2_) {
         this.field_148218_l = -1;
         if(p_148132_1_ >= 79 && p_148132_1_ < 115) {
            this.field_148218_l = 0;
         } else if(p_148132_1_ >= 129 && p_148132_1_ < 165) {
            this.field_148218_l = 1;
         } else if(p_148132_1_ >= 179 && p_148132_1_ < 215) {
            this.field_148218_l = 2;
         }

         if(this.field_148218_l >= 0) {
            this.func_148212_h(this.field_148218_l);
            this.field_148161_k.func_147118_V().func_147682_a(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
         }

      }

      protected final int func_148127_b() {
         return this.field_148219_m.size();
      }

      protected final StatCrafting func_148211_c(int p_148211_1_) {
         return (StatCrafting)this.field_148219_m.get(p_148211_1_);
      }

      protected abstract String func_148210_b(int var1);

      protected void func_148209_a(StatBase p_148209_1_, int p_148209_2_, int p_148209_3_, boolean p_148209_4_) {
         if(p_148209_1_ != null) {
            String s = p_148209_1_.func_75968_a(GuiStats.this.field_146546_t.func_77444_a(p_148209_1_));
            GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, s, p_148209_2_ - GuiStats.this.field_146289_q.func_78256_a(s), p_148209_3_ + 5, p_148209_4_?16777215:9474192);
         } else {
            String s1 = "-";
            GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, s1, p_148209_2_ - GuiStats.this.field_146289_q.func_78256_a(s1), p_148209_3_ + 5, p_148209_4_?16777215:9474192);
         }

      }

      protected void func_148142_b(int p_148142_1_, int p_148142_2_) {
         if(p_148142_2_ >= this.field_148153_b && p_148142_2_ <= this.field_148154_c) {
            int i = this.func_148124_c(p_148142_1_, p_148142_2_);
            int j = this.field_148155_a / 2 - 92 - 16;
            if(i >= 0) {
               if(p_148142_1_ < j + 40 || p_148142_1_ > j + 40 + 20) {
                  return;
               }

               StatCrafting statcrafting = this.func_148211_c(i);
               this.func_148213_a(statcrafting, p_148142_1_, p_148142_2_);
            } else {
               String s = "";
               if(p_148142_1_ >= j + 115 - 18 && p_148142_1_ <= j + 115) {
                  s = this.func_148210_b(0);
               } else if(p_148142_1_ >= j + 165 - 18 && p_148142_1_ <= j + 165) {
                  s = this.func_148210_b(1);
               } else {
                  if(p_148142_1_ < j + 215 - 18 || p_148142_1_ > j + 215) {
                     return;
                  }

                  s = this.func_148210_b(2);
               }

               s = ("" + I18n.func_135052_a(s, new Object[0])).trim();
               if(s.length() > 0) {
                  int k = p_148142_1_ + 12;
                  int l = p_148142_2_ - 12;
                  int i1 = GuiStats.this.field_146289_q.func_78256_a(s);
                  GuiStats.this.func_73733_a(k - 3, l - 3, k + i1 + 3, l + 8 + 3, -1073741824, -1073741824);
                  GuiStats.this.field_146289_q.func_175063_a(s, (float)k, (float)l, -1);
               }
            }

         }
      }

      protected void func_148213_a(StatCrafting p_148213_1_, int p_148213_2_, int p_148213_3_) {
         if(p_148213_1_ != null) {
            Item item = p_148213_1_.func_150959_a();
            ItemStack itemstack = new ItemStack(item);
            String s = itemstack.func_77977_a();
            String s1 = ("" + I18n.func_135052_a(s + ".name", new Object[0])).trim();
            if(s1.length() > 0) {
               int i = p_148213_2_ + 12;
               int j = p_148213_3_ - 12;
               int k = GuiStats.this.field_146289_q.func_78256_a(s1);
               GuiStats.this.func_73733_a(i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
               GuiStats.this.field_146289_q.func_175063_a(s1, (float)i, (float)j, -1);
            }

         }
      }

      protected void func_148212_h(int p_148212_1_) {
         if(p_148212_1_ != this.field_148217_o) {
            this.field_148217_o = p_148212_1_;
            this.field_148215_p = -1;
         } else if(this.field_148215_p == -1) {
            this.field_148215_p = 1;
         } else {
            this.field_148217_o = -1;
            this.field_148215_p = 0;
         }

         Collections.sort(this.field_148219_m, this.field_148216_n);
      }
   }

   class StatsBlock extends GuiStats.Stats {
      public StatsBlock(Minecraft p_i45513_2_) {
         super(p_i45513_2_);
         this.field_148219_m = Lists.<StatCrafting>newArrayList();

         for(StatCrafting statcrafting : StatList.field_75939_e) {
            boolean flag = false;
            int i = Item.func_150891_b(statcrafting.func_150959_a());
            if(GuiStats.this.field_146546_t.func_77444_a(statcrafting) > 0) {
               flag = true;
            } else if(StatList.field_75929_E[i] != null && GuiStats.this.field_146546_t.func_77444_a(StatList.field_75929_E[i]) > 0) {
               flag = true;
            } else if(StatList.field_75928_D[i] != null && GuiStats.this.field_146546_t.func_77444_a(StatList.field_75928_D[i]) > 0) {
               flag = true;
            }

            if(flag) {
               this.field_148219_m.add(statcrafting);
            }
         }

         this.field_148216_n = new Comparator<StatCrafting>() {
            public int compare(StatCrafting p_compare_1_, StatCrafting p_compare_2_) {
               int j = Item.func_150891_b(p_compare_1_.func_150959_a());
               int k = Item.func_150891_b(p_compare_2_.func_150959_a());
               StatBase statbase = null;
               StatBase statbase1 = null;
               if(StatsBlock.this.field_148217_o == 2) {
                  statbase = StatList.field_75934_C[j];
                  statbase1 = StatList.field_75934_C[k];
               } else if(StatsBlock.this.field_148217_o == 0) {
                  statbase = StatList.field_75928_D[j];
                  statbase1 = StatList.field_75928_D[k];
               } else if(StatsBlock.this.field_148217_o == 1) {
                  statbase = StatList.field_75929_E[j];
                  statbase1 = StatList.field_75929_E[k];
               }

               if(statbase != null || statbase1 != null) {
                  if(statbase == null) {
                     return 1;
                  }

                  if(statbase1 == null) {
                     return -1;
                  }

                  int l = GuiStats.this.field_146546_t.func_77444_a(statbase);
                  int i1 = GuiStats.this.field_146546_t.func_77444_a(statbase1);
                  if(l != i1) {
                     return (l - i1) * StatsBlock.this.field_148215_p;
                  }
               }

               return j - k;
            }
         };
      }

      protected void func_148129_a(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
         super.func_148129_a(p_148129_1_, p_148129_2_, p_148129_3_);
         if(this.field_148218_l == 0) {
            GuiStats.this.func_146527_c(p_148129_1_ + 115 - 18 + 1, p_148129_2_ + 1 + 1, 18, 18);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 18, 18);
         }

         if(this.field_148218_l == 1) {
            GuiStats.this.func_146527_c(p_148129_1_ + 165 - 18 + 1, p_148129_2_ + 1 + 1, 36, 18);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 36, 18);
         }

         if(this.field_148218_l == 2) {
            GuiStats.this.func_146527_c(p_148129_1_ + 215 - 18 + 1, p_148129_2_ + 1 + 1, 54, 18);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 54, 18);
         }

      }

      protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
         StatCrafting statcrafting = this.func_148211_c(p_180791_1_);
         Item item = statcrafting.func_150959_a();
         GuiStats.this.func_146521_a(p_180791_2_ + 40, p_180791_3_, item);
         int i = Item.func_150891_b(item);
         this.func_148209_a(StatList.field_75928_D[i], p_180791_2_ + 115, p_180791_3_, p_180791_1_ % 2 == 0);
         this.func_148209_a(StatList.field_75929_E[i], p_180791_2_ + 165, p_180791_3_, p_180791_1_ % 2 == 0);
         this.func_148209_a(statcrafting, p_180791_2_ + 215, p_180791_3_, p_180791_1_ % 2 == 0);
      }

      protected String func_148210_b(int p_148210_1_) {
         return p_148210_1_ == 0?"stat.crafted":(p_148210_1_ == 1?"stat.used":"stat.mined");
      }
   }

   class StatsGeneral extends GuiSlot {
      public StatsGeneral(Minecraft p_i45512_2_) {
         super(p_i45512_2_, GuiStats.this.field_146294_l, GuiStats.this.field_146295_m, 32, GuiStats.this.field_146295_m - 64, 10);
         this.func_148130_a(false);
      }

      protected int func_148127_b() {
         return StatList.field_75941_c.size();
      }

      protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      }

      protected boolean func_148131_a(int p_148131_1_) {
         return false;
      }

      protected int func_148138_e() {
         return this.func_148127_b() * 10;
      }

      protected void func_148123_a() {
         GuiStats.this.func_146276_q_();
      }

      protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
         StatBase statbase = (StatBase)StatList.field_75941_c.get(p_180791_1_);
         GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, statbase.func_150951_e().func_150260_c(), p_180791_2_ + 2, p_180791_3_ + 1, p_180791_1_ % 2 == 0?16777215:9474192);
         String s = statbase.func_75968_a(GuiStats.this.field_146546_t.func_77444_a(statbase));
         GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, s, p_180791_2_ + 2 + 213 - GuiStats.this.field_146289_q.func_78256_a(s), p_180791_3_ + 1, p_180791_1_ % 2 == 0?16777215:9474192);
      }
   }

   class StatsItem extends GuiStats.Stats {
      public StatsItem(Minecraft p_i45511_2_) {
         super(p_i45511_2_);
         this.field_148219_m = Lists.<StatCrafting>newArrayList();

         for(StatCrafting statcrafting : StatList.field_75938_d) {
            boolean flag = false;
            int i = Item.func_150891_b(statcrafting.func_150959_a());
            if(GuiStats.this.field_146546_t.func_77444_a(statcrafting) > 0) {
               flag = true;
            } else if(StatList.field_75930_F[i] != null && GuiStats.this.field_146546_t.func_77444_a(StatList.field_75930_F[i]) > 0) {
               flag = true;
            } else if(StatList.field_75928_D[i] != null && GuiStats.this.field_146546_t.func_77444_a(StatList.field_75928_D[i]) > 0) {
               flag = true;
            }

            if(flag) {
               this.field_148219_m.add(statcrafting);
            }
         }

         this.field_148216_n = new Comparator<StatCrafting>() {
            public int compare(StatCrafting p_compare_1_, StatCrafting p_compare_2_) {
               int j = Item.func_150891_b(p_compare_1_.func_150959_a());
               int k = Item.func_150891_b(p_compare_2_.func_150959_a());
               StatBase statbase = null;
               StatBase statbase1 = null;
               if(StatsItem.this.field_148217_o == 0) {
                  statbase = StatList.field_75930_F[j];
                  statbase1 = StatList.field_75930_F[k];
               } else if(StatsItem.this.field_148217_o == 1) {
                  statbase = StatList.field_75928_D[j];
                  statbase1 = StatList.field_75928_D[k];
               } else if(StatsItem.this.field_148217_o == 2) {
                  statbase = StatList.field_75929_E[j];
                  statbase1 = StatList.field_75929_E[k];
               }

               if(statbase != null || statbase1 != null) {
                  if(statbase == null) {
                     return 1;
                  }

                  if(statbase1 == null) {
                     return -1;
                  }

                  int l = GuiStats.this.field_146546_t.func_77444_a(statbase);
                  int i1 = GuiStats.this.field_146546_t.func_77444_a(statbase1);
                  if(l != i1) {
                     return (l - i1) * StatsItem.this.field_148215_p;
                  }
               }

               return j - k;
            }
         };
      }

      protected void func_148129_a(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
         super.func_148129_a(p_148129_1_, p_148129_2_, p_148129_3_);
         if(this.field_148218_l == 0) {
            GuiStats.this.func_146527_c(p_148129_1_ + 115 - 18 + 1, p_148129_2_ + 1 + 1, 72, 18);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 72, 18);
         }

         if(this.field_148218_l == 1) {
            GuiStats.this.func_146527_c(p_148129_1_ + 165 - 18 + 1, p_148129_2_ + 1 + 1, 18, 18);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 18, 18);
         }

         if(this.field_148218_l == 2) {
            GuiStats.this.func_146527_c(p_148129_1_ + 215 - 18 + 1, p_148129_2_ + 1 + 1, 36, 18);
         } else {
            GuiStats.this.func_146527_c(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 36, 18);
         }

      }

      protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
         StatCrafting statcrafting = this.func_148211_c(p_180791_1_);
         Item item = statcrafting.func_150959_a();
         GuiStats.this.func_146521_a(p_180791_2_ + 40, p_180791_3_, item);
         int i = Item.func_150891_b(item);
         this.func_148209_a(StatList.field_75930_F[i], p_180791_2_ + 115, p_180791_3_, p_180791_1_ % 2 == 0);
         this.func_148209_a(StatList.field_75928_D[i], p_180791_2_ + 165, p_180791_3_, p_180791_1_ % 2 == 0);
         this.func_148209_a(statcrafting, p_180791_2_ + 215, p_180791_3_, p_180791_1_ % 2 == 0);
      }

      protected String func_148210_b(int p_148210_1_) {
         return p_148210_1_ == 1?"stat.crafted":(p_148210_1_ == 2?"stat.used":"stat.depleted");
      }
   }

   class StatsMobsList extends GuiSlot {
      private final List<EntityList.EntityEggInfo> field_148222_l = Lists.<EntityList.EntityEggInfo>newArrayList();

      public StatsMobsList(Minecraft p_i45510_2_) {
         super(p_i45510_2_, GuiStats.this.field_146294_l, GuiStats.this.field_146295_m, 32, GuiStats.this.field_146295_m - 64, GuiStats.this.field_146289_q.field_78288_b * 4);
         this.func_148130_a(false);

         for(EntityList.EntityEggInfo entitylist$entityegginfo : EntityList.field_75627_a.values()) {
            if(GuiStats.this.field_146546_t.func_77444_a(entitylist$entityegginfo.field_151512_d) > 0 || GuiStats.this.field_146546_t.func_77444_a(entitylist$entityegginfo.field_151513_e) > 0) {
               this.field_148222_l.add(entitylist$entityegginfo);
            }
         }

      }

      protected int func_148127_b() {
         return this.field_148222_l.size();
      }

      protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      }

      protected boolean func_148131_a(int p_148131_1_) {
         return false;
      }

      protected int func_148138_e() {
         return this.func_148127_b() * GuiStats.this.field_146289_q.field_78288_b * 4;
      }

      protected void func_148123_a() {
         GuiStats.this.func_146276_q_();
      }

      protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
         EntityList.EntityEggInfo entitylist$entityegginfo = (EntityList.EntityEggInfo)this.field_148222_l.get(p_180791_1_);
         String s = I18n.func_135052_a("entity." + EntityList.func_75617_a(entitylist$entityegginfo.field_75613_a) + ".name", new Object[0]);
         int i = GuiStats.this.field_146546_t.func_77444_a(entitylist$entityegginfo.field_151512_d);
         int j = GuiStats.this.field_146546_t.func_77444_a(entitylist$entityegginfo.field_151513_e);
         String s1 = I18n.func_135052_a("stat.entityKills", new Object[]{Integer.valueOf(i), s});
         String s2 = I18n.func_135052_a("stat.entityKilledBy", new Object[]{s, Integer.valueOf(j)});
         if(i == 0) {
            s1 = I18n.func_135052_a("stat.entityKills.none", new Object[]{s});
         }

         if(j == 0) {
            s2 = I18n.func_135052_a("stat.entityKilledBy.none", new Object[]{s});
         }

         GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, s, p_180791_2_ + 2 - 10, p_180791_3_ + 1, 16777215);
         GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, s1, p_180791_2_ + 2, p_180791_3_ + 1 + GuiStats.this.field_146289_q.field_78288_b, i == 0?6316128:9474192);
         GuiStats.this.func_73731_b(GuiStats.this.field_146289_q, s2, p_180791_2_ + 2, p_180791_3_ + 1 + GuiStats.this.field_146289_q.field_78288_b * 2, j == 0?6316128:9474192);
      }
   }
}
