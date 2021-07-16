package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.TextureClock;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;

public class TextureAtlasSprite {
   private final String field_110984_i;
   protected List<int[][]> field_110976_a = Lists.<int[][]>newArrayList();
   protected int[][] field_176605_b;
   private AnimationMetadataSection field_110982_k;
   protected boolean field_130222_e;
   protected int field_110975_c;
   protected int field_110974_d;
   protected int field_130223_c;
   protected int field_130224_d;
   private float field_110979_l;
   private float field_110980_m;
   private float field_110977_n;
   private float field_110978_o;
   protected int field_110973_g;
   protected int field_110983_h;
   private static String field_176607_p = "builtin/clock";
   private static String field_176606_q = "builtin/compass";

   protected TextureAtlasSprite(String p_i1282_1_) {
      this.field_110984_i = p_i1282_1_;
   }

   protected static TextureAtlasSprite func_176604_a(ResourceLocation p_176604_0_) {
      String s = p_176604_0_.toString();
      return (TextureAtlasSprite)(field_176607_p.equals(s)?new TextureClock(s):(field_176606_q.equals(s)?new TextureCompass(s):new TextureAtlasSprite(s)));
   }

   public static void func_176602_a(String p_176602_0_) {
      field_176607_p = p_176602_0_;
   }

   public static void func_176603_b(String p_176603_0_) {
      field_176606_q = p_176603_0_;
   }

   public void func_110971_a(int p_110971_1_, int p_110971_2_, int p_110971_3_, int p_110971_4_, boolean p_110971_5_) {
      this.field_110975_c = p_110971_3_;
      this.field_110974_d = p_110971_4_;
      this.field_130222_e = p_110971_5_;
      float f = (float)(0.009999999776482582D / (double)p_110971_1_);
      float f1 = (float)(0.009999999776482582D / (double)p_110971_2_);
      this.field_110979_l = (float)p_110971_3_ / (float)((double)p_110971_1_) + f;
      this.field_110980_m = (float)(p_110971_3_ + this.field_130223_c) / (float)((double)p_110971_1_) - f;
      this.field_110977_n = (float)p_110971_4_ / (float)p_110971_2_ + f1;
      this.field_110978_o = (float)(p_110971_4_ + this.field_130224_d) / (float)p_110971_2_ - f1;
   }

   public void func_94217_a(TextureAtlasSprite p_94217_1_) {
      this.field_110975_c = p_94217_1_.field_110975_c;
      this.field_110974_d = p_94217_1_.field_110974_d;
      this.field_130223_c = p_94217_1_.field_130223_c;
      this.field_130224_d = p_94217_1_.field_130224_d;
      this.field_130222_e = p_94217_1_.field_130222_e;
      this.field_110979_l = p_94217_1_.field_110979_l;
      this.field_110980_m = p_94217_1_.field_110980_m;
      this.field_110977_n = p_94217_1_.field_110977_n;
      this.field_110978_o = p_94217_1_.field_110978_o;
   }

   public int func_130010_a() {
      return this.field_110975_c;
   }

   public int func_110967_i() {
      return this.field_110974_d;
   }

   public int func_94211_a() {
      return this.field_130223_c;
   }

   public int func_94216_b() {
      return this.field_130224_d;
   }

   public float func_94209_e() {
      return this.field_110979_l;
   }

   public float func_94212_f() {
      return this.field_110980_m;
   }

   public float func_94214_a(double p_94214_1_) {
      float f = this.field_110980_m - this.field_110979_l;
      return this.field_110979_l + f * (float)p_94214_1_ / 16.0F;
   }

   public float func_94206_g() {
      return this.field_110977_n;
   }

   public float func_94210_h() {
      return this.field_110978_o;
   }

   public float func_94207_b(double p_94207_1_) {
      float f = this.field_110978_o - this.field_110977_n;
      return this.field_110977_n + f * ((float)p_94207_1_ / 16.0F);
   }

   public String func_94215_i() {
      return this.field_110984_i;
   }

