package net.minecraft.client.renderer;

import java.nio.FloatBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class GlStateManager {
   private static GlStateManager.AlphaState field_179160_a = new GlStateManager.AlphaState();
   private static GlStateManager.BooleanState field_179158_b = new GlStateManager.BooleanState(2896);
   private static GlStateManager.BooleanState[] field_179159_c = new GlStateManager.BooleanState[8];
   private static GlStateManager.ColorMaterialState field_179156_d = new GlStateManager.ColorMaterialState();
   private static GlStateManager.BlendState field_179157_e = new GlStateManager.BlendState();
   private static GlStateManager.DepthState field_179154_f = new GlStateManager.DepthState();
   private static GlStateManager.FogState field_179155_g = new GlStateManager.FogState();
   private static GlStateManager.CullState field_179167_h = new GlStateManager.CullState();
   private static GlStateManager.PolygonOffsetState field_179168_i = new GlStateManager.PolygonOffsetState();
   private static GlStateManager.ColorLogicState field_179165_j = new GlStateManager.ColorLogicState();
   private static GlStateManager.TexGenState field_179166_k = new GlStateManager.TexGenState();
   private static GlStateManager.ClearState field_179163_l = new GlStateManager.ClearState();
   private static GlStateManager.StencilState field_179164_m = new GlStateManager.StencilState();
   private static GlStateManager.BooleanState field_179161_n = new GlStateManager.BooleanState(2977);
   private static int field_179162_o = 0;
   private static GlStateManager.TextureState[] field_179174_p = new GlStateManager.TextureState[8];
   private static int field_179173_q = 7425;
   private static GlStateManager.BooleanState field_179172_r = new GlStateManager.BooleanState('\u803a');
   private static GlStateManager.ColorMask field_179171_s = new GlStateManager.ColorMask();
   private static GlStateManager.Color field_179170_t = new GlStateManager.Color();

   public static void func_179123_a() {
      GL11.glPushAttrib(8256);
   }

   public static void func_179099_b() {
      GL11.glPopAttrib();
   }

   public static void func_179118_c() {
      field_179160_a.field_179208_a.func_179198_a();
   }

   public static void func_179141_d() {
      field_179160_a.field_179208_a.func_179200_b();
   }

   public static void func_179092_a(int p_179092_0_, float p_179092_1_) {
      if(p_179092_0_ != field_179160_a.field_179206_b || p_179092_1_ != field_179160_a.field_179207_c) {
         field_179160_a.field_179206_b = p_179092_0_;
         field_179160_a.field_179207_c = p_179092_1_;
         GL11.glAlphaFunc(p_179092_0_, p_179092_1_);
      }

   }

   public static void func_179145_e() {
      field_179158_b.func_179200_b();
   }

   public static void func_179140_f() {
      field_179158_b.func_179198_a();
   }

   public static void func_179085_a(int p_179085_0_) {
      field_179159_c[p_179085_0_].func_179200_b();
   }

   public static void func_179122_b(int p_179122_0_) {
      field_179159_c[p_179122_0_].func_179198_a();
   }

   public static void func_179142_g() {
      field_179156_d.field_179191_a.func_179200_b();
   }

   public static void func_179119_h() {
      field_179156_d.field_179191_a.func_179198_a();
   }

   public static void func_179104_a(int p_179104_0_, int p_179104_1_) {
      if(p_179104_0_ != field_179156_d.field_179189_b || p_179104_1_ != field_179156_d.field_179190_c) {
         field_179156_d.field_179189_b = p_179104_0_;
         field_179156_d.field_179190_c = p_179104_1_;
         GL11.glColorMaterial(p_179104_0_, p_179104_1_);
      }

   }

   public static void func_179097_i() {
      field_179154_f.field_179052_a.func_179198_a();
   }

   public static void func_179126_j() {
      field_179154_f.field_179052_a.func_179200_b();
   }

   public static void func_179143_c(int p_179143_0_) {
      if(p_179143_0_ != field_179154_f.field_179051_c) {
         field_179154_f.field_179051_c = p_179143_0_;
         GL11.glDepthFunc(p_179143_0_);
      }

   }

   public static void func_179132_a(boolean p_179132_0_) {
      if(p_179132_0_ != field_179154_f.field_179050_b) {
         field_179154_f.field_179050_b = p_179132_0_;
         GL11.glDepthMask(p_179132_0_);
      }

   }

   public static void func_179084_k() {
      field_179157_e.field_179213_a.func_179198_a();
   }

   public static void func_179147_l() {
      field_179157_e.field_179213_a.func_179200_b();
   }

   public static void func_179112_b(int p_179112_0_, int p_179112_1_) {
      if(p_179112_0_ != field_179157_e.field_179211_b || p_179112_1_ != field_179157_e.field_179212_c) {
         field_179157_e.field_179211_b = p_179112_0_;
         field_179157_e.field_179212_c = p_179112_1_;
         GL11.glBlendFunc(p_179112_0_, p_179112_1_);
      }

   }

   public static void func_179120_a(int p_179120_0_, int p_179120_1_, int p_179120_2_, int p_179120_3_) {
      if(p_179120_0_ != field_179157_e.field_179211_b || p_179120_1_ != field_179157_e.field_179212_c || p_179120_2_ != field_179157_e.field_179209_d || p_179120_3_ != field_179157_e.field_179210_e) {
         field_179157_e.field_179211_b = p_179120_0_;
         field_179157_e.field_179212_c = p_179120_1_;
         field_179157_e.field_179209_d = p_179120_2_;
         field_179157_e.field_179210_e = p_179120_3_;
         OpenGlHelper.func_148821_a(p_179120_0_, p_179120_1_, p_179120_2_, p_179120_3_);
      }

   }

   public static void func_179127_m() {
      field_179155_g.field_179049_a.func_179200_b();
   }

   public static void func_179106_n() {
      field_179155_g.field_179049_a.func_179198_a();
   }

   public static void func_179093_d(int p_179093_0_) {
      if(p_179093_0_ != field_179155_g.field_179047_b) {
         field_179155_g.field_179047_b = p_179093_0_;
         GL11.glFogi(2917, p_179093_0_);
      }

   }

   public static void func_179095_a(float p_179095_0_) {
      if(p_179095_0_ != field_179155_g.field_179048_c) {
         field_179155_g.field_179048_c = p_179095_0_;
         GL11.glFogf(2914, p_179095_0_);
      }

   }

   public static void func_179102_b(float p_179102_0_) {
      if(p_179102_0_ != field_179155_g.field_179045_d) {
         field_179155_g.field_179045_d = p_179102_0_;
         GL11.glFogf(2915, p_179102_0_);
      }

   }

   public static void func_179153_c(float p_179153_0_) {
      if(p_179153_0_ != field_179155_g.field_179046_e) {
         field_179155_g.field_179046_e = p_179153_0_;
         GL11.glFogf(2916, p_179153_0_);
      }

   }

   public static void func_179089_o() {
      field_179167_h.field_179054_a.func_179200_b();
   }

   public static void func_179129_p() {
      field_179167_h.field_179054_a.func_179198_a();
   }

   public static void func_179107_e(int p_179107_0_) {
      if(p_179107_0_ != field_179167_h.field_179053_b) {
         field_179167_h.field_179053_b = p_179107_0_;
         GL11.glCullFace(p_179107_0_);
      }

   }

   public static void func_179088_q() {
      field_179168_i.field_179044_a.func_179200_b();
   }

   public static void func_179113_r() {
      field_179168_i.field_179044_a.func_179198_a();
   }

   public static void func_179136_a(float p_179136_0_, float p_179136_1_) {
      if(p_179136_0_ != field_179168_i.field_179043_c || p_179136_1_ != field_179168_i.field_179041_d) {
         field_179168_i.field_179043_c = p_179136_0_;
         field_179168_i.field_179041_d = p_179136_1_;
         GL11.glPolygonOffset(p_179136_0_, p_179136_1_);
      }

   }

   public static void func_179115_u() {
      field_179165_j.field_179197_a.func_179200_b();
   }

   public static void func_179134_v() {
      field_179165_j.field_179197_a.func_179198_a();
   }

   public static void func_179116_f(int p_179116_0_) {
      if(p_179116_0_ != field_179165_j.field_179196_b) {
         field_179165_j.field_179196_b = p_179116_0_;
         GL11.glLogicOp(p_179116_0_);
      }

   }

   public static void func_179087_a(GlStateManager.TexGen p_179087_0_) {
      func_179125_c(p_179087_0_).field_179067_a.func_179200_b();
   }

   public static void func_179100_b(GlStateManager.TexGen p_179100_0_) {
      func_179125_c(p_179100_0_).field_179067_a.func_179198_a();
   }

   public static void func_179149_a(GlStateManager.TexGen p_179149_0_, int p_179149_1_) {
      GlStateManager.TexGenCoord glstatemanager$texgencoord = func_179125_c(p_179149_0_);
      if(p_179149_1_ != glstatemanager$texgencoord.field_179066_c) {
         glstatemanager$texgencoord.field_179066_c = p_179149_1_;
         GL11.glTexGeni(glstatemanager$texgencoord.field_179065_b, 9472, p_179149_1_);
      }

   }

   public static void func_179105_a(GlStateManager.TexGen p_179105_0_, int p_179105_1_, FloatBuffer p_179105_2_) {
      GL11.glTexGen(func_179125_c(p_179105_0_).field_179065_b, p_179105_1_, p_179105_2_);
   }

   private static GlStateManager.TexGenCoord func_179125_c(GlStateManager.TexGen p_179125_0_) {
      switch(p_179125_0_) {
      case S:
         return field_179166_k.field_179064_a;
      case T:
         return field_179166_k.field_179062_b;
      case R:
         return field_179166_k.field_179063_c;
      case Q:
         return field_179166_k.field_179061_d;
      default:
         return field_179166_k.field_179064_a;
      }
   }

   public static void func_179138_g(int p_179138_0_) {
      if(field_179162_o != p_179138_0_ - OpenGlHelper.field_77478_a) {
         field_179162_o = p_179138_0_ - OpenGlHelper.field_77478_a;
         OpenGlHelper.func_77473_a(p_179138_0_);
      }

   }

   public static void func_179098_w() {
      field_179174_p[field_179162_o].field_179060_a.func_179200_b();
   }

   public static void func_179090_x() {
      field_179174_p[field_179162_o].field_179060_a.func_179198_a();
   }

   public static int func_179146_y() {
      return GL11.glGenTextures();
   }

   public static void func_179150_h(int p_179150_0_) {
      GL11.glDeleteTextures(p_179150_0_);

      for(GlStateManager.TextureState glstatemanager$texturestate : field_179174_p) {
         if(glstatemanager$texturestate.field_179059_b == p_179150_0_) {
            glstatemanager$texturestate.field_179059_b = -1;
         }
      }

   }

   public static void func_179144_i(int p_179144_0_) {
      if(p_179144_0_ != field_179174_p[field_179162_o].field_179059_b) {
         field_179174_p[field_179162_o].field_179059_b = p_179144_0_;
         GL11.glBindTexture(3553, p_179144_0_);
      }

   }

   public static void func_179108_z() {
      field_179161_n.func_179200_b();
   }

   public static void func_179133_A() {
      field_179161_n.func_179198_a();
   }

   public static void func_179103_j(int p_179103_0_) {
      if(p_179103_0_ != field_179173_q) {
         field_179173_q = p_179103_0_;
         GL11.glShadeModel(p_179103_0_);
      }

   }

   public static void func_179091_B() {
      field_179172_r.func_179200_b();
   }

   public static void func_179101_C() {
      field_179172_r.func_179198_a();
   }

   public static void func_179083_b(int p_179083_0_, int p_179083_1_, int p_179083_2_, int p_179083_3_) {
      GL11.glViewport(p_179083_0_, p_179083_1_, p_179083_2_, p_179083_3_);
   }

   public static void func_179135_a(boolean p_179135_0_, boolean p_179135_1_, boolean p_179135_2_, boolean p_179135_3_) {
      if(p_179135_0_ != field_179171_s.field_179188_a || p_179135_1_ != field_179171_s.field_179186_b || p_179135_2_ != field_179171_s.field_179187_c || p_179135_3_ != field_179171_s.field_179185_d) {
         field_179171_s.field_179188_a = p_179135_0_;
         field_179171_s.field_179186_b = p_179135_1_;
         field_179171_s.field_179187_c = p_179135_2_;
         field_179171_s.field_179185_d = p_179135_3_;
         GL11.glColorMask(p_179135_0_, p_179135_1_, p_179135_2_, p_179135_3_);
      }

   }

   public static void func_179151_a(double p_179151_0_) {
      if(p_179151_0_ != field_179163_l.field_179205_a) {
         field_179163_l.field_179205_a = p_179151_0_;
         GL11.glClearDepth(p_179151_0_);
      }

   }

   public static void func_179082_a(float p_179082_0_, float p_179082_1_, float p_179082_2_, float p_179082_3_) {
      if(p_179082_0_ != field_179163_l.field_179203_b.field_179195_a || p_179082_1_ != field_179163_l.field_179203_b.field_179193_b || p_179082_2_ != field_179163_l.field_179203_b.field_179194_c || p_179082_3_ != field_179163_l.field_179203_b.field_179192_d) {
         field_179163_l.field_179203_b.field_179195_a = p_179082_0_;
         field_179163_l.field_179203_b.field_179193_b = p_179082_1_;
         field_179163_l.field_179203_b.field_179194_c = p_179082_2_;
         field_179163_l.field_179203_b.field_179192_d = p_179082_3_;
         GL11.glClearColor(p_179082_0_, p_179082_1_, p_179082_2_, p_179082_3_);
      }

   }

   public static void func_179086_m(int p_179086_0_) {
      GL11.glClear(p_179086_0_);
   }

   public static void func_179128_n(int p_179128_0_) {
      GL11.glMatrixMode(p_179128_0_);
   }

   public static void func_179096_D() {
      GL11.glLoadIdentity();
   }

   public static void func_179094_E() {
      GL11.glPushMatrix();
   }

   public static void func_179121_F() {
      GL11.glPopMatrix();
   }

   public static void func_179111_a(int p_179111_0_, FloatBuffer p_179111_1_) {
      GL11.glGetFloat(p_179111_0_, p_179111_1_);
   }

   public static void func_179130_a(double p_179130_0_, double p_179130_2_, double p_179130_4_, double p_179130_6_, double p_179130_8_, double p_179130_10_) {
      GL11.glOrtho(p_179130_0_, p_179130_2_, p_179130_4_, p_179130_6_, p_179130_8_, p_179130_10_);
   }

   public static void func_179114_b(float p_179114_0_, float p_179114_1_, float p_179114_2_, float p_179114_3_) {
      GL11.glRotatef(p_179114_0_, p_179114_1_, p_179114_2_, p_179114_3_);
   }

   public static void func_179152_a(float p_179152_0_, float p_179152_1_, float p_179152_2_) {
      GL11.glScalef(p_179152_0_, p_179152_1_, p_179152_2_);
   }

   public static void func_179139_a(double p_179139_0_, double p_179139_2_, double p_179139_4_) {
      GL11.glScaled(p_179139_0_, p_179139_2_, p_179139_4_);
   }

   public static void func_179109_b(float p_179109_0_, float p_179109_1_, float p_179109_2_) {
      GL11.glTranslatef(p_179109_0_, p_179109_1_, p_179109_2_);
   }

   public static void func_179137_b(double p_179137_0_, double p_179137_2_, double p_179137_4_) {
      GL11.glTranslated(p_179137_0_, p_179137_2_, p_179137_4_);
   }

   public static void func_179110_a(FloatBuffer p_179110_0_) {
      GL11.glMultMatrix(p_179110_0_);
   }

   public static void func_179131_c(float p_179131_0_, float p_179131_1_, float p_179131_2_, float p_179131_3_) {
      if(p_179131_0_ != field_179170_t.field_179195_a || p_179131_1_ != field_179170_t.field_179193_b || p_179131_2_ != field_179170_t.field_179194_c || p_179131_3_ != field_179170_t.field_179192_d) {
         field_179170_t.field_179195_a = p_179131_0_;
         field_179170_t.field_179193_b = p_179131_1_;
         field_179170_t.field_179194_c = p_179131_2_;
         field_179170_t.field_179192_d = p_179131_3_;
         GL11.glColor4f(p_179131_0_, p_179131_1_, p_179131_2_, p_179131_3_);
      }

   }

   public static void func_179124_c(float p_179124_0_, float p_179124_1_, float p_179124_2_) {
      func_179131_c(p_179124_0_, p_179124_1_, p_179124_2_, 1.0F);
   }

   public static void func_179117_G() {
      field_179170_t.field_179195_a = field_179170_t.field_179193_b = field_179170_t.field_179194_c = field_179170_t.field_179192_d = -1.0F;
   }

   public static void func_179148_o(int p_179148_0_) {
      GL11.glCallList(p_179148_0_);
   }

   static {
      for(int i = 0; i < 8; ++i) {
         field_179159_c[i] = new GlStateManager.BooleanState(16384 + i);
      }

      for(int j = 0; j < 8; ++j) {
         field_179174_p[j] = new GlStateManager.TextureState();
      }

   }

   static class AlphaState {
      public GlStateManager.BooleanState field_179208_a;
      public int field_179206_b;
      public float field_179207_c;

      private AlphaState() {
         this.field_179208_a = new GlStateManager.BooleanState(3008);
         this.field_179206_b = 519;
         this.field_179207_c = -1.0F;
      }
   }

   static class BlendState {
      public GlStateManager.BooleanState field_179213_a;
      public int field_179211_b;
      public int field_179212_c;
      public int field_179209_d;
      public int field_179210_e;

      private BlendState() {
         this.field_179213_a = new GlStateManager.BooleanState(3042);
         this.field_179211_b = 1;
         this.field_179212_c = 0;
         this.field_179209_d = 1;
         this.field_179210_e = 0;
      }
   }

   static class BooleanState {
      private final int field_179202_a;
      private boolean field_179201_b = false;

      public BooleanState(int p_i46267_1_) {
         this.field_179202_a = p_i46267_1_;
      }

      public void func_179198_a() {
         this.func_179199_a(false);
      }

      public void func_179200_b() {
         this.func_179199_a(true);
      }

      public void func_179199_a(boolean p_179199_1_) {
         if(p_179199_1_ != this.field_179201_b) {
            this.field_179201_b = p_179199_1_;
            if(p_179199_1_) {
               GL11.glEnable(this.field_179202_a);
            } else {
               GL11.glDisable(this.field_179202_a);
            }
         }

      }
   }

   static class ClearState {
      public double field_179205_a;
      public GlStateManager.Color field_179203_b;
      public int field_179204_c;

      private ClearState() {
         this.field_179205_a = 1.0D;
         this.field_179203_b = new GlStateManager.Color(0.0F, 0.0F, 0.0F, 0.0F);
         this.field_179204_c = 0;
      }
   }

   static class Color {
      public float field_179195_a = 1.0F;
      public float field_179193_b = 1.0F;
      public float field_179194_c = 1.0F;
      public float field_179192_d = 1.0F;

      public Color() {
      }

      public Color(float p_i46265_1_, float p_i46265_2_, float p_i46265_3_, float p_i46265_4_) {
         this.field_179195_a = p_i46265_1_;
         this.field_179193_b = p_i46265_2_;
         this.field_179194_c = p_i46265_3_;
         this.field_179192_d = p_i46265_4_;
      }
   }

   static class ColorLogicState {
      public GlStateManager.BooleanState field_179197_a;
      public int field_179196_b;

      private ColorLogicState() {
         this.field_179197_a = new GlStateManager.BooleanState(3058);
         this.field_179196_b = 5379;
      }
   }

   static class ColorMask {
      public boolean field_179188_a;
      public boolean field_179186_b;
      public boolean field_179187_c;
      public boolean field_179185_d;

      private ColorMask() {
         this.field_179188_a = true;
         this.field_179186_b = true;
         this.field_179187_c = true;
         this.field_179185_d = true;
      }
   }

   static class ColorMaterialState {
      public GlStateManager.BooleanState field_179191_a;
      public int field_179189_b;
      public int field_179190_c;

      private ColorMaterialState() {
         this.field_179191_a = new GlStateManager.BooleanState(2903);
         this.field_179189_b = 1032;
         this.field_179190_c = 5634;
      }
   }

   static class CullState {
      public GlStateManager.BooleanState field_179054_a;
      public int field_179053_b;

      private CullState() {
         this.field_179054_a = new GlStateManager.BooleanState(2884);
         this.field_179053_b = 1029;
      }
   }

   static class DepthState {
      public GlStateManager.BooleanState field_179052_a;
      public boolean field_179050_b;
      public int field_179051_c;

      private DepthState() {
         this.field_179052_a = new GlStateManager.BooleanState(2929);
         this.field_179050_b = true;
         this.field_179051_c = 513;
      }
   }

   static class FogState {
      public GlStateManager.BooleanState field_179049_a;
      public int field_179047_b;
      public float field_179048_c;
      public float field_179045_d;
      public float field_179046_e;

      private FogState() {
         this.field_179049_a = new GlStateManager.BooleanState(2912);
         this.field_179047_b = 2048;
         this.field_179048_c = 1.0F;
         this.field_179045_d = 0.0F;
         this.field_179046_e = 1.0F;
      }
   }

   static class PolygonOffsetState {
      public GlStateManager.BooleanState field_179044_a;
      public GlStateManager.BooleanState field_179042_b;
      public float field_179043_c;
      public float field_179041_d;

      private PolygonOffsetState() {
         this.field_179044_a = new GlStateManager.BooleanState('\u8037');
         this.field_179042_b = new GlStateManager.BooleanState(10754);
         this.field_179043_c = 0.0F;
         this.field_179041_d = 0.0F;
      }
   }

   static class StencilFunc {
      public int field_179081_a;
      public int field_179079_b;
      public int field_179080_c;

      private StencilFunc() {
         this.field_179081_a = 519;
         this.field_179079_b = 0;
         this.field_179080_c = -1;
      }
   }

   static class StencilState {
      public GlStateManager.StencilFunc field_179078_a;
      public int field_179076_b;
      public int field_179077_c;
      public int field_179074_d;
      public int field_179075_e;

      private StencilState() {
         this.field_179078_a = new GlStateManager.StencilFunc();
         this.field_179076_b = -1;
         this.field_179077_c = 7680;
         this.field_179074_d = 7680;
         this.field_179075_e = 7680;
      }
   }

   public static enum TexGen {
      S,
      T,
      R,
      Q;
   }

   static class TexGenCoord {
      public GlStateManager.BooleanState field_179067_a;
      public int field_179065_b;
      public int field_179066_c = -1;

      public TexGenCoord(int p_i46254_1_, int p_i46254_2_) {
         this.field_179065_b = p_i46254_1_;
         this.field_179067_a = new GlStateManager.BooleanState(p_i46254_2_);
      }
   }

   static class TexGenState {
      public GlStateManager.TexGenCoord field_179064_a;
      public GlStateManager.TexGenCoord field_179062_b;
      public GlStateManager.TexGenCoord field_179063_c;
      public GlStateManager.TexGenCoord field_179061_d;

      private TexGenState() {
         this.field_179064_a = new GlStateManager.TexGenCoord(8192, 3168);
         this.field_179062_b = new GlStateManager.TexGenCoord(8193, 3169);
         this.field_179063_c = new GlStateManager.TexGenCoord(8194, 3170);
         this.field_179061_d = new GlStateManager.TexGenCoord(8195, 3171);
      }
   }

   static class TextureState {
      public GlStateManager.BooleanState field_179060_a;
      public int field_179059_b;

      private TextureState() {
         this.field_179060_a = new GlStateManager.BooleanState(3553);
         this.field_179059_b = 0;
      }
   }
}
