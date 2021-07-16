package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import net.minecraft.client.renderer.IImageBuffer;

public class ImageBufferDownload implements IImageBuffer {
   private int[] field_78438_a;
   private int field_78436_b;
   private int field_78437_c;

   public BufferedImage func_78432_a(BufferedImage p_78432_1_) {
      if(p_78432_1_ == null) {
         return null;
      } else {
         this.field_78436_b = 64;
         this.field_78437_c = 64;
         BufferedImage bufferedimage = new BufferedImage(this.field_78436_b, this.field_78437_c, 2);
         Graphics graphics = bufferedimage.getGraphics();
         graphics.drawImage(p_78432_1_, 0, 0, (ImageObserver)null);
         if(p_78432_1_.getHeight() == 32) {
            graphics.drawImage(bufferedimage, 24, 48, 20, 52, 4, 16, 8, 20, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 28, 48, 24, 52, 8, 16, 12, 20, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 20, 52, 16, 64, 8, 20, 12, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 24, 52, 20, 64, 4, 20, 8, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 28, 52, 24, 64, 0, 20, 4, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 32, 52, 28, 64, 12, 20, 16, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 40, 48, 36, 52, 44, 16, 48, 20, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 44, 48, 40, 52, 48, 16, 52, 20, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 36, 52, 32, 64, 48, 20, 52, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 40, 52, 36, 64, 44, 20, 48, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 44, 52, 40, 64, 40, 20, 44, 32, (ImageObserver)null);
            graphics.drawImage(bufferedimage, 48, 52, 44, 64, 52, 20, 56, 32, (ImageObserver)null);
         }

         graphics.dispose();
         this.field_78438_a = ((DataBufferInt)bufferedimage.getRaster().getDataBuffer()).getData();
         this.func_78433_b(0, 0, 32, 16);
         this.func_78434_a(32, 0, 64, 32);
         this.func_78433_b(0, 16, 64, 32);
         this.func_78434_a(0, 32, 16, 48);
         this.func_78434_a(16, 32, 40, 48);
         this.func_78434_a(40, 32, 56, 48);
         this.func_78434_a(0, 48, 16, 64);
         this.func_78433_b(16, 48, 48, 64);
         this.func_78434_a(48, 48, 64, 64);
         return bufferedimage;
      }
   }

   public void func_152634_a() {
   }

   private void func_78434_a(int p_78434_1_, int p_78434_2_, int p_78434_3_, int p_78434_4_) {
      if(!this.func_78435_c(p_78434_1_, p_78434_2_, p_78434_3_, p_78434_4_)) {
         for(int i = p_78434_1_; i < p_78434_3_; ++i) {
            for(int j = p_78434_2_; j < p_78434_4_; ++j) {
               this.field_78438_a[i + j * this.field_78436_b] &= 16777215;
            }
         }

      }
   }

   private void func_78433_b(int p_78433_1_, int p_78433_2_, int p_78433_3_, int p_78433_4_) {
      for(int i = p_78433_1_; i < p_78433_3_; ++i) {
         for(int j = p_78433_2_; j < p_78433_4_; ++j) {
            this.field_78438_a[i + j * this.field_78436_b] |= -16777216;
         }
      }

   }

   private boolean func_78435_c(int p_78435_1_, int p_78435_2_, int p_78435_3_, int p_78435_4_) {
      for(int i = p_78435_1_; i < p_78435_3_; ++i) {
         for(int j = p_78435_2_; j < p_78435_4_; ++j) {
            int k = this.field_78438_a[i + j * this.field_78436_b];
            if((k >> 24 & 255) < 128) {
               return true;
            }
         }
      }

      return false;
   }
}