   public void func_94219_l() {
      ++this.field_110983_h;
      if(this.field_110983_h >= this.field_110982_k.func_110472_a(this.field_110973_g)) {
         int i = this.field_110982_k.func_110468_c(this.field_110973_g);
         int j = this.field_110982_k.func_110473_c() == 0?this.field_110976_a.size():this.field_110982_k.func_110473_c();
         this.field_110973_g = (this.field_110973_g + 1) % j;
         this.field_110983_h = 0;
         int k = this.field_110982_k.func_110468_c(this.field_110973_g);
         if(i != k && k >= 0 && k < this.field_110976_a.size()) {
            TextureUtil.func_147955_a((int[][])this.field_110976_a.get(k), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
         }
      } else if(this.field_110982_k.func_177219_e()) {
         this.func_180599_n();
      }

   }

   private void func_180599_n() {
      double d0 = 1.0D - (double)this.field_110983_h / (double)this.field_110982_k.func_110472_a(this.field_110973_g);
      int i = this.field_110982_k.func_110468_c(this.field_110973_g);
      int j = this.field_110982_k.func_110473_c() == 0?this.field_110976_a.size():this.field_110982_k.func_110473_c();
      int k = this.field_110982_k.func_110468_c((this.field_110973_g + 1) % j);
      if(i != k && k >= 0 && k < this.field_110976_a.size()) {
         int[][] aint = (int[][])this.field_110976_a.get(i);
         int[][] aint1 = (int[][])this.field_110976_a.get(k);
         if(this.field_176605_b == null || this.field_176605_b.length != aint.length) {
            this.field_176605_b = new int[aint.length][];
         }

         for(int l = 0; l < aint.length; ++l) {
            if(this.field_176605_b[l] == null) {
               this.field_176605_b[l] = new int[aint[l].length];
            }

            if(l < aint1.length && aint1[l].length == aint[l].length) {
               for(int i1 = 0; i1 < aint[l].length; ++i1) {
                  int j1 = aint[l][i1];
                  int k1 = aint1[l][i1];
                  int l1 = (int)((double)((j1 & 16711680) >> 16) * d0 + (double)((k1 & 16711680) >> 16) * (1.0D - d0));
                  int i2 = (int)((double)((j1 & '\uff00') >> 8) * d0 + (double)((k1 & '\uff00') >> 8) * (1.0D - d0));
                  int j2 = (int)((double)(j1 & 255) * d0 + (double)(k1 & 255) * (1.0D - d0));
                  this.field_176605_b[l][i1] = j1 & -16777216 | l1 << 16 | i2 << 8 | j2;
               }
            }
         }

         TextureUtil.func_147955_a(this.field_176605_b, this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
      }

   }

   public int[][] func_147965_a(int p_147965_1_) {
      return (int[][])this.field_110976_a.get(p_147965_1_);
   }

   public int func_110970_k() {
      return this.field_110976_a.size();
   }

   public void func_110966_b(int p_110966_1_) {
      this.field_130223_c = p_110966_1_;
   }

   public void func_110969_c(int p_110969_1_) {
      this.field_130224_d = p_110969_1_;
   }

   public void func_180598_a(BufferedImage[] p_180598_1_, AnimationMetadataSection p_180598_2_) throws IOException {
      this.func_130102_n();
      int i = p_180598_1_[0].getWidth();
      int j = p_180598_1_[0].getHeight();
      this.field_130223_c = i;
      this.field_130224_d = j;
      int[][] aint = new int[p_180598_1_.length][];

      for(int k = 0; k < p_180598_1_.length; ++k) {
         BufferedImage bufferedimage = p_180598_1_[k];
         if(bufferedimage != null) {
            if(k > 0 && (bufferedimage.getWidth() != i >> k || bufferedimage.getHeight() != j >> k)) {
               throw new RuntimeException(String.format("Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", new Object[]{Integer.valueOf(k), Integer.valueOf(bufferedimage.getWidth()), Integer.valueOf(bufferedimage.getHeight()), Integer.valueOf(i >> k), Integer.valueOf(j >> k)}));
            }

            aint[k] = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), aint[k], 0, bufferedimage.getWidth());
         }
      }

      if(p_180598_2_ == null) {
         if(j != i) {
            throw new RuntimeException("broken aspect ratio and not an animation");
         }

         this.field_110976_a.add(aint);
      } else {
         int j1 = j / i;
         int k1 = i;
         int l = i;
         this.field_130224_d = this.field_130223_c;
         if(p_180598_2_.func_110473_c() > 0) {
            Iterator iterator = p_180598_2_.func_130073_e().iterator();

            while(iterator.hasNext()) {
               int i1 = ((Integer)iterator.next()).intValue();
               if(i1 >= j1) {
                  throw new RuntimeException("invalid frameindex " + i1);
               }

               this.func_130099_d(i1);
               this.field_110976_a.set(i1, func_147962_a(aint, k1, l, i1));
            }

            this.field_110982_k = p_180598_2_;
         } else {
            List<AnimationFrame> list = Lists.<AnimationFrame>newArrayList();

            for(int l1 = 0; l1 < j1; ++l1) {
               this.field_110976_a.add(func_147962_a(aint, k1, l, l1));
               list.add(new AnimationFrame(l1, -1));
            }

            this.field_110982_k = new AnimationMetadataSection(list, this.field_130223_c, this.field_130224_d, p_180598_2_.func_110469_d(), p_180598_2_.func_177219_e());
         }
      }

   }

