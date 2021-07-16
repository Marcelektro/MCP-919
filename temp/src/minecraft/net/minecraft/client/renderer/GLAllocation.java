package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GLAllocation {
   public static synchronized int func_74526_a(int p_74526_0_) {
      int i = GL11.glGenLists(p_74526_0_);
      if(i == 0) {
         int j = GL11.glGetError();
         String s = "No error code reported";
         if(j != 0) {
            s = GLU.gluErrorString(j);
         }

         throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + p_74526_0_ + ", GL error (" + j + "): " + s);
      } else {
         return i;
      }
   }

   public static synchronized void func_178874_a(int p_178874_0_, int p_178874_1_) {
      GL11.glDeleteLists(p_178874_0_, p_178874_1_);
   }

   public static synchronized void func_74523_b(int p_74523_0_) {
      GL11.glDeleteLists(p_74523_0_, 1);
   }

   public static synchronized ByteBuffer func_74524_c(int p_74524_0_) {
      return ByteBuffer.allocateDirect(p_74524_0_).order(ByteOrder.nativeOrder());
   }

   public static IntBuffer func_74527_f(int p_74527_0_) {
      return func_74524_c(p_74527_0_ << 2).asIntBuffer();
   }

   public static FloatBuffer func_74529_h(int p_74529_0_) {
      return func_74524_c(p_74529_0_ << 2).asFloatBuffer();
   }
}
