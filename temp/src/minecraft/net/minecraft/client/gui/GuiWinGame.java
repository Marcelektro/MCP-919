package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiWinGame extends GuiScreen {
   private static final Logger field_146580_a = LogManager.getLogger();
   private static final ResourceLocation field_146576_f = new ResourceLocation("textures/gui/title/minecraft.png");
   private static final ResourceLocation field_146577_g = new ResourceLocation("textures/misc/vignette.png");
   private int field_146581_h;
   private List<String> field_146582_i;
   private int field_146579_r;
   private float field_146578_s = 0.5F;

   public void func_73876_c() {
      MusicTicker musicticker = this.field_146297_k.func_181535_r();
      SoundHandler soundhandler = this.field_146297_k.func_147118_V();
      if(this.field_146581_h == 0) {
         musicticker.func_181557_a();
         musicticker.func_181558_a(MusicTicker.MusicType.CREDITS);
         soundhandler.func_147687_e();
      }

      soundhandler.func_73660_a();
      ++this.field_146581_h;
      float f = (float)(this.field_146579_r + this.field_146295_m + this.field_146295_m + 24) / this.field_146578_s;
      if((float)this.field_146581_h > f) {
         this.func_146574_g();
      }

   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if(p_73869_2_ == 1) {
         this.func_146574_g();
      }

   }

   private void func_146574_g() {
      this.field_146297_k.field_71439_g.field_71174_a.func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
      this.field_146297_k.func_147108_a((GuiScreen)null);
   }

   public boolean func_73868_f() {
      return true;
   }

   public void func_73866_w_() {
      if(this.field_146582_i == null) {
         this.field_146582_i = Lists.<String>newArrayList();

         try {
            String s = "";
            String s1 = "" + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
            int i = 274;
            InputStream inputstream = this.field_146297_k.func_110442_L().func_110536_a(new ResourceLocation("texts/end.txt")).func_110527_b();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, Charsets.UTF_8));
            Random random = new Random(8124371L);

            while((s = bufferedreader.readLine()) != null) {
               String s2;
               String s3;
               for(s = s.replaceAll("PLAYERNAME", this.field_146297_k.func_110432_I().func_111285_a()); s.contains(s1); s = s2 + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, random.nextInt(4) + 3) + s3) {
                  int j = s.indexOf(s1);
                  s2 = s.substring(0, j);
                  s3 = s.substring(j + s1.length());
               }

               this.field_146582_i.addAll(this.field_146297_k.field_71466_p.func_78271_c(s, i));
               this.field_146582_i.add("");
            }

            inputstream.close();

            for(int k = 0; k < 8; ++k) {
               this.field_146582_i.add("");
            }

            inputstream = this.field_146297_k.func_110442_L().func_110536_a(new ResourceLocation("texts/credits.txt")).func_110527_b();
            bufferedreader = new BufferedReader(new InputStreamReader(inputstream, Charsets.UTF_8));

            while((s = bufferedreader.readLine()) != null) {
               s = s.replaceAll("PLAYERNAME", this.field_146297_k.func_110432_I().func_111285_a());
               s = s.replaceAll("\t", "    ");
               this.field_146582_i.addAll(this.field_146297_k.field_71466_p.func_78271_c(s, i));
               this.field_146582_i.add("");
            }

            inputstream.close();
            this.field_146579_r = this.field_146582_i.size() * 12;
         } catch (Exception exception) {
            field_146580_a.error((String)"Couldn\'t load credits", (Throwable)exception);
         }

      }
   }

   private void func_146575_b(int p_146575_1_, int p_146575_2_, float p_146575_3_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      this.field_146297_k.func_110434_K().func_110577_a(Gui.field_110325_k);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      int i = this.field_146294_l;
      float f = 0.0F - ((float)this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
      float f1 = (float)this.field_146295_m - ((float)this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
      float f2 = 0.015625F;
      float f3 = ((float)this.field_146581_h + p_146575_3_ - 0.0F) * 0.02F;
      float f4 = (float)(this.field_146579_r + this.field_146295_m + this.field_146295_m + 24) / this.field_146578_s;
      float f5 = (f4 - 20.0F - ((float)this.field_146581_h + p_146575_3_)) * 0.005F;
      if(f5 < f3) {
         f3 = f5;
      }

      if(f3 > 1.0F) {
         f3 = 1.0F;
      }

      f3 = f3 * f3;
      f3 = f3 * 96.0F / 255.0F;
      worldrenderer.func_181662_b(0.0D, (double)this.field_146295_m, (double)this.field_73735_i).func_181673_a(0.0D, (double)(f * f2)).func_181666_a(f3, f3, f3, 1.0F).func_181675_d();
      worldrenderer.func_181662_b((double)i, (double)this.field_146295_m, (double)this.field_73735_i).func_181673_a((double)((float)i * f2), (double)(f * f2)).func_181666_a(f3, f3, f3, 1.0F).func_181675_d();
      worldrenderer.func_181662_b((double)i, 0.0D, (double)this.field_73735_i).func_181673_a((double)((float)i * f2), (double)(f1 * f2)).func_181666_a(f3, f3, f3, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(0.0D, 0.0D, (double)this.field_73735_i).func_181673_a(0.0D, (double)(f1 * f2)).func_181666_a(f3, f3, f3, 1.0F).func_181675_d();
      tessellator.func_78381_a();
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146575_b(p_73863_1_, p_73863_2_, p_73863_3_);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      int i = 274;
      int j = this.field_146294_l / 2 - i / 2;
      int k = this.field_146295_m + 50;
      float f = -((float)this.field_146581_h + p_73863_3_) * this.field_146578_s;
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, f, 0.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_146576_f);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.func_73729_b(j, k, 0, 0, 155, 44);
      this.func_73729_b(j + 155, k, 0, 45, 155, 44);
      int l = k + 200;

      for(int i1 = 0; i1 < this.field_146582_i.size(); ++i1) {
         if(i1 == this.field_146582_i.size() - 1) {
            float f1 = (float)l + f - (float)(this.field_146295_m / 2 - 6);
            if(f1 < 0.0F) {
               GlStateManager.func_179109_b(0.0F, -f1, 0.0F);
            }
         }

         if((float)l + f + 12.0F + 8.0F > 0.0F && (float)l + f < (float)this.field_146295_m) {
            String s = (String)this.field_146582_i.get(i1);
            if(s.startsWith("[C]")) {
               this.field_146289_q.func_175063_a(s.substring(3), (float)(j + (i - this.field_146289_q.func_78256_a(s.substring(3))) / 2), (float)l, 16777215);
            } else {
               this.field_146289_q.field_78289_c.setSeed((long)i1 * 4238972211L + (long)(this.field_146581_h / 4));
               this.field_146289_q.func_175063_a(s, (float)j, (float)l, 16777215);
            }
         }

         l += 12;
      }

      GlStateManager.func_179121_F();
      this.field_146297_k.func_110434_K().func_110577_a(field_146577_g);
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(0, 769);
      int j1 = this.field_146294_l;
      int k1 = this.field_146295_m;
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      worldrenderer.func_181662_b(0.0D, (double)k1, (double)this.field_73735_i).func_181673_a(0.0D, 1.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      worldrenderer.func_181662_b((double)j1, (double)k1, (double)this.field_73735_i).func_181673_a(1.0D, 1.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      worldrenderer.func_181662_b((double)j1, 0.0D, (double)this.field_73735_i).func_181673_a(1.0D, 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(0.0D, 0.0D, (double)this.field_73735_i).func_181673_a(0.0D, 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179084_k();
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
