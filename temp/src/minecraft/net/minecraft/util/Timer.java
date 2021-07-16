package net.minecraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class Timer {
   float field_74282_a;
   private double field_74276_f;
   public int field_74280_b;
   public float field_74281_c;
   public float field_74278_d = 1.0F;
   public float field_74279_e;
   private long field_74277_g;
   private long field_74284_h;
   private long field_74285_i;
   private double field_74283_j = 1.0D;

   public Timer(float p_i1018_1_) {
      this.field_74282_a = p_i1018_1_;
      this.field_74277_g = Minecraft.func_71386_F();
      this.field_74284_h = System.nanoTime() / 1000000L;
   }

   public void func_74275_a() {
      long i = Minecraft.func_71386_F();
      long j = i - this.field_74277_g;
      long k = System.nanoTime() / 1000000L;
      double d0 = (double)k / 1000.0D;
      if(j <= 1000L && j >= 0L) {
         this.field_74285_i += j;
         if(this.field_74285_i > 1000L) {
            long l = k - this.field_74284_h;
            double d1 = (double)this.field_74285_i / (double)l;
            this.field_74283_j += (d1 - this.field_74283_j) * 0.20000000298023224D;
            this.field_74284_h = k;
            this.field_74285_i = 0L;
         }

         if(this.field_74285_i < 0L) {
            this.field_74284_h = k;
         }
      } else {
         this.field_74276_f = d0;
      }

      this.field_74277_g = i;
      double d2 = (d0 - this.field_74276_f) * this.field_74283_j;
      this.field_74276_f = d0;
      d2 = MathHelper.func_151237_a(d2, 0.0D, 1.0D);
      this.field_74279_e = (float)((double)this.field_74279_e + d2 * (double)this.field_74278_d * (double)this.field_74282_a);
      this.field_74280_b = (int)this.field_74279_e;
      this.field_74279_e -= (float)this.field_74280_b;
      if(this.field_74280_b > 10) {
         this.field_74280_b = 10;
      }

      this.field_74281_c = this.field_74279_e;
   }
}
