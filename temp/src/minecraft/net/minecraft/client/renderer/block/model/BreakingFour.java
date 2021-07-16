package net.minecraft.client.renderer.block.model;

import java.util.Arrays;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class BreakingFour extends BakedQuad {
   private final TextureAtlasSprite field_178218_d;

   public BreakingFour(BakedQuad p_i46217_1_, TextureAtlasSprite p_i46217_2_) {
      super(Arrays.copyOf(p_i46217_1_.func_178209_a(), p_i46217_1_.func_178209_a().length), p_i46217_1_.field_178213_b, FaceBakery.func_178410_a(p_i46217_1_.func_178209_a()));
      this.field_178218_d = p_i46217_2_;
      this.func_178217_e();
   }

   private void func_178217_e() {
      for(int i = 0; i < 4; ++i) {
         this.func_178216_a(i);
      }

   }

   private void func_178216_a(int p_178216_1_) {
      int i = 7 * p_178216_1_;
      float f = Float.intBitsToFloat(this.field_178215_a[i]);
      float f1 = Float.intBitsToFloat(this.field_178215_a[i + 1]);
      float f2 = Float.intBitsToFloat(this.field_178215_a[i + 2]);
      float f3 = 0.0F;
      float f4 = 0.0F;
      switch(this.field_178214_c) {
      case DOWN:
         f3 = f * 16.0F;
         f4 = (1.0F - f2) * 16.0F;
         break;
      case UP:
         f3 = f * 16.0F;
         f4 = f2 * 16.0F;
         break;
      case NORTH:
         f3 = (1.0F - f) * 16.0F;
         f4 = (1.0F - f1) * 16.0F;
         break;
      case SOUTH:
         f3 = f * 16.0F;
         f4 = (1.0F - f1) * 16.0F;
         break;
      case WEST:
         f3 = f2 * 16.0F;
         f4 = (1.0F - f1) * 16.0F;
         break;
      case EAST:
         f3 = (1.0F - f2) * 16.0F;
         f4 = (1.0F - f1) * 16.0F;
      }

      this.field_178215_a[i + 4] = Float.floatToRawIntBits(this.field_178218_d.func_94214_a((double)f3));
      this.field_178215_a[i + 4 + 1] = Float.floatToRawIntBits(this.field_178218_d.func_94207_b((double)f4));
   }
}
