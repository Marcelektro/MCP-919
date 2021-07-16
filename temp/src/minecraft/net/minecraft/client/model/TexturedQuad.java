package net.minecraft.client.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Vec3;

public class TexturedQuad {
   public PositionTextureVertex[] field_78239_a;
   public int field_78237_b;
   private boolean field_78238_c;

   public TexturedQuad(PositionTextureVertex[] p_i46364_1_) {
      this.field_78239_a = p_i46364_1_;
      this.field_78237_b = p_i46364_1_.length;
   }

   public TexturedQuad(PositionTextureVertex[] p_i1153_1_, int p_i1153_2_, int p_i1153_3_, int p_i1153_4_, int p_i1153_5_, float p_i1153_6_, float p_i1153_7_) {
      this(p_i1153_1_);
      float f = 0.0F / p_i1153_6_;
      float f1 = 0.0F / p_i1153_7_;
      p_i1153_1_[0] = p_i1153_1_[0].func_78240_a((float)p_i1153_4_ / p_i1153_6_ - f, (float)p_i1153_3_ / p_i1153_7_ + f1);
      p_i1153_1_[1] = p_i1153_1_[1].func_78240_a((float)p_i1153_2_ / p_i1153_6_ + f, (float)p_i1153_3_ / p_i1153_7_ + f1);
      p_i1153_1_[2] = p_i1153_1_[2].func_78240_a((float)p_i1153_2_ / p_i1153_6_ + f, (float)p_i1153_5_ / p_i1153_7_ - f1);
      p_i1153_1_[3] = p_i1153_1_[3].func_78240_a((float)p_i1153_4_ / p_i1153_6_ - f, (float)p_i1153_5_ / p_i1153_7_ - f1);
   }

   public void func_78235_a() {
      PositionTextureVertex[] apositiontexturevertex = new PositionTextureVertex[this.field_78239_a.length];

      for(int i = 0; i < this.field_78239_a.length; ++i) {
         apositiontexturevertex[i] = this.field_78239_a[this.field_78239_a.length - i - 1];
      }

      this.field_78239_a = apositiontexturevertex;
   }

   public void func_178765_a(WorldRenderer p_178765_1_, float p_178765_2_) {
      Vec3 vec3 = this.field_78239_a[1].field_78243_a.func_72444_a(this.field_78239_a[0].field_78243_a);
      Vec3 vec31 = this.field_78239_a[1].field_78243_a.func_72444_a(this.field_78239_a[2].field_78243_a);
      Vec3 vec32 = vec31.func_72431_c(vec3).func_72432_b();
      float f = (float)vec32.field_72450_a;
      float f1 = (float)vec32.field_72448_b;
      float f2 = (float)vec32.field_72449_c;
      if(this.field_78238_c) {
         f = -f;
         f1 = -f1;
         f2 = -f2;
      }

      p_178765_1_.func_181668_a(7, DefaultVertexFormats.field_181703_c);

      for(int i = 0; i < 4; ++i) {
         PositionTextureVertex positiontexturevertex = this.field_78239_a[i];
         p_178765_1_.func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * (double)p_178765_2_, positiontexturevertex.field_78243_a.field_72448_b * (double)p_178765_2_, positiontexturevertex.field_78243_a.field_72449_c * (double)p_178765_2_).func_181673_a((double)positiontexturevertex.field_78241_b, (double)positiontexturevertex.field_78242_c).func_181663_c(f, f1, f2).func_181675_d();
      }

      Tessellator.func_178181_a().func_78381_a();
   }
}
