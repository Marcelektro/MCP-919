package net.minecraft.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ScreenShotHelper {
   private static final Logger field_148261_a = LogManager.getLogger();
   private static final DateFormat field_74295_a = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private static IntBuffer field_74293_b;
   private static int[] field_74294_c;

   public static IChatComponent func_148260_a(File p_148260_0_, int p_148260_1_, int p_148260_2_, Framebuffer p_148260_3_) {
      return func_148259_a(p_148260_0_, (String)null, p_148260_1_, p_148260_2_, p_148260_3_);
   }

   public static IChatComponent func_148259_a(File p_148259_0_, String p_148259_1_, int p_148259_2_, int p_148259_3_, Framebuffer p_148259_4_) {
      try {
         File file1 = new File(p_148259_0_, "screenshots");
         file1.mkdir();
         if(OpenGlHelper.func_148822_b()) {
            p_148259_2_ = p_148259_4_.field_147622_a;
            p_148259_3_ = p_148259_4_.field_147620_b;
         }

         int i = p_148259_2_ * p_148259_3_;
         if(field_74293_b == null || field_74293_b.capacity() < i) {
            field_74293_b = BufferUtils.createIntBuffer(i);
            field_74294_c = new int[i];
         }

         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
         field_74293_b.clear();
         if(OpenGlHelper.func_148822_b()) {
            GlStateManager.func_179144_i(p_148259_4_.field_147617_g);
            GL11.glGetTexImage(3553, 0, '\u80e1', '\u8367', (IntBuffer)field_74293_b);
         } else {
            GL11.glReadPixels(0, 0, p_148259_2_, p_148259_3_, '\u80e1', '\u8367', (IntBuffer)field_74293_b);
         }

         field_74293_b.get(field_74294_c);
         TextureUtil.func_147953_a(field_74294_c, p_148259_2_, p_148259_3_);
         BufferedImage bufferedimage = null;
         if(OpenGlHelper.func_148822_b()) {
            bufferedimage = new BufferedImage(p_148259_4_.field_147621_c, p_148259_4_.field_147618_d, 1);
            int j = p_148259_4_.field_147620_b - p_148259_4_.field_147618_d;

            for(int k = j; k < p_148259_4_.field_147620_b; ++k) {
               for(int l = 0; l < p_148259_4_.field_147621_c; ++l) {
                  bufferedimage.setRGB(l, k - j, field_74294_c[k * p_148259_4_.field_147622_a + l]);
               }
            }
         } else {
            bufferedimage = new BufferedImage(p_148259_2_, p_148259_3_, 1);
            bufferedimage.setRGB(0, 0, p_148259_2_, p_148259_3_, field_74294_c, 0, p_148259_2_);
         }

         File file2;
         if(p_148259_1_ == null) {
            file2 = func_74290_a(file1);
         } else {
            file2 = new File(file1, p_148259_1_);
         }

         ImageIO.write(bufferedimage, "png", (File)file2);
         IChatComponent ichatcomponent = new ChatComponentText(file2.getName());
         ichatcomponent.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_FILE, file2.getAbsolutePath()));
         ichatcomponent.func_150256_b().func_150228_d(Boolean.valueOf(true));
         return new ChatComponentTranslation("screenshot.success", new Object[]{ichatcomponent});
      } catch (Exception exception) {
         field_148261_a.warn((String)"Couldn\'t save screenshot", (Throwable)exception);
         return new ChatComponentTranslation("screenshot.failure", new Object[]{exception.getMessage()});
      }
   }

   private static File func_74290_a(File p_74290_0_) {
      String s = field_74295_a.format(new Date()).toString();
      int i = 1;

      while(true) {
         File file1 = new File(p_74290_0_, s + (i == 1?"":"_" + i) + ".png");
         if(!file1.exists()) {
            return file1;
         }

         ++i;
      }
   }
}
