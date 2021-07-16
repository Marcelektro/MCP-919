package net.minecraft.client.renderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class ActiveRenderInfo {
   private static final IntBuffer field_178814_a = GLAllocation.func_74527_f(16);
   private static final FloatBuffer field_178812_b = GLAllocation.func_74529_h(16);
   private static final FloatBuffer field_178813_c = GLAllocation.func_74529_h(16);
   private static final FloatBuffer field_178810_d = GLAllocation.func_74529_h(3);
   private static Vec3 field_178811_e = new Vec3(0.0D, 0.0D, 0.0D);
   private static float field_74588_d;
   private static float field_74589_e;
   private static float field_74586_f;
   private static float field_74587_g;
   private static float field_74596_h;

   public static void func_74583_a(EntityPlayer p_74583_0_, boolean p_74583_1_) {
      GlStateManager.func_179111_a(2982, field_178812_b);
      GlStateManager.func_179111_a(2983, field_178813_c);
      GL11.glGetInteger(2978, field_178814_a);
      float f = (float)((field_178814_a.get(0) + field_178814_a.get(2)) / 2);
      float f1 = (float)((field_178814_a.get(1) + field_178814_a.get(3)) / 2);
      GLU.gluUnProject(f, f1, 0.0F, field_178812_b, field_178813_c, field_178814_a, field_178810_d);
      field_178811_e = new Vec3((double)field_178810_d.get(0), (double)field_178810_d.get(1), (double)field_178810_d.get(2));
      int i = p_74583_1_?1:0;
      float f2 = p_74583_0_.field_70125_A;
      float f3 = p_74583_0_.field_70177_z;
      field_74588_d = MathHelper.func_76134_b(f3 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      field_74586_f = MathHelper.func_76126_a(f3 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      field_74587_g = -field_74586_f * MathHelper.func_76126_a(f2 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      field_74596_h = field_74588_d * MathHelper.func_76126_a(f2 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      field_74589_e = MathHelper.func_76134_b(f2 * 3.1415927F / 180.0F);
   }

   public static Vec3 func_178806_a(Entity p_178806_0_, double p_178806_1_) {
      double d0 = p_178806_0_.field_70169_q + (p_178806_0_.field_70165_t - p_178806_0_.field_70169_q) * p_178806_1_;
      double d1 = p_178806_0_.field_70167_r + (p_178806_0_.field_70163_u - p_178806_0_.field_70167_r) * p_178806_1_;
      double d2 = p_178806_0_.field_70166_s + (p_178806_0_.field_70161_v - p_178806_0_.field_70166_s) * p_178806_1_;
      double d3 = d0 + field_178811_e.field_72450_a;
      double d4 = d1 + field_178811_e.field_72448_b;
      double d5 = d2 + field_178811_e.field_72449_c;
      return new Vec3(d3, d4, d5);
   }

   public static Block func_180786_a(World p_180786_0_, Entity p_180786_1_, float p_180786_2_) {
      Vec3 vec3 = func_178806_a(p_180786_1_, (double)p_180786_2_);
      BlockPos blockpos = new BlockPos(vec3);
      IBlockState iblockstate = p_180786_0_.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      if(block.func_149688_o().func_76224_d()) {
         float f = 0.0F;
         if(iblockstate.func_177230_c() instanceof BlockLiquid) {
            f = BlockLiquid.func_149801_b(((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue()) - 0.11111111F;
         }

         float f1 = (float)(blockpos.func_177956_o() + 1) - f;
         if(vec3.field_72448_b >= (double)f1) {
            block = p_180786_0_.func_180495_p(blockpos.func_177984_a()).func_177230_c();
         }
      }

      return block;
   }

   public static Vec3 func_178804_a() {
      return field_178811_e;
   }

   public static float func_178808_b() {
      return field_74588_d;
   }

   public static float func_178809_c() {
      return field_74589_e;
   }

   public static float func_178803_d() {
      return field_74586_f;
   }

   public static float func_178805_e() {
      return field_74587_g;
   }

   public static float func_178807_f() {
      return field_74596_h;
   }
}