   public void func_147963_d(int p_147963_1_) {
      List<int[][]> list = Lists.<int[][]>newArrayList();

      for(int i = 0; i < this.field_110976_a.size(); ++i) {
         final int[][] aint = (int[][])this.field_110976_a.get(i);
         if(aint != null) {
            try {
               list.add(TextureUtil.func_147949_a(p_147963_1_, this.field_130223_c, aint));
            } catch (Throwable throwable) {
               CrashReport crashreport = CrashReport.func_85055_a(throwable, "Generating mipmaps for frame");
               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Frame being iterated");
               crashreportcategory.func_71507_a("Frame index", Integer.valueOf(i));
               crashreportcategory.func_71500_a("Frame sizes", new Callable<String>() {
                  public String call() throws Exception {
                     StringBuilder stringbuilder = new StringBuilder();

                     for(int[] aint1 : aint) {
                        if(stringbuilder.length() > 0) {
                           stringbuilder.append(", ");
                        }

                        stringbuilder.append(aint1 == null?"null":Integer.valueOf(aint1.length));
                     }

                     return stringbuilder.toString();
                  }
               });
               throw new ReportedException(crashreport);
            }
         }
      }

      this.func_110968_a(list);
   }

   private void func_130099_d(int p_130099_1_) {
      if(this.field_110976_a.size() <= p_130099_1_) {
         for(int i = this.field_110976_a.size(); i <= p_130099_1_; ++i) {
            this.field_110976_a.add((int[][])null);
         }

      }
   }

   private static int[][] func_147962_a(int[][] p_147962_0_, int p_147962_1_, int p_147962_2_, int p_147962_3_) {
      int[][] aint = new int[p_147962_0_.length][];

      for(int i = 0; i < p_147962_0_.length; ++i) {
         int[] aint1 = p_147962_0_[i];
         if(aint1 != null) {
            aint[i] = new int[(p_147962_1_ >> i) * (p_147962_2_ >> i)];
            System.arraycopy(aint1, p_147962_3_ * aint[i].length, aint[i], 0, aint[i].length);
         }
      }

      return aint;
   }

   public void func_130103_l() {
      this.field_110976_a.clear();
   }

   public boolean func_130098_m() {
      return this.field_110982_k != null;
   }

   public void func_110968_a(List<int[][]> p_110968_1_) {
      this.field_110976_a = p_110968_1_;
   }

   private void func_130102_n() {
      this.field_110982_k = null;
      this.func_110968_a(Lists.<int[][]>newArrayList());
      this.field_110973_g = 0;
      this.field_110983_h = 0;
   }

   public String toString() {
      return "TextureAtlasSprite{name=\'" + this.field_110984_i + '\'' + ", frameCount=" + this.field_110976_a.size() + ", rotated=" + this.field_130222_e + ", x=" + this.field_110975_c + ", y=" + this.field_110974_d + ", height=" + this.field_130224_d + ", width=" + this.field_130223_c + ", u0=" + this.field_110979_l + ", u1=" + this.field_110980_m + ", v0=" + this.field_110977_n + ", v1=" + this.field_110978_o + '}';
   }
}
