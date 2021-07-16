package net.minecraft.client.resources.model;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;

public class WeightedBakedModel implements IBakedModel {
   private final int field_177567_a;
   private final List<WeightedBakedModel.MyWeighedRandomItem> field_177565_b;
   private final IBakedModel field_177566_c;

   public WeightedBakedModel(List<WeightedBakedModel.MyWeighedRandomItem> p_i46073_1_) {
      this.field_177565_b = p_i46073_1_;
      this.field_177567_a = WeightedRandom.func_76272_a(p_i46073_1_);
      this.field_177566_c = ((WeightedBakedModel.MyWeighedRandomItem)p_i46073_1_.get(0)).field_177636_b;
   }

   public List<BakedQuad> func_177551_a(EnumFacing p_177551_1_) {
      return this.field_177566_c.func_177551_a(p_177551_1_);
   }

   public List<BakedQuad> func_177550_a() {
      return this.field_177566_c.func_177550_a();
   }

   public boolean func_177555_b() {
      return this.field_177566_c.func_177555_b();
   }

   public boolean func_177556_c() {
      return this.field_177566_c.func_177556_c();
   }

   public boolean func_177553_d() {
      return this.field_177566_c.func_177553_d();
   }

   public TextureAtlasSprite func_177554_e() {
      return this.field_177566_c.func_177554_e();
   }

   public ItemCameraTransforms func_177552_f() {
      return this.field_177566_c.func_177552_f();
   }

   public IBakedModel func_177564_a(long p_177564_1_) {
      return ((WeightedBakedModel.MyWeighedRandomItem)WeightedRandom.func_180166_a(this.field_177565_b, Math.abs((int)p_177564_1_ >> 16) % this.field_177567_a)).field_177636_b;
   }

   public static class Builder {
      private List<WeightedBakedModel.MyWeighedRandomItem> field_177678_a = Lists.<WeightedBakedModel.MyWeighedRandomItem>newArrayList();

      public WeightedBakedModel.Builder func_177677_a(IBakedModel p_177677_1_, int p_177677_2_) {
         this.field_177678_a.add(new WeightedBakedModel.MyWeighedRandomItem(p_177677_1_, p_177677_2_));
         return this;
      }

      public WeightedBakedModel func_177676_a() {
         Collections.sort(this.field_177678_a);
         return new WeightedBakedModel(this.field_177678_a);
      }

      public IBakedModel func_177675_b() {
         return ((WeightedBakedModel.MyWeighedRandomItem)this.field_177678_a.get(0)).field_177636_b;
      }
   }

   static class MyWeighedRandomItem extends WeightedRandom.Item implements Comparable<WeightedBakedModel.MyWeighedRandomItem> {
      protected final IBakedModel field_177636_b;

      public MyWeighedRandomItem(IBakedModel p_i46072_1_, int p_i46072_2_) {
         super(p_i46072_2_);
         this.field_177636_b = p_i46072_1_;
      }

      public int compareTo(WeightedBakedModel.MyWeighedRandomItem p_compareTo_1_) {
         return ComparisonChain.start().compare(p_compareTo_1_.field_76292_a, this.field_76292_a).compare(this.func_177635_a(), p_compareTo_1_.func_177635_a()).result();
      }

      protected int func_177635_a() {
         int i = this.field_177636_b.func_177550_a().size();

         for(EnumFacing enumfacing : EnumFacing.values()) {
            i += this.field_177636_b.func_177551_a(enumfacing).size();
         }

         return i;
      }

      public String toString() {
         return "MyWeighedRandomItem{weight=" + this.field_76292_a + ", model=" + this.field_177636_b + '}';
      }
   }
}
